package com.inn.nextDoorIt.POJO;

import lombok.Data;

@Data
public class UserLoginResponse {
    private int userId;
    private String userName;
    private String accessToken;
}
