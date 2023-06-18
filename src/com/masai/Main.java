package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Trader;
import com.masai.entities.Broker;
import com.masai.entities.Shares;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;
import com.masai.service.TraderService;
import com.masai.service.TraderServiceImpl;
import com.masai.service.BrokerService;
import com.masai.service.BrokerServiceImpl;
import com.masai.service.SharesService;
import com.masai.service.SharesServicesImpl;
import com.masai.service.TransactionService;
import com.masai.service.TransactionServiceImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IDGeneration;

public class Main {

	// admin functionality
	private static void adminFunctionality(Scanner sc, Map<Integer, Shares> shares, Map<String, Trader> traders,
			List<Transaction> transactions) throws InvalidDetailsException, ProductException, TransactionException {
		// admin login

		adminLogin(sc);

		SharesService shareService = new SharesServicesImpl();
		TraderService trdrService = new TraderServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 add the product");
				System.out.println("Press 2 view all the product");
				System.out.println("Press 3 delete the product");
				System.out.println("Press 4 update the product");
				System.out.println("Press 5 view all customers");
				System.out.println("Press 6 to view all transactions");
				System.out.println("Press 7 to log out");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddShare(sc, shares, shareService);
					System.out.println(added);
					break;
				case 2:

					adminViewAllShares(shares, shareService);
					break;
				case 3:

					adminDeleteShare(sc, shares, shareService);
					break;
				case 4:

					String upt = adminUpdateShare(sc, shares, shareService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllTraders(traders, trdrService);

					break;
				case 6:
					adminViewAllTransactions(transactions, trnsactionService);
					break;
				case 7:
					System.out.println("Admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		if (userName.equals(Admin.username) && password.equals(Admin.password)) {

			System.out.println("Successfully logged in as Admin");
		} else {
			throw new InvalidDetailsException("Invalid Admin Credentials");
		}
	}

	public static String adminAddShare(Scanner sc, Map<Integer, Shares> shares, SharesService shareService) {

		String str = null;
		System.out.println("please enter the Share details");
		System.out.println("Enter the Share name");
		String name = sc.next();
		System.out.println("Enter the Share qty");
		int qty = sc.nextInt();
		System.out.println("Enter the Share price/piece");
		double price = sc.nextDouble();

		Shares share = new Shares(IDGeneration.generateId(), name, qty, price);

		str = shareService.addShare(share, shares);// considering all details are valid

		return str;

	}

	public static void adminViewAllShares(Map<Integer, Shares> shares, SharesService shareService)
			throws ProductException {
		shareService.viewAllShares(shares);
	}

	public static void adminDeleteShare(Scanner sc, Map<Integer, Shares> shares, SharesService shareService)
			throws ProductException {

		System.out.println("please enter the id of Share to be deleted");
		int id = sc.nextInt();
		shareService.deleteShare(id, shares);
	}

	public static String adminUpdateShare(Scanner sc, Map<Integer, Shares> shares, SharesService shareService)
			throws ProductException {
		String result = null;
		System.out.println("please enter the id of the Share which is to be updated");
		int id = sc.nextInt();
		System.out.println("Enter the updated details ");

		System.out.println("Enter the Share name");
		String name = sc.next();

		System.out.println("Enter the Share Quantity");
		int qty = sc.nextInt();

		System.out.println("Enter the Share price/piece");
		double price = sc.nextDouble();

		Shares update = new Shares(id, name, qty, price);

		result = shareService.updateShare(id, update, shares);
		return result;
	}

	public static void adminViewAllTraders(Map<String, Trader> traders, TraderService trdrService)
			throws ProductException {
		List<Trader> list = trdrService.viewAllTraders(traders);

		for (Trader c : list) {
			System.out.println(c);
		}
	}

	public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService trnsactionService)
			throws TransactionException {
		List<Transaction> allTransactions = trnsactionService.viewAllTransactions(transactions);

		for (Transaction tr : allTransactions) {
			System.out.println(tr);
		}

	}

	
	
