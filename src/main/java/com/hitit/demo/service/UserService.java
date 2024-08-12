package com.hitit.demo.service;

import com.hitit.demo.data.entity.User;
import com.hitit.demo.data.response.UserResponse;
import com.hitit.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hitit.demo.repository.UserRepository;

@Service
public class UserService {

    private final GitHubClient gitHubClient;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(GitHubClient gitHubClient,
                       UserRepository userRepository,
                       UserMapper userMapper) {
        this.gitHubClient = gitHubClient;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User fetchUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            UserResponse userResponse = gitHubClient.fetchUserDetails(username);
            if (userResponse != null) {
                user = userMapper.mapToUser(userResponse);
                userRepository.save(user);
            }
        }
        return user;
    }
}
