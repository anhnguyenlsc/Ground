package com.ground.support;


import com.ground.data.models.documents._Member;
import com.ground.data.models.documents._User;
import com.ground.data.models.documents._Usser;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@Accessors(chain = false)
public class JwtAuthentication implements Authentication {

    private static final long serialVersionUID = 1L;

    //모든 정보는 member로 관리된다. - All information is managed as a member.
    _Member principal;

    //형식적 위치 - Formal position
    _Usser details;

    //jwt token
    String credentials;

    @Builder.Default
    private boolean authenticated = false;

    @Override
    public String getName() {
        return principal.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return details.getUs_authorities();
    }

}
