package com.converter.api.service;

import com.converter.api.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class DevUserServiceTest {

    @Autowired
    private DevUserService devUserService;

    @Test
    void getLoggedUser() {
        User loggedUser = devUserService.getLoggedUser();
        Assertions.assertEquals(Long.valueOf("1"), loggedUser.getId());
    }
}
