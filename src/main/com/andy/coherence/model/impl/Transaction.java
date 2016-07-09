package com.andy.coherence.model.impl;

import java.io.Serializable;

import com.andy.coherence.model.IEntity;
import com.andy.enums.TransactionType;
import com.tangosol.net.cache.KeyAssociation;

public class Transaction implements IEntity<Id>, Serializable {

	@Override
	public Id getKey() {
		return null;

	}

	public static Transaction create(Object mid, int i, TransactionType type, String description, Money amount,
			Money balance) {
		// TODO Auto-generated method stub
		return null;
	}

}

class Id implements Serializable, KeyAssociation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long mAccountId;
	private final Long mTxNumber;

	public Id(Long accountId, Long txNumber) {
		mAccountId = accountId;
		mTxNumber = txNumber;
	}

	@Override
	public Object getAssociatedKey() {
		return mAccountId;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
