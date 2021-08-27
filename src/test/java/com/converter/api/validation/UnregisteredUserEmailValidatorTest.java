package com.converter.api.validation;

import com.converter.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
class UnregisteredUserEmailValidatorTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void isValid() {
        Assertions.assertFalse(new UnregisteredUserEmailValidator(userRepository).isValid("email@email.com", null));
    }
}
