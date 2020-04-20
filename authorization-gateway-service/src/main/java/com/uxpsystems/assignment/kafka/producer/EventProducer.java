package com.uxpsystems.assignment.kafka.producer;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.uxpsystems.assignment.model.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventProducer {
	
	@Autowired
	private ReplyingKafkaTemplate<String, UserDTO,UserDTO> kafkaTemplate;
	
	@Value("${kafka.topic.request-topic}")
	private String requestTopic;
	
	@Value("${kafka.topic.requestreply-topic}")
	private String requestReplyTopic;
	
	public UserDTO publish(@RequestBody UserDTO user) throws InterruptedException, ExecutionException {
		log.info("Publishing user --> {} Operation --> {} : ",user.getUserName(),user.getAdditionalProperties().get("Operation"));
		ProducerRecord<String, UserDTO> record = new ProducerRecord<>(requestTopic, user);
		record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
		RequestReplyFuture<String, UserDTO, UserDTO> sendAndReceive = kafkaTemplate.sendAndReceive(record);
		SendResult<String, UserDTO> sendResult = sendAndReceive.getSendFuture().get();
		sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));
		ConsumerRecord<String, UserDTO> consumerRecord = sendAndReceive.get();
		log.info("Response form Consumer for user--> {} : ", user.getUserName());
		return consumerRecord.value();		
	}


}
