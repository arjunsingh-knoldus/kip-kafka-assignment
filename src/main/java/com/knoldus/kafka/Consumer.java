package com.knoldus.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.knoldus.models.User;

public class Consumer {
    static String serverIpPort = "192.168.1.131:9092";
    public static void main(String[] args) {
       ConsumerListener c = new ConsumerListener();
        Thread thread = new Thread(c);
        thread.start();
    }

    
    public static void consumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", serverIpPort);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.knoldus.serialization.UserDeserializer");
        properties.put("group.id", "test-group-0");
        
        KafkaConsumer<String, User> kafkaConsumer = new KafkaConsumer<>(properties);
        List<String> topics = new ArrayList<String>();
        topics.add("userTest");
        kafkaConsumer.subscribe(topics);
        try {
            while (true){
                ConsumerRecords<String, User> records = kafkaConsumer.poll(Duration.ofMillis(1000));
                try(
                    PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                            new FileWriter("kafka_output.txt", true)
                        )
                    )
                ) {
                    for (ConsumerRecord<String, User> record: records){
                        User userObj = record.value();
                        out.println(String.format("{\"id\":\"%s\",\"name\":\"%s\",\"age\":\"%d\",\"course\":\"%s\"}", record.key(), userObj.getName(), userObj.getAge(), userObj.getCourse()));
                    }
                } catch (IOException e) {
                    System.err.println(e);
                }

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.close();
        }
    }
}

class ConsumerListener implements Runnable {    
    @Override
    public void run() {
    Consumer.consumer();
    }
}