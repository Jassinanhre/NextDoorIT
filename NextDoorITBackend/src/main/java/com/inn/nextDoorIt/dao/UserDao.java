package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);
    User findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
}
