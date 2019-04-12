package com.pitt.zjs.springboot.restful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitt.zjs.springboot.restful.dao.AccountRepository;
import com.pitt.zjs.springboot.restful.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {


	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public void saveAccount(Account theAccount) {
		// TODO Auto-generated method stub
		accountRepository.save(theAccount);
	}

	@Override
	public Account getAccount(int theId) {
		// TODO Auto-generated method stub
		Optional<Account> result = accountRepository.findById(theId);
		Account theAccount = null;
		if (result.isPresent()) {
			theAccount = result.get(); 
		} else {
			// didn't find the employee
			throw new RuntimeException("Did not find the account id - " + theId);
		}
		return theAccount;
	}

	@Override
	public void deleteAccount(int theId) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(theId);
	}

	@Override
	public Account getAccountByEmail(String theEmail) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(theEmail);
	}

}
