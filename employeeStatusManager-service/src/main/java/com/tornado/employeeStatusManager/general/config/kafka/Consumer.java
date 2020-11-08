package com.tornado.employeeStatusManager.general.config.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tornado.employeeStatusManager.dto.Message;
import com.tornado.employeeStatusManager.service.employee.EmployeeService;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired EmployeeService employeeService;

	@KafkaListener(topics = "EmployeeStatus", groupId = "group_id")
	public void approvedStatus(String messageJson) throws IOException {
		logger.info(String.format("#### -> Consumed message -> %s", messageJson));
		Message message = new Gson().fromJson(messageJson, Message.class);
		employeeService.updateDateBasedOnStatus(message.getId(),message.getEmployeeStates());
	}

}
