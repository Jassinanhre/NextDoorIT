package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.JWT.JwtUtil;
import com.inn.nextDoorIt.POJO.User;
import com.inn.nextDoorIt.constants.NextDoorItConstants;
import com.inn.nextDoorIt.dao.UserDao;
import com.inn.nextDoorIt.service.UserService;
import com.inn.nextDoorIt.utils.NextDoorItUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside SignUp{}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));// save the data into the database
                    return NextDoorItUtils.getResponseEntity("User Successfully Registered", HttpStatus.OK);
                } else {
                    return NextDoorItUtils.getResponseEntity("Email already exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                return NextDoorItUtils.getResponseEntity(NextDoorItConstants.invalid_data, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return NextDoorItUtils.getResponseEntity(NextDoorItConstants.wrong, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("LOGIN REQUEST RECEIVED");
        try {
            if (validateLoginRequest(requestMap)) {
                User user = userDao.findByEmailAndPassword(requestMap.get("email"), requestMap.get("password"));
                if (Objects.isNull(user)) {
                    return NextDoorItUtils.getTokenResponseEntity("UNAUTHORIZED_USER", HttpStatus.UNAUTHORIZED);
                } else {
                    // WRITE TOKEN HERE TO GENERATE TOKEN AND RETURN IT AFTER SUCCESSFUL LOGIN
                    String accessToken = jwtUtil.generateToken(requestMap.get("username"), "user");
                    return NextDoorItUtils.getTokenResponseEntity(accessToken, HttpStatus.OK);
                }
            } else {
                return NextDoorItUtils.getResponseEntity(NextDoorItConstants.invalid_data, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return NextDoorItUtils.getResponseEntity(NextDoorItConstants.wrong, HttpStatus.INTERNAL_SERVER_ERROR);
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
