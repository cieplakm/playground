package com.mmc.playground.userprofile;

import com.mmc.playground.Application;
import com.mmc.playground.ContainerizedMongoDbTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {Application.class})
public class UserProfileDbIT extends ContainerizedMongoDbTest {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Test
    public void should() {
        String s = userProfileRepository.create(new UserProfile("Mati", "C", "AK50"));

        UserProfile userProfile = userProfileRepository.fetchById(s);

        Assertions.assertEquals(userProfile.getName(), "Mati");
    }
}
