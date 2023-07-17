package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.JWT.JwtUtil;
import com.inn.nextDoorIt.POJO.User;
import com.inn.nextDoorIt.dao.UserDao;
import com.inn.nextDoorIt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public User signUp(Map<String, String> requestMap) {
        log.info("Inside SignUp{}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    User savedUser = userDao.save(getUserFromMap(requestMap));// save the data into the database
                    return savedUser;
                } else {
                    throw new RuntimeException("USER ALREADY EXISTS");
                }
            } else {
                throw new RuntimeException("INVALID SIGNUP FIELDS");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("SOMETHING WENT WRONG");
        }
    }

    @Override
    public String login(Map<String, String> requestMap) {
        log.info("LOGIN REQUEST RECEIVED");
        try {
            if (validateLoginRequest(requestMap)) {
                User user = userDao.findByEmailAndPassword(requestMap.get("email"), requestMap.get("password"));
                if (Objects.isNull(user)) {
                    throw new RuntimeException("NO USER FOUND");
                } else {
                    // WRITE TOKEN HERE TO GENERATE TOKEN AND RETURN IT AFTER SUCCESSFUL LOGIN
                    String accessToken = jwtUtil.generateToken(requestMap.get("username"), "user");
                    return accessToken;
                }
            } else {
                throw new RuntimeException("NOT A VALID USER");
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("something went wrong");
        }

    }


    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private boolean validateLoginRequest(Map<String, String> loginRequest) {
        return loginRequest.containsKey("email") && loginRequest.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
