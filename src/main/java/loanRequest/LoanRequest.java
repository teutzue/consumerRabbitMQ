package loanRequest;

import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;

public class LoanRequest 
{
	private String ssn;
	private double loanAmount;
	private String loanDuration;
	private int creditScore;

	public LoanRequest(String ssn, double loanAmount, String loanDuration, int creditScore) 
	{
		this.ssn = ssn;
		this.loanAmount = loanAmount;
		this.loanDuration = loanDuration;
		this.creditScore = creditScore;
	}
	
	public int getCreditScore() {
		return creditScore;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ssn: " + ssn + "; LoanAmount: " + loanAmount + "; Loan Durataion: " + loanDuration + "; Credit Score: " + creditScore;
	}
	
	public String toJSON() {
		// TODO Auto-generated method stub
		Gson g = new Gson();
		return g.toJson(this);
	}
	
}
