package com.masai.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.masai.entities.Broker;
import com.masai.entities.Shares;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;

public interface BrokerService {

	public boolean login(String email,String password, Map<String, Broker> brokers) throws InvalidDetailsException;

	public void signUp(Broker trds, Map<String, Broker> brokers) throws DuplicateDataException;

	public boolean buyProduct(int id, int qty, String email, Map<Integer, Shares> shares,
			Map<String, Broker> brokers, List<Transaction> transactions)
			throws InvalidDetailsException, ProductException;

	public boolean addMoneyToWallet(double amount, String email, Map<String, Broker> brokers);

	public double viewWalletBalance(String email, Map<String, Broker> brokers);

	public Broker viewTraderDetails(String email, Map<String, Broker> brokers);

	public List<Broker> viewAllTraders(Map<String, Broker> brokers) throws ProductException;

}
