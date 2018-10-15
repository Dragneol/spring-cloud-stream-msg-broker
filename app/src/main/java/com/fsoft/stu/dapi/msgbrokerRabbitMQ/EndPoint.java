package com.fsoft.stu.dapi.msgbrokerRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EndPoint {

    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public EndPoint(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        getConnection();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void getConnection() throws IOException, TimeoutException {
        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost("localhost");

        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
        channel.queueDeclare(queueName, false, false, false, null);
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
