package com.ground.support;

import com.ground.domain.enums.authentication.UserAuthority;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
@UtilityClass
public class SecurityUtils {

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
