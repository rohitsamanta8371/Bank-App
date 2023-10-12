package com.mjunction.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Accounts {
	@Id
	private long id;
	private String name;
	private String acno;
	private String phone;
	private String email;
	private double balance;
	private String password;
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public Accounts(long id, String name, String acno, String phone, String email, double balance, String password) {
        super();
        this.id = id;
        this.name = name;
        this.acno = acno;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
