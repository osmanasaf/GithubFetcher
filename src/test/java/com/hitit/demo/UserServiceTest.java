package com.hitit.demo;

import com.hitit.demo.data.entity.User;
import com.hitit.demo.data.response.UserResponse;
import com.hitit.demo.mapper.UserMapper;
import com.hitit.demo.repository.UserRepository;
import com.hitit.demo.service.GitHubClient;
import com.hitit.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchUser_WhenUserExistsInDB() {
        User user = new User();
        user.setUsername("user1");

        when(userRepository.findByUsername("user1")).thenReturn(user);

        User fetchedUser = userService.fetchUser("user1");

        assertEquals("user1", fetchedUser.getUsername());
        verify(userRepository, times(1)).findByUsername("user1");
    }

    @Test
    void testFetchUser_WhenUserDoesNotExistInDB() {
        UserResponse userResponse = new UserResponse();
        userResponse.setLogin("user1");

        User user = new User();
        user.setUsername("user1");

        when(userRepository.findByUsername("user1")).thenReturn(null);
        when(gitHubClient.fetchUserDetails("user1")).thenReturn(userResponse);
        when(userMapper.mapToUser(userResponse)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User fetchedUser = userService.fetchUser("user1");

        assertEquals("user1", fetchedUser.getUsername());
        verify(userRepository, times(1)).findByUsername("user1");
        verify(gitHubClient, times(1)).fetchUserDetails("user1");
        verify(userMapper, times(1)).mapToUser(userResponse);
        verify(userRepository, times(1)).save(user);
    }
}
