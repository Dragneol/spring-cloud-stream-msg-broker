package com.fsoft.stu.dapi.msgbrokerRabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.stu.dapi.DemoApplication;
import com.fsoft.stu.dapi.dto.DemoDto;
import com.rabbitmq.client.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Subscriber implements Consumer, CommandLineRunner {


    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }


    /**
     * Called when new message is available.
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String msg = new String(body);
        System.out.println("Message " + mapper.readValue(msg, DemoDto.class) + " received.");
    }

    public void handleCancel(String consumerTag) {
    }

    public void handleCancelOk(String consumerTag) {
    }

    public void handleRecoverOk(String consumerTag) {
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            //start consuming messages. Auto acknowledge messages.
            EndPoint endPoint = new EndPoint(DemoApplication.queueName);
            endPoint.getConnection();
            endPoint.getChannel().basicConsume(endPoint.getQueueName(), true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
