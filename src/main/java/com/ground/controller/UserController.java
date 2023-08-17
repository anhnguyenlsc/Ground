package com.ground.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground.data.models.documents._User;
import com.ground.data.models.documents._Usser;
import com.ground.data.repositories.UserRepository;
import com.ground.services.EmailVerificationService;
import com.ground.services.ResponseService;
import com.ground.services.UserService;
import com.ground.services.bot.components.TelegramCommandBot;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("api/authentication/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepo;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/signup")
    public Mono<ResponseEntity<ResponseService>> signUp(@RequestBody _Usser inputUser) throws JOSEException {
        return userService.saveUser(inputUser)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("/verification")
    public ResponseService verification(@RequestBody String token) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        ResponseService resObj = new ResponseService();
        try {
            if (token == null) {
                resObj.setStatus("failed");
                resObj.setMessage("Your verification token is invalid");
                return resObj;
            }
            else {
                if (currentTimestamp.after(emailVerificationService.expTimeInSec)) {
                    resObj.setStatus("failed");
                    resObj.setMessage("Your token has expired. Please try again.");
                    emailVerificationService.setExpTime(true);
                    return resObj;
                }

                else if (currentTimestamp.before(emailVerificationService.expTimeInSec)) {
                    resObj.setStatus("success");
                    resObj.setMessage("Your account is verified");
                    return resObj;
                }
            }
        }

        catch (Exception e) {
            resObj.setStatus("failed");
            resObj.setMessage("Error: " + e.getMessage());
            return resObj;
        }
        return resObj;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseService>> logIn(@RequestBody _Usser logInUser) throws JOSEException {
        return userService.logInUser(logInUser).map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}
