package io.magicthegathering.javasdk.api;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;

/**
 * {@link SetAPI} is used to fetch {@link MtgSet}s from magicthegathering.io
 * 
 * @author nniklas
 */
public class SetAPI extends MTGAPI {
	private static final String RESOURCE_PATH = "sets";

	/**
	 * Returns a {@link MtgSet} based on the given set code.
	 * @param setCode Code to find the specific set.
	 */
	public static MtgSet getSet(String setCode) {
		String path = String.format("%s/%s/", RESOURCE_PATH, setCode);
		MtgSet returnSet = get(path, "set", MtgSet.class);
		if(returnSet != null) {
			returnSet.setCards(CardAPI.getAllCards(new LinkedList<>(Collections.singletonList("set=" + setCode))));
		}
		return returnSet;
	}

	/**
	 * The method that returns all the {@link MtgSet}.
	 * If you want all the card lists populated use
	 * {@link #getAllSetsWithCards()}.
	 * @return A List of all the sets.
	 */
	public static List<MtgSet> getAllSets() {
		return getList(RESOURCE_PATH, "sets", MtgSet.class);
	}

	/**
	 * The method that will generate a booster for the selected {@link MtgSet}
	 * @param setCode Code of which set you want a booster for.
	 * @return the randomized booster for the set.
	 */
	public static List<Card> getBooster(String setCode) {
		String path = String.format("%s/%s/%s/", RESOURCE_PATH, setCode,
				"booster");
		return getList(path, "cards", Card.class);
	}

	/**
	 * Gets a list of {@link MtgSet} based on the provided filters in the
	 * <a href="https://docs.magicthegathering.io/#sets">web API documentation.</a>
	 * @param filters List of string filters
	 * @return The list of {@link MtgSet}s that was found by the filter.
	 */
	public static List<MtgSet> getAllSets(List<String> filters){
		return getList(RESOURCE_PATH, "sets", MtgSet.class, filters);
	}

	/**
	 * Gets a list of {@link MtgSet} with all the card objects populated.
	 * Performance will be degraded because of all the Api calls that will
	 * happen.
	 * @return A list of all the sets with cards populated.
	 */
	public static List<MtgSet> getAllSetsWithCards() {
		List<MtgSet> returnList = getList(RESOURCE_PATH, "sets", MtgSet.class);
		for(MtgSet set : returnList){
			set.setCards(CardAPI.getAllCards(new LinkedList<>(Collections.singletonList("set=" + set.getCode()))));
		}
		return returnList;
	}
}
