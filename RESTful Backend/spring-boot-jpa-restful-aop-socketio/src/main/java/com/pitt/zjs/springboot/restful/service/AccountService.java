package com.pitt.zjs.springboot.restful.service;

import java.util.List;

import com.pitt.zjs.springboot.restful.entity.Account;


public interface AccountService {
	public List<Account> getAccounts();

	public void saveAccount(Account theAccount);

	public Account getAccount(int theId);

	public void deleteAccount(int theId);
	
	public Account getAccountByEmail(String theEmail);

}
