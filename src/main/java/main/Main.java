package main;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import banks.GetBanksReceiver;
import creditScore.CreditScoreSender;
import creditScore.GetCreditScore;
import loanRequest.LoanRequest;
import loanRequest.LoanRequestSender;
import util.CreditScore;
import util.RuleBaseClient;

public class Main 
{
	
	public static void main(String args[]) throws IOException, TimeoutException
	{
		//initialize receivers
		
		//start the consumer to receive loan request for the 'GetCreditScore'
		GetCreditScore creditScore = new GetCreditScore();
		//Start the consumer to receive the CreditScore in 'GetBanks'
		GetBanksReceiver getBanksReceiver = new GetBanksReceiver();
		
		LoanRequest lr = createLoanRequest("123456-6543", 1234567.00, "6", 0);
		
		//send lr to get credit score.
		LoanRequestSender loanRequestSender = new LoanRequestSender();
		loanRequestSender.sendLoanRequest(lr);
		
		String loanRequestResult = creditScore.receiveLoanRequest();
		System.out.println(loanRequestResult);
		//Param from CreditScore must be taken from the loanRequestResult. @TODO
		CreditScore cs = new CreditScore("123456-6543");
		//System.out.println("Credit score: " + cs.getScore());
		
		
		String creditScorefromGetCreditScore = getBanksReceiver.receiveCreditScore();
		
		
		//send credit score from 'GetCreditScore' to 'GetBanks'
		CreditScoreSender creditScoreSender = new CreditScoreSender();
		creditScoreSender.sendCreditScore(String.valueOf(cs.getScore()));
		
		
		RuleBaseClient client = new RuleBaseClient();
		String[] banks = client.getBanks(cs.getScore());
		for (String bank : banks) {
			//send message to each bank
			//construct the message according to each bank's format
			System.out.println(bank);
		}
		
		//client.getBanks(cs.getScore());
		//cs.getScore()
		//System.out.println(cs.getScore());
		
	}
	
	public static LoanRequest createLoanRequest(String ssn, Double loanAmount, String loanDuration, int creditScore) {
		return new LoanRequest(ssn, loanAmount, loanDuration, creditScore);
	}

}
