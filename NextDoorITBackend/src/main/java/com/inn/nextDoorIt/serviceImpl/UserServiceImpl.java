package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.JWT.JwtUtil;
import com.inn.nextDoorIt.POJO.User;
import com.inn.nextDoorIt.dao.UserDao;
import com.inn.nextDoorIt.exception.ApplicationException;
import com.inn.nextDoorIt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        if (validateSignUpMap(requestMap)) {
            User user = userDao.findByEmailId(requestMap.get("email"));
            if (Objects.isNull(user)) {
                User savedUser = userDao.save(getUserFromMap(requestMap));// save the data into the database
                return savedUser;
            } else {
                throw new ApplicationException("The user is already registered", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ApplicationException("The signup request contains invalid data", HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public String login(Map<String, String> requestMap) {
        log.info("LOGIN REQUEST RECEIVED");
            if (validateLoginRequest(requestMap)) {
                User user = userDao.findByEmailAndPassword(requestMap.get("email"), requestMap.get("password"));
                if (Objects.isNull(user)) {
                    throw new ApplicationException("User with given credentials does not exists", HttpStatus.NOT_FOUND);
                } else {
                    // WRITE TOKEN HERE TO GENERATE TOKEN AND RETURN IT AFTER SUCCESSFUL LOGIN
                    String accessToken = jwtUtil.generateToken(requestMap.get("username"), "user");
                    return accessToken;
                }
            } else {
                throw new ApplicationException("Invalid login request", HttpStatus.BAD_REQUEST);
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
