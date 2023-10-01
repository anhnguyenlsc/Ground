package com.ground.services;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ground.data.models.documents._User;
import com.ground.data.models.documents._Usser;
import com.ground.data.repositories.UserRepository;
import com.ground.domain.enums.authentication.UserAuthority;
import com.ground.support.SecurityUtils;
import com.ground.usage.cases.port.api.user.profile.EditProfile;
import com.ground.usage.values.base.TransferOut;
import com.ground.usage.values.in.EditableProfileIN;
import com.ground.usage.values.out.UserOUT;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements EditProfile {

  private final ReactiveMongoTemplate mongoOps;
  private final BCryptPasswordEncoder passwordEncoder;
  private final EmailVerificationService emailService;
  private final SecurityUtils secUtils;

  private final UserRepository userRepo;
  public Mono<ResponseService> saveUser(_Usser inputUser) throws JOSEException{
    ResponseService resObj = new ResponseService();
    Mono<_Usser> _optUser = userRepo.findByEmail(inputUser.getEmail());

    return _optUser
            .flatMap(user -> {
              resObj.setStatus("failed");
              resObj.setMessage("Email address " + inputUser.getEmail() + " is already existed");
              resObj.setPayload(null);
              return Mono.just(resObj);
            })
            .switchIfEmpty(
                    Mono.defer(() -> {
                        // Send email verification
                        emailService.emailVerification(inputUser.getEmail());
                        inputUser.setUs_authorities(List.of(UserAuthority.USER));
                        inputUser.setPassword(passwordEncoder.encode(inputUser.getPassword()));
                        String token = null;
                        try {
                            token = secUtils.generateToken(inputUser.getEmail());
                        } catch (JOSEException e) {
                            throw new RuntimeException(e);
                        }
                        String finalToken = token;
                        return mongoOps.save(inputUser)
                              .map(user -> {
                                resObj.setPayload(user);
                                resObj.setStatus("success");
                                resObj.setMessage("success");
                                resObj.setToken(finalToken);
                                return resObj;
                              });
                    })
            );
  }

  public Mono<ResponseService> logInUser(_Usser logInUser) throws JOSEException {
    ResponseService resObj = new ResponseService();
    String token = secUtils.generateToken(logInUser.getEmail());
    Mono<_Usser> _optUser = userRepo.findByEmail(logInUser.getEmail());
    return _optUser
            .flatMap(userLogin -> {
                resObj.setStatus("success");
                resObj.setMessage("Login in success");
                resObj.setPayload(userLogin);
                resObj.setToken(token);
                return Mono.just(resObj);
            })
            .switchIfEmpty(
                    Mono.defer(() -> {
                        resObj.setStatus("failed");
                        resObj.setMessage("Email address " + logInUser.getEmail() + "does not exist");
                        resObj.setPayload(null);
                        return Mono.just(resObj);
                    })
            );
  }

  public Mono<ResponseService> viewProfileUser(String email) {
      ResponseService resObj = new ResponseService();
      Mono<_Usser> _optUser = userRepo.findByEmail(email);

      return _optUser.flatMap(viewPro -> {
          resObj.setStatus("success");
          resObj.setMessage("User found");
          resObj.setPayload(viewPro);
          return Mono.just(resObj);
      })
      .switchIfEmpty(
              Mono.defer(() -> {
                  resObj.setStatus("failed");
                  resObj.setMessage("Email address " + email + "does not exist");
                  resObj.setPayload(null);
                  return Mono.just(resObj);
              })
      );
  }
  @Override
  @PreAuthorize("hasRole('USER')")
  public Mono<TransferOut<UserOUT>> viewProfile() {
    return null;
  }

  @Override
  public Mono<TransferOut<UserOUT>> viewProfile(String id) {
    return null;
  }

  @Override
  public Mono<TransferOut<UserOUT>> editProfile(EditableProfileIN editableProfile) {
    return null;
  }
}
