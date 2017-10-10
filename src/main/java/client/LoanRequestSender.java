package client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class LoanRequestSender 
{

	private final static String QUEUE_NAME = "loan_request";
	private final static String HOST = "localhost";
	
	public static void main(String[] args) throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = "Loan request sent";
		
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		
        System.out.println(" [x] Sent '" + message + "'");
        
        channel.close();
        connection.close();
	}
}
