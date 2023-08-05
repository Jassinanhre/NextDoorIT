package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.POJO.UserLoginResponse;
import com.inn.nextDoorIt.entity.User;

import java.util.Map;

public interface UserService {

    User signUp(Map<String, String> requestMap);

    UserLoginResponse login(Map<String, String> requestMap);
}
