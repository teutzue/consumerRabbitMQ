package creditScore;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class CreditScoreSender {
	
	private final static String QUEUE_NAME = "credit_score";
	private final static String HOST = "localhost";
	private final static String CREDIT_SCORE = "";
	
	public void sendCreditScore(String creditScore) throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = creditScore;
		
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		
        System.out.println(" [x] Sent credit score: '" + message + "'");
        
        channel.close();
        connection.close();
	}

}
