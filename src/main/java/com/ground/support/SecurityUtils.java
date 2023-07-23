package com.ground.support;

import com.ground.domain.enums.authentication.UserAuthority;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
@Component
@RequiredArgsConstructor
public class SecurityUtils {

  //@Value("${spring.security.jwt.secret}")
  private String jwtSecret;

  private ECKey eCKey;
  private ECDSAVerifier verifier;

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    try {
      eCKey = new ECKeyGenerator(Curve.P_256).keyUse(KeyUse.SIGNATURE).keyID(jwtSecret).generate();
      verifier = new ECDSAVerifier(eCKey.toPublicJWK());
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }
  }


  public String encode(UsernamePasswordAuthenticationToken authentication) throws JOSEException {

    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .claim("principal", authentication.getPrincipal())
        .claim("credentials", authentication.getCredentials())
        .claim("authorities", authentication.getAuthorities())
        .build();

    // Create JWT for ES256K alg
    SignedJWT jwt = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.ES256).keyID(eCKey.getKeyID()).build(), claimsSet);

    // Sign with private EC key
    jwt.sign(new ECDSASigner(eCKey));

    return jwt.serialize();

  }

  public UsernamePasswordAuthenticationToken decode(String token) throws ParseException, JOSEException {
    SignedJWT jwt = SignedJWT.parse(token);
    jwt.verify(verifier);
    JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
    var authenticationToken =  new UsernamePasswordAuthenticationToken(
        claimsSet.getStringClaim("principal"),
        claimsSet.getStringClaim("credentials"),
        claimsSet.getStringListClaim("authorities").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
    );
    authenticationToken.setAuthenticated(true);
    return authenticationToken;
  }


  /**
   * Get the login of the current user.
   *
   * @return the login of the current user.
   */
  public static Mono<String> getCurrentUserLogin() {
    return ReactiveSecurityContextHolder.getContext()
    .map(SecurityContext::getAuthentication)
    .filter(Authentication::isAuthenticated)
    .map(Authentication::getPrincipal)
    .map(UserDetails.class::cast)
    .map(UserDetails::getUsername);
  }

  /**
   * Check if a user is authenticated.
   *
   * @return true if the user is authenticated, false otherwise.
   */
  public static Mono<Boolean> isAuthenticated() {
    return ReactiveSecurityContextHolder.getContext()
    .map(SecurityContext::getAuthentication)
    .filter(Authentication::isAuthenticated)
    .map(authentication -> getAuthorities(authentication).noneMatch(UserAuthority.ANONYMOUS::equals))
    .switchIfEmpty(Mono.just(false));
  }

  /**
   * Checks if the current user has any of the authorities.
   *
   * @param authorities the authorities to check.
   * @return true if the current user has any of the authorities, false otherwise.
   */
  public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (
        authentication != null && getAuthorities(authentication).anyMatch(authority -> Arrays.asList(authorities).contains(authority))
    );
  }

  /**
   * Checks if the current user has none of the authorities.
   *
   * @param authorities the authorities to check.
   * @return true if the current user has none of the authorities, false otherwise.
   */
  public static boolean hasCurrentUserNoneOfAuthorities(String... authorities) {
    return !hasCurrentUserAnyOfAuthorities(authorities);
  }

  /**
   * Checks if the current user has a specific authority.
   *
   * @param authority the authority to check.
   * @return true if the current user has the authority, false otherwise.
   */
  public static boolean hasCurrentUserThisAuthority(String authority) {
    return hasCurrentUserAnyOfAuthorities(authority);
  }

  private static Stream<String> getAuthorities(Authentication authentication) {
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
  }
}
