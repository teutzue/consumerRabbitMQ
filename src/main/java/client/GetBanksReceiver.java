package client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class GetBanksReceiver {
	
	private final static String QUEUE_NAME = "credit_score";
	private final static String HOST = "localhost";
	private static String CREDIT_SCORE = "";
	
	public String receiveCreditScore() throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for Credit Score. To exit press CTRL+C");
		
		Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		        String creditScore = new String(body, "UTF-8");
		        CREDIT_SCORE = creditScore;
		        System.out.println(" [x] Received Credit Score: " + creditScore + "'");
		      }
		    };
		    channel.basicConsume(QUEUE_NAME, true, consumer);
		    return CREDIT_SCORE;
	}

}
