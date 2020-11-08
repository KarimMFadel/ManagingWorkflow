package com.tornado.employeeStatusManager.general.config.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tornado.employeeStatusManager.dto.EmployeeStates;
import com.tornado.employeeStatusManager.dto.Message;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "EmployeeStatus";

    @Autowired private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(EmployeeStates employeeStates,Long id) {
        String message = new Gson().toJson(new Message(id,employeeStates));
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
