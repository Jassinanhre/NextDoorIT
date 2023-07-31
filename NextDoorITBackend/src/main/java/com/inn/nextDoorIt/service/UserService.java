package com.inn.nextDoorIt.service;

import com.inn.nextDoorIt.entity.User;

import java.util.Map;

public interface UserService {

    User signUp(Map<String, String> requestMap);

    String login(Map<String, String> requestMap);
}
