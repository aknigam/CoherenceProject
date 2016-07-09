package com.andy.coherence.cache.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.andy.coherence.cache.IDistributedCacheRepository;
import com.andy.coherence.model.IEntity;
import com.tangosol.net.NamedCache;

public abstract class AbstractDistributedCacehRepository<K, V extends IEntity<K>>
		implements IDistributedCacheRepository {

	public abstract NamedCache getCache();

	public V get(K key) {
		return (V) getCache().get(key);
	}

	public Collection<V> getAll(Collection<K> keys) {
		return (Collection<V>) getCache().getAll(keys);
	}

	/**
	 * putAll is used instead of put to avoid two network calls, one to put the
	 * value and another to return the old value. Put increases the latency.
	 * 
	 * @param value
	 */
	public void save(V value) {
		getCache().putAll(Collections.singletonMap(value.getKey(), value));
	}

	public void saveAll(Collection<V> values) {
		Map<K, V> batch = new HashMap<K, V>();
		for (V v : values) {
			batch.put(v.getKey(), v);
		}
		getCache().putAll(batch);
	}
}
