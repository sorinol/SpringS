package com.marsor.service;

import com.marsor.entity.UserModel;
import com.marsor.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<UserModel> message;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addUserTest() {

        UserModel userModel = new UserModel();
        userModel.setUsername("iglu");
        userModel.setPassword("iglu");
        userModel.setRole("user");

        userService.addUser(userModel);

        // Mockito.verify(userRepository, Mockito.times(1)).save(userModel);
        Mockito.verify(userRepository, Mockito.times(1)).save(message.capture());

        UserModel userCapturat = message.getValue();
        assertNotEquals("iglu", userCapturat.getPassword());

    }
}
