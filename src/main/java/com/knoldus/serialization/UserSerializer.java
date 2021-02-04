package com.knoldus.serialization;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.models.User;

public class UserSerializer implements Serializer<User> {

    @Override
    public byte[] serialize(String arg0, User arg1) {
        byte[] returntVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            returntVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returntVal;
    }  
}