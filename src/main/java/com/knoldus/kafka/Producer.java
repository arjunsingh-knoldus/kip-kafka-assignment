package com.knoldus.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

import com.knoldus.models.User;

public class Producer {
    static String serverIpPort = "192.168.1.131:9092";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", serverIpPort);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.serialization.UserSerializer");
        
        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<>(properties);
        Random randGen = new Random(); 
        try {
            for(int i = 0; i < 1000; i++)
                kafkaProducer.send(new ProducerRecord<>("userTest", Integer.toString(i), new User("NaMe"+i, randGen.nextInt(100), "MCA")));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}