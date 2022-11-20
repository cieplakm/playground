package com.mmc.playground.userprofile;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserProfileJpaRepository extends MongoRepository<UserProfileEntity, String> {

}
