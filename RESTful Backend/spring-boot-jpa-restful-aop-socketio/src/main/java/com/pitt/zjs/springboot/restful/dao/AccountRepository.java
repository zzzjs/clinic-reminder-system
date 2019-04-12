package com.pitt.zjs.springboot.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pitt.zjs.springboot.restful.entity.Account;

@CrossOrigin()
public interface AccountRepository extends JpaRepository<Account, Integer> {
	@Query(value = "select * from Account a where a.email=?1", nativeQuery = true)
	Account findByEmail(String email);
}
