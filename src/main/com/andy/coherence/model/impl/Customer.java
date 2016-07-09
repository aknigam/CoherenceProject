package com.andy.coherence.model.impl;

import java.io.Serializable;
import java.util.Collection;

import com.andy.coherence.model.IEntity;
import com.andy.repository.AccountRepository;

public class Customer implements IEntity<Long>, Serializable {

	private Collection<Long> mAccountIds;

	public Collection<Account> getAccounts() {
		return getAccountRepository().getAccounts(mAccountIds);
	}

	private AccountRepository getAccountRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		return null;
	}

	@Override
	public Long getKey() {
		return null;
	}

}
