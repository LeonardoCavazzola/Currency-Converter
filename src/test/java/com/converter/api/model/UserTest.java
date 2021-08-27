package com.converter.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void mustEncryptThePassword(){

        User user = new User("a@a.com", "12345678");
        Assertions.assertNotEquals("12345678", user.getPassword());
    }

}
