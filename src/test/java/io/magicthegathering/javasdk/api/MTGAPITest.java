package io.magicthegathering.javasdk.api;

import org.junit.After;
import org.junit.Before;

import by.stub.client.StubbyClient;

/**
 * Base class for unit tests. Takes care of starting and stopping a stubby4j
 * server to simulate the magicthegathering.io REST APIs locally.
 * 
 * @author nniklas
 *
 */
public class MTGAPITest {
	private StubbyClient stubbyClient;

	@Before
	public void initStubby() throws Exception {
		stubbyClient = new StubbyClient();
		stubbyClient.startJetty("stubby/stubby-config.yaml");
		MTGAPI.ENDPOINT = "http://localhost:8882";
	}

	@After
	public void teardownStubby() throws Exception {
		stubbyClient.stopJetty();
		// TODO Remove when all tests use stubby instead of real http calls
		MTGAPI.ENDPOINT = "https://api.magicthegathering.io/v1";
	}
}
