package com.ground.support;


import com.ground.data.models.documents._Member;
import com.ground.data.models.documents._Usser;
import com.ground.data.models.documents._User;

import com.ground.data.models.documents._Usser._UsserBuilder;
import com.ground.data.models.documents._Member._MemberBuilder;
import com.ground.data.models.enums.ContactType;
import com.ground.data.models.supports.Snowflake_;
import com.ground.domain.enums.authentication.UserAuthority;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@Slf4j
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    DefaultServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
    URI uri = URI.create("http://localhost:5173/#/");

    @Autowired
    ReactiveMongoTemplate template;

    @Autowired
    SecurityUtils securityUtils;

    final FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true).upsert(true);

    final Map<String,Function<OAuth2User,Tuple2<_UsserBuilder, _MemberBuilder>>> mapper = Map.of(
            "google",u->{
                Tuple2<_UsserBuilder,_MemberBuilder> t = Tuples.of(_Usser.builder(), _Member.builder());

                t.getT1()
                        .id(u.getName())
                        .name(u.getAttribute("name"))
                        .avatar(u.getAttribute("picture"))
                        .provider("google");

                t.getT2()
                        .user(u.getName())
                        .name(u.getAttribute("name"))
                        .avatar(u.getAttribute("picture"))
                        .contact(ContactType.email, u.getAttribute("email"));

                return t;
            },
            "github",u->{
                Tuple2<_UsserBuilder,_MemberBuilder> t = Tuples.of(_Usser.builder(), _Member.builder());

                t.getT1()
                        .id(u.getName())
                        .name(u.getAttribute("login"))
                        .avatar(u.getAttribute("avatar_url"))
                        .provider("github");

                t.getT2()
                        .user(u.getName())
                        .name(u.getAttribute("login"))
                        .avatar(u.getAttribute("avatar_url"))
                        .contact(ContactType.email, u.getAttribute("email"));

                return t;
            },
            "discord",u->{
                Tuple2<_UsserBuilder,_MemberBuilder> t = Tuples.of(_Usser.builder(), _Member.builder());

                //https://cdn.discordapp.com/avatars/829534784791117824/283443a85bb545dbe3a5e247d7c878ff.jpg

                t.getT1()
                        .id(u.getAttribute("id"))
                        .name(u.getName())
                        .avatar("https://cdn.discordapp.com/avatars/" + u.getAttribute("id") + "/" +u.getAttribute("avatar") + ".jpg")
                        .provider("discord");

                t.getT2()
                        .user(u.getAttribute("id"))
                        .name(u.getName())
                        .avatar("https://cdn.discordapp.com/avatars/" + u.getAttribute("id") + "/" +u.getAttribute("avatar") + ".jpg")
                        .contact(ContactType.email, u.getAttribute("email"));

                return t;
            },
            "kakao",u->{
                Tuple2<_UsserBuilder,_MemberBuilder> t = Tuples.of(_Usser.builder(),_Member.builder());
                return t;
            },
            "naver",u->{
                Tuple2<_UsserBuilder,_MemberBuilder> t = Tuples.of(_Usser.builder(),_Member.builder());
                return t;
            }
    );

    private Tuple2<_Usser, _Member> o2u(String provider, OAuth2User oAuth2User,InetSocketAddress ip) {
        var t = mapper.get(provider).apply(oAuth2User);
        _Usser user = t.getT1().us_authority(UserAuthority.USER).ip(ip.getHostName()).build();
        _Member member = t.getT2().build();
        return Tuples.of(user,member);
    }

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        InetSocketAddress ip = webFilterExchange.getExchange().getRequest().getRemoteAddress();
        OAuth2AuthenticationToken authenticationResult = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        log.debug("RegistrationId ----->[{}]", authenticationResult.getAuthorizedClientRegistrationId());
        log.debug("Authentication getName -----> [{}]", authentication.getName());
        log.debug("OAuth2User getName -----> [{}]", oAuth2User.getName());

        oAuth2User.getAttributes().forEach((k,v) -> {
            log.debug("{} --------> [{}]", k,v);
        });

        var t = o2u(authenticationResult.getAuthorizedClientRegistrationId(),oAuth2User,ip);

        return template.exists(Query.query(Criteria.where("id").is(t.getT1().getId()).and("provider").is(t.getT1().getProvider())), _Usser.class)
                .flatMap(it->{
                    if(it) {
                        Update update = new Update().set("ip", t.getT1().getIp()).inc("loginCount", 1);
                        return template.findAndModify(Query.query(Criteria.where("id").is(t.getT1().getId())), update, options, _Usser.class)
                                .flatMap(u -> template.findById(u.getMember(), _Member.class).map(m -> JwtAuthentication.builder().principal(m).details(u).build()));
                    }

                    else {
                        long id = Snowflake_.generate(0);
                        t.getT1().setMember(id);
                        t.getT2().setId(id);
                        return template.insert(t.getT1()).flatMap(u -> template.insert(t.getT2()).map(m -> JwtAuthentication.builder().principal(m).details(u).build()));
                    }
                })
                .map(it -> {
                    try {
                        log.debug("JWT encode it: " + securityUtils.encode(it).getCredentials());

                        return UriComponentsBuilder.fromUriString("http://localhost:5173/#/" + securityUtils.encode(it).getCredentials()).build().toUri();
                    } catch (JOSEException e) {
                        log.error("jwt encode error -------->>{}",it);
                    }

                    return uri;
                })
                .flatMap(it -> redirectStrategy.sendRedirect(exchange, it));
    }
}
