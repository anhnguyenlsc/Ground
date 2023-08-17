package com.ground.support;


import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Slf4j
@Component
public class JwtWebFilter extends AuthenticationWebFilter {

    @Autowired
    SecurityUtils securityUtils;

    private static final ReactiveAuthenticationManager authenticationManager = authentication -> {
//        log.debug("jwtAuthenticationManager------------------------------>>{}", authentication);
        return Mono.just(authentication);
    };

    private final ServerAuthenticationConverter serverAuthenticationConverter = exchange -> {

        String _token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (_token == null) return Mono.empty();
        try {
            var authentication = securityUtils.decode(_token);
            return Mono.just(authentication);
        }

        catch (ParseException | JOSEException e) {
            log.error("JwtCoder parsing error ...............");
        }
        return Mono.empty();
    };

    public JwtWebFilter() throws JOSEException, ParseException {
        super(authenticationManager);
        setServerAuthenticationConverter(serverAuthenticationConverter);
    }
}
