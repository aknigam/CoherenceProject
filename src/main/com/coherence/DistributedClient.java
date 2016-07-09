package com.coherence;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class DistributedClient {

	
	public static void main(String[] args) {
		CacheFactory.ensureCluster();
		NamedCache cache = CacheFactory.getCache("cache_name");
	} 

}
