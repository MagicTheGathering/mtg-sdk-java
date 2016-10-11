package io.magicthegathering.javasdk.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import io.magicthegathering.javasdk.resource.MtgSet;

import org.junit.Test;

public class SetAPITest {
	@Test
	public void testGetSet() {
		MtgSet testSet = new MtgSet();
		testSet.setGatherercode("1E");
		assertEquals(testSet, SetAPI.getSet("LEA"));
		assertFalse(testSet.equals(SetAPI.getSet("LEB")));
	}
}
