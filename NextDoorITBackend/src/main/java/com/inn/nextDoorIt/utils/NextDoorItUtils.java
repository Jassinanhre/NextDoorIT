package com.inn.nextDoorIt.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NextDoorItUtils {
    private NextDoorItUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\" " + responseMessage + "\"}", httpStatus);
    }

    public static ResponseEntity<String> getTokenResponseEntity(String tokenResponse, HttpStatus httpStatus) {
        if (tokenResponse.equals("UNAUTHORIZED_USER")) {
            return new ResponseEntity<String>("{\"message\":\" " + tokenResponse + "\"}", httpStatus);
        }
        return new ResponseEntity<>("{\"ACCESS TOKEN \":\" " + tokenResponse + "\"}", httpStatus);
    }


}
