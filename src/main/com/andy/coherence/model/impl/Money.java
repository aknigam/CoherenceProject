package com.andy.coherence.model.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money implements Serializable {

	private static final long serialVersionUID = 1L;
	private final BigDecimal mAmount;
	private final Currency mCurrency;

	public Money(BigDecimal amount, Currency currency) {
		mAmount = amount.setScale(currency.getDefaultFractionDigits(),
				RoundingMode.HALF_EVEN);
		mCurrency = currency;
	}

	public BigDecimal getAmount() {
		return mAmount;
	}

	public Currency getCurrency() {
		return mCurrency;
	}

	public boolean isSameCurrency(Money money) {
		return mCurrency.equals(money.mCurrency);
	}

	public Money add(Money money) {
		checkCurrency(money);
		return new Money(mAmount.add(money.mAmount), mCurrency);
	}

	public Money subtract(Money money) {
		checkCurrency(money);
		return new Money(mAmount.subtract(money.mAmount), mCurrency);
	}

	public boolean greaterThan(Money money) {
		checkCurrency(money);
		return mAmount.compareTo(money.mAmount) > 0;
	}

	public boolean lessThan(Money money) {
		checkCurrency(money);
		return mAmount.compareTo(money.mAmount) < 0;
	}

	private void checkCurrency(Money money) {
		// TODO Auto-generated method stub

	}

}
