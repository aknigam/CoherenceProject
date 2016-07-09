package com.andy.coherence.model.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import com.andy.CustomerRepository;
import com.andy.TransactionRepository;
import com.andy.coherence.model.IEntity;
import com.andy.enums.TransactionType;
import com.andy.exceptions.InsufficientFundsException;
import com.andy.repository.RepositoryRegistry;
import com.andy.services.CurrencyConversion;
import com.andy.services.CurrencyConverter;
import com.seovic.identity.IdGenerator;
import com.seovic.identity.SequenceGenerator;
import com.seovic.identity.SequenceGenerator.SequenceBlock;

public class Account implements IEntity<Long>, Serializable {

	private static IdGenerator<Long> s_idGen = new SequenceGenerator("account.id", 20) {
		
		@Override
		protected SequenceBlock allocateSequenceBlock() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	private final Long mId;
	private final Long mCustomerId;
	private String mDescription;
	private Money mBalance;
	private int mLastTransactionId;
	private transient CurrencyConverter mCurrencyConverter;
	private transient TransactionRepository mTransactionRepository;
	private transient CustomerRepository mCustomerRepository;
	// constructor, getters and setters omitted for brevity
	private Object mid;
	private int mlastTransactionId;

	private Account(Long id, Long customerId, String description, Money balance) {
		mId = id;
		mCustomerId = customerId;
		mDescription = description;
		mBalance = balance;
	}

	static Account create(Customer customer, String description, Currency currency) {
		return new Account(s_idGen.generateId(), customer.getId(), description, new Money(new BigDecimal(0),
				currency));
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		mCustomerRepository = customerRepository;
	}

	public CustomerRepository getCustomerRepository() {
		if (mCustomerRepository == null) {
			mCustomerRepository = RepositoryRegistry.getCustomerRepository();
		}
		return mCustomerRepository;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public Money getBalance() {
		return mBalance;
	}

	public void setBalance(Money balance) {
		mBalance = balance;
	}

	public int getLastTransactionId() {
		return mLastTransactionId;
	}

	public void setLastTransactionId(int lastTransactionId) {
		mLastTransactionId = lastTransactionId;
	}

	public CurrencyConverter getCurrencyConverter() {
		return mCurrencyConverter;
	}

	public void setCurrencyConverter(CurrencyConverter currencyConverter) {
		mCurrencyConverter = currencyConverter;
	}

	public TransactionRepository getTransactionRepository() {
		return mTransactionRepository;
	}

	public void setTransactionRepository(TransactionRepository transactionRepository) {
		mTransactionRepository = transactionRepository;
	}

	public Long getId() {
		return mId;
	}

	public Long getCustomerId() {
		return mCustomerId;
	}

	public Customer getCustomer() {
		return getCustomerRepository().getCustomer(mCustomerId);
	}

	// core logic
	public Money withdraw(Money amount, String description) throws InsufficientFundsException {
		Money balance = mBalance;
		if (!balance.isSameCurrency(amount)) {
			CurrencyConversion conversion = getCurrencyConverter().convert(amount, getCurrency());
			amount = conversion.getConvertedAmount();
			description += " (" + conversion.getOriginalAmount() + " @ " + conversion.getExchangeRate() + ")";
		}
		if (amount.greaterThan(balance)) {
			throw new InsufficientFundsException(balance, amount);
		}
		// entity, domain model building blocksimplementingmbalance = balance =
		// balance.subtract(amount);
		postTransaction(TransactionType.WITHDRAWAL, description, amount, balance);
		return balance;
	}

	private Currency getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	public Money deposit(Money amount, String description) {
		return amount;
	}

	protected void postTransaction(TransactionType type, String description, Money amount, Money balance) {
		Transaction transaction = Transaction.create(mid, ++mlastTransactionId, type, description, amount, balance);
		getTransactionRepository().save(transaction);
	}

	@Override
	public Long getKey() {
		return null;
	}

}
