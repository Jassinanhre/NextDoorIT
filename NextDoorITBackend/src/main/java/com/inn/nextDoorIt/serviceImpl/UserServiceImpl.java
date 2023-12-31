package com.inn.nextDoorIt.serviceImpl;

import com.inn.nextDoorIt.JWT.JwtUtil;
import com.inn.nextDoorIt.POJO.UserLoginResponse;
import com.inn.nextDoorIt.entity.Cart;
import com.inn.nextDoorIt.entity.User;
import com.inn.nextDoorIt.dao.CartDao;
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
    private CartDao cartDao;
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
                if (savedUser != null) {
                    createCartForUser(savedUser);
                }
                return savedUser;
            } else {
                throw new ApplicationException("The user is already registered", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ApplicationException("The signup request contains invalid data", HttpStatus.BAD_REQUEST);
        }
    }

    private void createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setProducts(null);
        cart.setUsername(user.getEmail());
        cart.setUserId(user.getId());
        Cart savedCartRecord = cartDao.save(cart);
        if (Objects.isNull(savedCartRecord)) {
            throw new ApplicationException("Unable to create cart for user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public UserLoginResponse login(Map<String, String> requestMap) {
        log.info("LOGIN REQUEST RECEIVED");
        if (validateLoginRequest(requestMap)) {
            User user = userDao.findByEmailAndPassword(requestMap.get("email"), requestMap.get("password"));
            if (Objects.isNull(user)) {
                throw new ApplicationException("User with given credentials does not exists", HttpStatus.NOT_FOUND);
            } else {
                // WRITE TOKEN HERE TO GENERATE TOKEN AND RETURN IT AFTER SUCCESSFUL LOGIN
                String accessToken = jwtUtil.generateToken(requestMap.get("email"), "user");
                UserLoginResponse loginResponse = new UserLoginResponse();
                loginResponse.setAccessToken(accessToken);
                loginResponse.setUserId(user.getId());
                loginResponse.setUserName(user.getName());
                return loginResponse;
            }
        } else {
            throw new ApplicationException("Invalid login request", HttpStatus.BAD_REQUEST);
        }


    }


    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if ((requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password")) && (!((String) requestMap.get("name")).isBlank() && !((String) requestMap.get("contactNumber")).isBlank() && !((String) requestMap.get("email")).isBlank() && !((String) requestMap.get("password")).isBlank())) {
            return true;
        }
        return false;
    }

    private boolean validateLoginRequest(Map<String, String> loginRequest) {
        return loginRequest.containsKey("email") && loginRequest.containsKey("password") && !((String) loginRequest.get("email")).isBlank() && !((String) loginRequest.get("password")).isBlank();
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
