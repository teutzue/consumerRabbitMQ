package client;

import com.rabbitmq.client.ConnectionFactory;

public class LoanRequest 
{
	private String ssn;
	private double loanAmount;
	private String loanDuration;

	public LoanRequest(String ssn, double loanAmount, String loanDuration) 
	{
		this.ssn = ssn;
		this.loanAmount = loanAmount;
		this.loanDuration = loanDuration;
	}
	
	public double getLoanAmount() 
	{
		 return loanAmount;
	}
	
	public String getSsn() 
	{
		return ssn;
	}
	
	public String getLoanDuration() 
	{
		return loanDuration;
	}
	
}
