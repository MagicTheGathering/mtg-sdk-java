package io.magicthegathering.javasdk.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import io.magicthegathering.javasdk.resource.Card;

import java.util.List;

import org.junit.Test;

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
	
	@Test
	public void testGetAll() {
		List<Card> testCardList = CardAPI.getAllCards();
		Card testCard = new Card();
		testCard.setMultiverseid(94);
		assertEquals(testCardList.get(0), testCard);
	}
}
