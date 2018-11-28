package io.magicthegathering.javasdk.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SetAPITest extends MTGAPITest {
	@Test
	public void testGetSet() {
		MtgSet testSet = new MtgSet();
		testSet.setGatherercode("1E");
		assertEquals(testSet, SetAPI.getSet("LEA"));
		assertNotEquals(testSet, SetAPI.getSet("LEB"));
	}

	@Test
	public void testBadSetId() {
		assertNull(SetAPI.getSet("666"));
	}

	@Test
	public void testGetAllSets() {
		List<MtgSet> testSetList = SetAPI.getAllSets();
		MtgSet testSet = new MtgSet();
		testSet.setGatherercode("1E");
		assertEquals(testSetList.get(0), testSet);
	}

	@Test
	public void testGetBoosterFromSet() {
		String setCode = "KLD";
		List<Card> booster = SetAPI.getBooster(setCode);
		assertEquals(15, booster.size());
	}

	@Test
	public void testSetFilter(){
		ArrayList<String> filter = new ArrayList<>();
		filter.add("name=Alpha");

		MtgSet alpha = SetAPI.getSet("LEA");

		assertTrue(SetAPI.getAllSets(filter).contains(alpha));
	}

	@Test
	public void testSetGetCards() {
		MtgSet testSet;
		testSet = SetAPI.getSet("LEA");

		assertNotNull(testSet.getCards());

		Card testCard = new Card();
		testCard.setMultiverseid(94);
		testCard.setName("Air Elemental");
		testCard.setCmc(5);

		assertTrue(testSet.getCards().contains(testCard));
	}

	@Test
	public void testGetAllSetsWithCards() {
		List<MtgSet> sets = SetAPI.getAllSetsWithCards();

		assertNotNull(sets.get(0).getCards());
	}
}
