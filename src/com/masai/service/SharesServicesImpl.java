package com.masai.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Trader;
import com.masai.entities.Shares;
import com.masai.entities.Transaction;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;

public class SharesServicesImpl implements SharesService {

	public String addShare(Shares shr, Map<Integer, Shares> shares) {
		// TODO Auto-generated method stub
		//as our ids are always unique thats why directly putting into products
		shares.put(shr.getId(), shr);

		return "Share added successfully";

	}

	public void viewAllShares(Map<Integer, Shares> shares) throws ProductException {
		// TODO Auto-generated method stub
		if (shares != null && shares.size() > 0) {
			for (Map.Entry<Integer, Shares> me : shares.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			throw new ProductException("Shares List is empty");
		}
	}

	public void deleteShare(int id, Map<Integer, Shares> shares) throws ProductException {

		// System.out.println(products);
		if (shares != null && shares.size() > 0) {

			if (shares.containsKey(id)) {
				shares.remove(id);
				System.out.println("Share deleted successfully");

			} else {
				throw new ProductException("Share not found");
			}

		} else {
			throw new ProductException("Shares list is empty");
		}

	}

	public String updateShare(int id, Shares shr, Map<Integer, Shares> shares) throws ProductException {

		if (shares != null && shares.size() > 0) {

			if (shares.containsKey(id)) {
				shares.put(id, shr);
				return "Share has successfully updated";
			} else {
				throw new ProductException("Share not found");
			}

		} else {
			throw new ProductException("Shares list is empty");
		}

	}

}
