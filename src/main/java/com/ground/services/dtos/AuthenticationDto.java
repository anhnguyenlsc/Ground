package com.ground.services.dtos;

import com.ground.data.models.documents._Member;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class AuthenticationDto {
    //Send the JWT and Member principal to client
    String token;
    _Member principal;
}
