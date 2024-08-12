package com.hitit.demo.mapper;

import com.hitit.demo.data.entity.User;
import com.hitit.demo.data.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserResponse response) {
        User user = new User();
        user.setUsername(response.getLogin());
        user.setLocation(response.getLocation());
        user.setCompany(response.getCompany());
        return user;
    }
}
