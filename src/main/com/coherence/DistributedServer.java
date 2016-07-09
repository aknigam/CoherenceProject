package com.coherence;

import com.tangosol.net.DefaultCacheServer;
import com.tangosol.net.DefaultConfigurableCacheFactory;

public class DistributedServer {

	public static void main(String[] args) {
		DefaultConfigurableCacheFactory factory;
		factory = new DefaultConfigurableCacheFactory();

		DefaultCacheServer dcs = new DefaultCacheServer(factory);
		dcs.startAndMonitor(5000);
	}
}
