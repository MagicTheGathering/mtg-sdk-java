package io.magicthegathering.javasdk;

import io.magicthegathering.javasdk.resource.MtgSet;
import io.magicthegathering.javasdk.resource.Resource;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

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


}
