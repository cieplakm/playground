package com.mmc.playground.userprofile;

interface UserProfileRepository {

    UserProfile fetchById(String id);

    String create(UserProfile userProfile);
}
