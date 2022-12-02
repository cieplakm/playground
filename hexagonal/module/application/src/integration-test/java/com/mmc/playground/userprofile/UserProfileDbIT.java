package com.mmc.playground.userprofile;

import com.mmc.playground.Application;
import com.mmc.playground.ContainerizedMongoDbTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ContextConfiguration(classes = {Application.class})
@TestPropertySource(value = "classpath:application-test.properties")
public class UserProfileDbIT extends ContainerizedMongoDbTest {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Value("${test.name}")
    String testName;

    @Test
    public void should() {
        String s = userProfileRepository.create(new UserProfile(testName, "C", "AK50"));

        UserProfile userProfile = userProfileRepository.fetchById(s);

        Assertions.assertEquals(userProfile.getName(), testName);
    }
}
