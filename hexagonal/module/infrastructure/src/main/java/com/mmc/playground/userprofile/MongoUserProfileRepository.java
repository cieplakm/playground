package com.mmc.playground.userprofile;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MongoUserProfileRepository implements UserProfileRepository {

    private final MongoUserProfileJpaRepository repo;

    @Override
    public UserProfile fetchById(String id) {

        return repo.findById(id).map(entity -> new UserProfile(entity.getName(), entity.getSurname(), entity.getAddress())).orElseThrow();
    }

    @Override
    public String create(UserProfile userProfile) {
        UserProfileEntity save = repo.save(new UserProfileEntity(UUID.randomUUID().toString(), userProfile.getName(), userProfile.getSurname(), userProfile.getAddress()));
        return save.getId();
    }
}
