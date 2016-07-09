package com.andy.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.andy.coherence.model.impl.Money;

public class CurrencyConversion {
	private final Money m_originalAmount;
	private final Money m_convertedAmount;

	public CurrencyConversion(Money originalAmount, Money convertedAmount) {
		m_originalAmount = originalAmount;
		m_convertedAmount = convertedAmount;
	}

	public Money getOriginalAmount() {
		return m_originalAmount;
	}

	public Money getConvertedAmount() {
		return m_convertedAmount;
	}

	public BigDecimal getExchangeRate() {
		BigDecimal exchangeRate = m_convertedAmount.getAmount().divide(
				m_originalAmount.getAmount());
		return exchangeRate.setScale(4, RoundingMode.HALF_EVEN);
	}
}