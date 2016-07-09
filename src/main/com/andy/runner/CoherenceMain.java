package com.andy.runner;

import java.util.Map;
import java.util.Set;

import com.andy.coherence.cache.impl.CountryDistributedCache;
import com.andy.coherence.model.Country;

public class CoherenceMain {

	public static void main(String[] args) throws InterruptedException {
		CountryDistributedCache cache = new CountryDistributedCache();
		cache.init();

		cache.add("USA", new Country("USA", "United States", "Washington", "USD", "Dollar"));
		cache.add("GBR", new Country("GBR", "United Kingdom", "London", "GBP", "Pound"));
		cache.add("RUS", new Country("RUS", "Russia", "Moscow", "RUB", "Ruble"));
		cache.add("CHN", new Country("CHN", "China", "Beijing", "CNY", "Yuan"));
		cache.add("JPN", new Country("JPN", "Japan", "Tokyo", "JPY", "Yen"));
		cache.add("DEU", new Country("DEU", "Germany", "Berlin", "EUR", "Euro"));
		cache.add("FRA", new Country("FRA", "France", "Paris", "EUR", "Euro"));
		cache.add("ITA", new Country("ITA", "Italy", "Rome", "EUR", "Euro"));
		cache.add("SRB", new Country("SRB", "Serbia", "Belgrade", "RSD", "Dinar"));

		assert cache.exists("JPN") : "Japan is not in the cache";
		// get and print a single country
		System.out.println("get(SRB) = " + cache.findByKey("SRB"));
		// remove Italy from the cache
		int size = cache.size();
		System.out.println("remove(ITA) = " + cache.remove("ITA"));
		assert cache.size() == size - 1 : "Italy was not removed";
		// list all cache entries
		Set<Map.Entry<String, Country>> entries = cache.findAll(null, null);
		for (Map.Entry entry : entries) {
			System.out.println(entry.getKey() + " = " + entry.getValue());

		}
		System.out.println(cache.getTotalNoOfCountries());
//		Thread.sleep(100000l);
	}
}
