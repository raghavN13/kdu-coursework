package com.caching;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CachingApplicationTest {

    @Test
    void contextLoads() {
        // This test method can be empty; it's just used to ensure that the application context loads successfully
    }
}