package com.ground.services;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseService {
    private String status;
    private String message;
    private Object payload;
    private String token;
}
