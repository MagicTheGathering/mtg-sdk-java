import junit.framework.TestCase;

import mtg.sdk.java.Card;
import mtg.sdk.java.QueryBuilder;

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
	private QueryBuilder queryBuilder;

	@Override
	protected void setUp() throws Exception {
		queryBuilder = new QueryBuilder("cards");
	}

	public void testCardGet(){
		Card testCard1 = new Card();
		testCard1.setName("Ankh of Mishra");
		testCard1.setMultiverseid(1);

		assertTrue(testCard1.equals(queryBuilder.find("1")));

	}
}
