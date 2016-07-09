package com.andy.coherence.model;

import java.io.Serializable;

public class Country implements Serializable, Comparable<Country> {

	private static final long serialVersionUID = 1L;

	private String mCode;
	private String mName;
	private String mCapital;
	private String mCurrencySymbol;
	private String mCurrencyName;

	public Country(String code, String name, String capital,
			String currencySymbol, String currencyName) {
		mCode = code;
		mName = name;
		mCapital = capital;
		mCurrencySymbol = currencySymbol;
		mCurrencyName = currencyName;
	}

	public String getCode() {
		return mCode;
	}

	public void setCode(String code) {
		mCode = code;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getCapital() {
		return mCapital;
	}

	public void setCapital(String capital) {
		mCapital = capital;
	}

	public String getCurrencySymbol() {
		return mCurrencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		mCurrencySymbol = currencySymbol;
	}

	public String getCurrencyName() {
		return mCurrencyName;
	}

	public void setCurrencyName(String currencyName) {
		mCurrencyName = currencyName;
	}

	@Override
	public String toString() {
		return "Country(" + "Code = " + mCode + ", " + "Name = " + mName + ", "
				+ "Capital = " + mCapital + ", " + "CurrencySymbol = "
				+ mCurrencySymbol + ", " + "CurrencyName = " + mCurrencyName
				+ ")";
	}

	@Override
	public int compareTo(Country o) {
		if (o == null)
			return -1;
		return mName.compareTo(o.mName);
	}

}
