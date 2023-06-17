package com.masai.entities;

import java.io.Serializable;

public class Trader extends User implements Serializable{
	private double walletBalance;

	public Trader(double walletBalance, String username, String password, String address, String email) {
		super(username, password, address, email);
		this.walletBalance = walletBalance;
	}

	public double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "Trader [walletBalance=" + walletBalance + ", getWalletBalance()=" + getWalletBalance()
				+ ", getUsername()=" + getUsername() + ", getAddress()=" + getAddress() + ", getEmail()=" + getEmail()
				+  "]";
	}
}
