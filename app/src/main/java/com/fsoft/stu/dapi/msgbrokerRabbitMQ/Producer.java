package com.fsoft.stu.dapi.msgbrokerRabbitMQ;

import com.fsoft.stu.dapi.dto.DemoDto;
import com.fsoft.stu.dapi.msgprovider.Provider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer extends EndPoint implements Provider {
    public Producer(String endPointName) throws IOException, TimeoutException {
        super(endPointName);
    }

    public void sendMessage(DemoDto object) throws IOException {
        String json = object.toString();
        byte[] body = json.getBytes();
        channel.basicPublish("", queueName, null, body);
    }
}

