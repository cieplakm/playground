package com.mmc.playground.userprofile;

import lombok.Value;
import org.bson.codecs.pojo.annotations.BsonId;

@Value
public class UserProfileEntity {

    @BsonId
    String id;
    String name;
    String surname;
    String address;
}
