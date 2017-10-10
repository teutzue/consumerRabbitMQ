package client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class GetCreditScore {
	
	private final static String QUEUE_NAME = "loan_request";
	private final static String HOST = "localhost";
	
	public static void main(String[] args) throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for Loan Requests. To exit press CTRL+C");
		
		Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		        String loanRequest = new String(body, "UTF-8");
		        System.out.println(" [x] Received Loan Request: " + loanRequest + "'");
		      }
		    };
		    channel.basicConsume(QUEUE_NAME, true, consumer);
	}

}
