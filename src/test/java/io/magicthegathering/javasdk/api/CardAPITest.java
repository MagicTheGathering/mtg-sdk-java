package io.magicthegathering.javasdk.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import io.magicthegathering.javasdk.resource.Card;

public class CardAPITest {
	
	@Test
	public void testGetCard() {
		Card testCard = new Card();
		testCard.setMultiverseid(1);
		assertEquals(testCard, CardAPI.getCard(1));
		assertFalse(testCard.equals(CardAPI.getCard(10)));
	}
	
	@Test
	public void testBadCardId(){
		assertNull(CardAPI.getCard(-1));
	}
}
