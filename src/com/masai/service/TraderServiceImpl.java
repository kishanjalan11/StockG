package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.masai.entities.Shares;
import com.masai.entities.Trader;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;

public class TraderServiceImpl implements TraderService {

	@Override
	public void signUp(Trader trdr, Map<String, Trader> traders) throws DuplicateDataException {

		if (traders.containsKey(trdr.getEmail())) {
			throw new DuplicateDataException("Trader already exists , please login");
		} else {

			traders.put(trdr.getEmail(), trdr);

		}

	}

	@Override
	public boolean login(String email,String password, Map<String, Trader> traders) throws InvalidDetailsException {

		if (traders.containsKey(email) ) {
			
			if(traders.get(email).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
			
		} else {
			throw new InvalidDetailsException("Account Not found, Please Sign Up");
		}

	}

	@Override
	public boolean buyProduct(int id, int qty, String email, Map<Integer, Shares> shares,
			Map<String, Trader> traders, List<Transaction> transactions)
			throws InvalidDetailsException, ProductException {

		if (shares.size() == 0)
			throw new ProductException("Shares list is empty");

		if (shares.containsKey(id)) {

			Shares prod = shares.get(id);

			if (prod.getQty() >= qty) {

				Trader cus = traders.get(email);

				double buyingPrice = qty * prod.getPrice();

				if (cus.getWalletBalance() >= buyingPrice) {
					cus.setWalletBalance(cus.getWalletBalance() - buyingPrice);

					prod.setQty(prod.getQty() - qty);

					shares.put(id, prod);

					Transaction tr = new Transaction(cus.getUsername(), email, id,prod.getName(), qty, prod.getPrice(),
							prod.getPrice() * qty, LocalDate.now());

					transactions.add(tr);

				} else {
					throw new InvalidDetailsException("wallet balance is not sufficient");
				}

			} else {
				throw new InvalidDetailsException("product quantity is not suffiecient");
			}

		} else {
			throw new InvalidDetailsException("product not available with id: " + id);
		}

		return false;
	}

	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Trader> traders) {
		// TODO Auto-generated method stub

		Trader cus = traders.get(email);

		cus.setWalletBalance(cus.getWalletBalance() + amount);

		traders.put(email, cus);

		return true;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Trader> traders) {
		// TODO Auto-generated method stub

		Trader cus = traders.get(email);

		return cus.getWalletBalance();
	}

	@Override
	public Trader viewTraderDetails(String email, Map<String, Trader> traders) {

		if (traders.containsKey(email)) {

			return traders.get(email);

		}

		return null;
	}

	@Override
	public List<Trader> viewAllTraders(Map<String, Trader> traders) throws ProductException {
		// TODO Auto-generated method stub
		List<Trader> list = null;

		if (traders != null && traders.size() > 0) {
			Collection<Trader> coll = traders.values();
			list = new ArrayList<>(coll);
		} else {
			throw new ProductException("Traders list is empty");
		}

		return list;
	}

}
