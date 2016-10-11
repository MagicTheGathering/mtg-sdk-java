package io.magicthegathering.javasdk;

import junit.framework.TestCase;

import java.util.List;

import org.junit.Test;

import io.magicthegathering.javasdk.QueryBuilder;
import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.Resource;
import io.magicthegathering.javasdk.resource.MtgSet;

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
public class QueryBuilderTest extends TestCase {
	private QueryBuilder cardQueryBuilder;
	private QueryBuilder setQueryBuilder;

	@Override
	protected void setUp() throws Exception {
		cardQueryBuilder = new QueryBuilder("cards");
		setQueryBuilder = new QueryBuilder("sets");
	}

	@Test
	public void testBadSetID() {
		assertNull(setQueryBuilder.find("1"));
	}

	@Test
	public void testAllSet() {
		List<Resource> testSetList = setQueryBuilder.all();

		MtgSet testSet = new MtgSet();
		testSet.setGatherercode("1E");

		assertTrue(testSetList.get(0).equals(testSet));
	}
}
