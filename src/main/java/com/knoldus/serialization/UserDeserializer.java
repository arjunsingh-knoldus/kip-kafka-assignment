package com.knoldus.serialization;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.models.User;

public class UserDeserializer implements Deserializer<User> {
  
    @Override
    public User deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(arg1, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}