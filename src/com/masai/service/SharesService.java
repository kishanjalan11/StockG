package com.masai.service;

import java.util.List;
import java.util.Map;

import com.masai.entities.Trader;
import com.masai.entities.Shares;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;

public interface SharesService {

	public String addShare(Shares shr, Map<Integer, Shares> shares);

	public void viewAllShares(Map<Integer, Shares> shares) throws ProductException;

	public void deleteShare(int id, Map<Integer, Shares> shares) throws ProductException;

	public String updateShare(int id, Shares shr, Map<Integer, Shares> shares) throws ProductException;

	
	
}
