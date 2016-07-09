package com.andy.services;

import java.util.Currency;

import com.andy.coherence.model.impl.Money;

public interface CurrencyConverter {
	CurrencyConversion convert(Money amount, Currency targetCurrency);
}
