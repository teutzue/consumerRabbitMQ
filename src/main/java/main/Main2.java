package main;

import loanRequest.LoanRequest;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoanRequest lr = new LoanRequest("123456-6543", 1234567.00, "6", 0);
		System.out.println(lr.toJSON());
	}

}
