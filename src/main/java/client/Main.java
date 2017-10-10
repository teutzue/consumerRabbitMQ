package client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main 
{
	
	public static void main(String args[]) throws IOException, TimeoutException
	{
		LoanRequest lr = new LoanRequest("123456-6543", 1234567, "6");
		
		//start the consumer for the credit score
		GetCreditScore creditScore = new GetCreditScore();
		creditScore.receiveLoanRequest();
		
		//send lr to get credit score.
		LoanRequestSender loanRequestSender = new LoanRequestSender();
		loanRequestSender.sendLoanRequest(lr);
		
		
		CreditScore cs = new CreditScore("123456-6543");
		System.out.println(cs.getScore());
		//send credit score to get banks.
		RuleBaseClient client = new RuleBaseClient();
		String[] banks = client.getBanks(cs.getScore());
		for (String bank : banks) {
			//send message to each bank
			System.out.println(bank);
		}
		
		//client.getBanks(cs.getScore());
		//cs.getScore()
		//System.out.println(cs.getScore());
		
	}
}