	// Trader functionality
	public static void traderFunctionality(Scanner sc, Map<String, Trader> traders,
			Map<Integer, Shares> shares, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		TraderService trdrService = new TraderServiceImpl();
		SharesService shareService = new SharesServicesImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();

		// Customer login
		System.out.println("please enter the following details to login");
		System.out.println("please enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		traderLogin(email,pass, traders, trdrService);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all Shares");
				System.out.println("Press 2 to buy a Share");
				System.out.println("Press 3 to add money to a wallet");
				System.out.println("Press 4 view wallet balance");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 view my transactions");
				System.out.println("Press 7 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					traderViewAllShares(shares, shareService);
					break;
				case 2:
					String result = traderBuyShare(sc, email, shares, traders, transactions, trdrService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = traderAddMoneyToWallet(sc, email, traders, trdrService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = traderViewWalletBalance(email, traders, trdrService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					traderViewMyDetails(email, traders, trdrService);
					break;
				case 6:
					traderViewTraderTransactions(email, transactions, trnsactionService);
					break;
				case 7:
					System.out.println("you have successsfully logout");
					break;
				default:
					System.out.println("invalid choice");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void traderSignup(Scanner sc, Map<String, Trader> traders) throws DuplicateDataException {
		System.out.println("please enter the following details to Signup");
		System.out.println("please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		Trader trdr= new Trader(balance, name, pass, address, email);

		TraderService trdrService = new TraderServiceImpl();
		trdrService.signUp(trdr, traders);
		System.out.println("Trader has Succefully sign up");

	}

	public static void traderLogin(String email,String pass, Map<String, Trader> traders, TraderService trdrService)
			throws InvalidDetailsException {
		trdrService.login(email, pass,traders);
		System.out.println("Trader has successfully Logged In");

	}

	public static void traderViewAllShares(Map<Integer, Shares> shares, SharesService shareService)
			throws ProductException {
		shareService.viewAllShares(shares);
	}

	public static String traderBuyShare(Scanner sc, String email, Map<Integer, Shares> shares,
			Map<String, Trader> traders, List<Transaction> transactions, TraderService trdrService)
			throws InvalidDetailsException, ProductException {
		System.out.println("Enter The Product id");
		int id = sc.nextInt();
		System.out.println("Enter the quantity you want to buy");
		int qty = sc.nextInt();
		trdrService.buyProduct(id, qty, email, shares, traders, transactions);

		return "You have successfully bought the S";

	}

	public static String traderAddMoneyToWallet(Scanner sc, String email, Map<String, Trader> traders,
			TraderService trdrService) {
		System.out.println("Please enter the amount");
		double money = sc.nextDouble();
		boolean added = trdrService.addMoneyToWallet(money, email, traders);

		return "Amount: " + money + " successfully added to your wallet";
	}

	public static double traderViewWalletBalance(String email, Map<String, Trader> traders,
			TraderService trdrService) {
		double walletBalance = trdrService.viewWalletBalance(email, traders);
		return walletBalance;
	}

	public static void traderViewMyDetails(String email, Map<String, Trader> traders,
			TraderService trdrService) {
		Trader trdr = trdrService.viewTraderDetails(email, traders);
		System.out.println("name : " + trdr.getUsername());
		System.out.println("address : " + trdr.getAddress());
		System.out.println("email : " + trdr.getEmail());
		System.out.println("wallet balance : " + trdr.getWalletBalance());
	}

	public static void traderViewTraderTransactions(String email, List<Transaction> transactions,
			TransactionService trnsactionService) throws TransactionException {
		List<Transaction> myTransactions = trnsactionService.viewTraderTransactions(email, transactions);

		for (Transaction tr : myTransactions) {
			System.out.println(tr);
		}
	}

	
	
	public static void main(String[] args) {
//file check
		Map<Integer, Shares> shares = FileExists.productFile();
		Map<String, Trader> traders = FileExists.traderFile();
		List<Transaction> transactions = FileExists.transactionFile();
//		System.out.println(products.size());
//		System.out.println(customers.size());
//		System.out.println(transactions.size());

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome , in ShareG - Navigating the Financial Markets");

		try {

			int preference = 0;
			do {
				System.out.println("Please enter your preference, " + " '1' --> Admin login , '2' --> Trader login , "
				+ "'3' for Trader signup, '0' for exit");
				preference = sc.nextInt();
				switch (preference) {
				case 1:
					adminFunctionality(sc, shares, traders, transactions);
					break;
				case 2:
					traderFunctionality(sc, traders, shares, transactions);
					break;

				case 3:
					traderSignup(sc, traders);
					break;

				case 0:
					System.out.println("successfully Signed Out from the system");

					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}

			}

			while (preference != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			// serialization (finally always executed)
			try {
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Share.ser"));
				poos.writeObject(shares);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Trader.ser"));
				coos.writeObject(traders);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(transactions);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}

	}

}
