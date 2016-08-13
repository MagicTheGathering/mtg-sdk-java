import junit.framework.TestCase;

import mtg.sdk.java.Card;
import mtg.sdk.java.QueryBuilder;
import mtg.sdk.java.Set;

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
public class QueryBuilderTests extends TestCase{
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

		assertTrue(testCard.equals(cardQueryBuilder.find("1")));

		assertFalse(testCard.equals(cardQueryBuilder.find("10")));
	}

	public void testBadCardId(){
		assertNull(cardQueryBuilder.find("-1"));
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
}
