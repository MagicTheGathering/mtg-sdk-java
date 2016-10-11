package io.magicthegathering.javasdk.api;

import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.MtgSet;

import java.util.List;

/**
 * {@link SetAPI} is used to fetch {@link MtgSet}s from magicthegathering.io
 * 
 * @author nniklas
 */
public class SetAPI extends MTGAPI {
	private static final String RESOURCE_PATH = "sets";

	/**
	 * Returns a {@link MtgSet} based on the given set code.
	 */
	public static MtgSet getSet(String setCode) {
		String path = String.format("%s/%s/", RESOURCE_PATH, setCode);
		return get(path, "set", MtgSet.class);
	}


	public static List<MtgSet> getAllSets() {
		return getList(RESOURCE_PATH, "sets", MtgSet.class);
	}
}
