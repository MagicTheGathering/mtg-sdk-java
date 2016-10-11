package io.magicthegathering.javasdk;

import junit.framework.TestCase;

import java.util.List;

import io.magicthegathering.javasdk.QueryBuilder;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.Resource;
import io.magicthegathering.javasdk.resource.Set;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 *
 * Created by thechucklingatom on 8/10/2016.
 *
 * @author thechucklingatom
 */
public class QueryBuilderTest extends TestCase{
	private QueryBuilder cardQueryBuilder;
	private QueryBuilder setQueryBuilder;

	@Override
	protected void setUp() throws Exception {
		cardQueryBuilder = new QueryBuilder("cards");
		setQueryBuilder = new QueryBuilder("sets");
	}

	public void testCardGet(){
		Card testCard = new Card();
		testCard.setMultiverseid(1);
		Card result = (Card) cardQueryBuilder.find("1");
		assertTrue(testCard.equals(result));

		assertFalse(testCard.equals(cardQueryBuilder.find("10")));
	}

	public void testBadCardId(){
		assertNull(cardQueryBuilder.find("-1"));
	}

	public void testAllCard(){
		List<Resource> testCardList = cardQueryBuilder.all();

		Card testCard = new Card();
		testCard.setMultiverseid(94);

		assertTrue(testCardList.get(0).equals(testCard));
	}

	public void testSetGet(){
		Set testSet = new Set();
		testSet.setGatherercode("1E");

		assertTrue(testSet.equals(setQueryBuilder.find("LEA")));

		assertFalse(testSet.equals(setQueryBuilder.find("LEB")));
	}

	public void testBadSetID(){
		assertNull(setQueryBuilder.find("1"));
	}

	public void testAllSet(){
		List<Resource> testSetList = setQueryBuilder.all();

		Set testSet = new Set();
		testSet.setGatherercode("1E");

		assertTrue(testSetList.get(0).equals(testSet));
	}
}
