package com.andy.coherence.cache.impl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import com.andy.coherence.cache.IDistributedCacheRepository;
import com.andy.coherence.model.Country;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.Filter;
import com.tangosol.util.aggregator.AbstractAggregator;
import com.tangosol.util.filter.EqualsFilter;

public class CountryDistributedCache implements IDistributedCacheRepository {

	private NamedCache mCountryCache;

	public void init() {
		mCountryCache = CacheFactory.getCache("countries");
	}

	public NamedCache getCountryCache() {
		return mCountryCache;
	}

	public Country add(String key, Country country) {
		return (Country) mCountryCache.put(key, country);
	}

	public boolean exists(String key) {
		return mCountryCache.containsKey(key);
	}

	public Country findByKey(String key) {
		return (Country) mCountryCache.get(key);
	}

	public int size() {
		return mCountryCache.size();
	}

	public Country remove(String key) {
		return (Country) mCountryCache.remove(key);
	}

	public Set<Map.Entry<String, Country>> findAll(Filter filter, Comparator<Country> comparator) {
		return mCountryCache.entrySet(filter, comparator);
	}

	public int getTotalNoOfCountries() {
		Filter filter = new EqualsFilter("getCurrencySymbol", "EUR");
		int s = (Integer) mCountryCache.aggregate(filter, new AbstractAggregator("getCurrencySymbol") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private transient int sum;

			@Override
			protected void process(Object o, boolean fFinal) {
				if (o != null) {
					if (fFinal) {
						sum = sum + ((PartialResult) o).getSum();
					} else {
						sum = sum + 1;
					}
				}
			}

			@Override
			protected void init(boolean fFinal) {
				sum = 0;
			}

			@Override
			protected Object finalizeResult(boolean fFinal) {
				if (fFinal) {
					return sum;
				} else {
					return new PartialResult(sum, 0);
				}

			}
		});
		return s;

	}

	static class PartialResult implements Serializable {
		private final int sum;
		private final int count;

		PartialResult(int sum, int count) {
			this.sum = sum;
			this.count = count;
		}

		public int getSum() {
			return sum;
		}

		public int getCount() {
			return count;
		}
	}
}
