package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class Shares implements Serializable{
	private int id;
	private String name;
	private int qty;
	private double price;

	public Shares() {
		super();
	}

	public Shares(int id, String name, int qty, double price) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Shares [id=" + id + ", name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shares other = (Shares) obj;
		return Objects.equals(id , other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && qty == other.qty;
}
}