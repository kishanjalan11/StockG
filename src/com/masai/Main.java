package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Trader;
import com.masai.entities.Broker;

//import com.masai.exceptions.DuplicateDataException;
import com.masai.utility.FileExists;
public class Main {

	public static void traderSignup(Scanner sc, Map<String, Trader> customers) {
		System.out.println("Please Enter the following details to Sign Up");
		System.out.println("Please Enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("Enter the address");
		String address = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		Trader trader = new Trader(balance, name, pass, address, email);

//		CustomerService cusService = new CustomerServiceImpl();
//		cusService.signUp(cus, customers);
		System.out.println("Trader has Succefully sign up");

	}
	public static void brokerSignup(Scanner sc, Map<String, Broker> brokers) {
		System.out.println("Please Enter the following details to Sign Up");
		System.out.println("Please Enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("Enter the address");
		String address = sc.next(); 	
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the balance to be added into the wallet");
		double balance = sc.nextDouble();
		Broker broker = new Broker(balance, name, pass, address, email);

//		CustomerService cusService = new CustomerServiceImpl();
//		cusService.signUp(cus, customers);
		System.out.println("Broker has Succefully sign up");

	}

	public static void main(String[] args) {
		//file check
//				Map<Integer, Product> products = FileExists.productFile();
				Map<String, Trader> traders = FileExists.traderFile();
				Map<String, Broker> brokers= FileExists.brokerFile();
//				List<Transaction> transactions = FileExists.transactionFile();

				Scanner sc = new Scanner(System.in);

				System.out.println("Welcome to StockG - Navigating the Financial Markets");

				try {

					int preference = 0;
					do {
						System.out.println("Please enter your preference, " + " '1' --> Admin login , '2' --> Trader login , "
						+ "'3' for Trader signup, '4' for Broker signup, '0' for exit");
						preference = sc.nextInt();
						switch (preference) {
						case 1:
//							adminFunctionality(sc, products, customers, transactions);
							break;
						case 2:
//							traderFunctionality(sc, traders, products, transactions);
							break;

						case 3:
							traderSignup(sc, traders);
							System.out.println("------------------------");
							break;
						case 4:
							brokerSignup(sc, brokers);
							System.out.println("------------------------");
							break;

						case 0:
							System.out.println("You have successfully exited from the system");

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
//						ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Product.ser"));
//						poos.writeObject(products);
						ObjectOutputStream trs = new ObjectOutputStream(new FileOutputStream("Trader.ser"));
						trs.writeObject(traders);
						
						ObjectOutputStream brs = new ObjectOutputStream(new FileOutputStream("Broker.ser"));
						trs.writeObject(brokers);
						
						System.out.println("_____________________________________________");
//						ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
//						toos.writeObject(transactions);
					//	System.out.println("serialized..........");
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}
				}

			}
}