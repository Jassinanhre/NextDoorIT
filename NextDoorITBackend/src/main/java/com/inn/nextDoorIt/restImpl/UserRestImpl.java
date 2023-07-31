package com.inn.nextDoorIt.restImpl;

import com.inn.nextDoorIt.rest.UserRest;
import com.inn.nextDoorIt.service.UserService;
import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<ApplicationResponse> signUp(Map<String, String> requestMap) {

        return ResponseEntity.ok(new ApplicationResponse(userService.signUp(requestMap), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApplicationResponse> login(Map<String, String> requestMap) {
        return ResponseEntity.ok(new ApplicationResponse(userService.login(requestMap), HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApplicationResponse> logout() {
        return ResponseEntity.ok(new ApplicationResponse("User successfully logged out", HttpStatus.OK.value()));
    }
}
