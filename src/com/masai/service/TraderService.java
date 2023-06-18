package com.masai.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.masai.entities.Shares;
import com.masai.entities.Trader;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;

public interface TraderService {

	public boolean login(String email,String password, Map<String, Trader> traders) throws InvalidDetailsException;

	public void signUp(Trader trds, Map<String, Trader> traders) throws DuplicateDataException;

	public boolean buyProduct(int id, int qty, String email, Map<Integer, Shares> shares,
			Map<String, Trader> traders, List<Transaction> transactions)
			throws InvalidDetailsException, ProductException;

	public boolean addMoneyToWallet(double amount, String email, Map<String, Trader> traders);

	public double viewWalletBalance(String email, Map<String, Trader> traders);

	public Trader viewTraderDetails(String email, Map<String, Trader> traders);

	public List<Trader> viewAllTraders(Map<String, Trader> traders) throws ProductException;

}
