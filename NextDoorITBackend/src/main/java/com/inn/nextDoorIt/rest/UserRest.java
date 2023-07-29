package com.inn.nextDoorIt.rest;

import com.inn.nextDoorIt.utils.ApplicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {
    @RequestMapping(path = "/signup")
    public ResponseEntity<ApplicationResponse> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @RequestMapping(path = "/login")
    public ResponseEntity<ApplicationResponse> login(@RequestBody(required = true) Map<String, String> requestMap);

    @RequestMapping(path = "/logout")
    public ResponseEntity<ApplicationResponse> logout();
}
