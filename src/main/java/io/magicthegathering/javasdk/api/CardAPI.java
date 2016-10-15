package io.magicthegathering.javasdk.api;

import io.magicthegathering.javasdk.resource.Card;

import java.util.List;

/**
 * {@link CardAPI} is used to fetch {@link Card}s from magicthegathering.io
 * 
 * @author nniklas
 */
public class CardAPI extends MTGAPI {
	private static final String RESOURCE_PATH = "cards";

	/**
	 * Returns a {@link Card} based on the given multiverseid
	 */
	public static Card getCard(int multiverseId) {
		String path = String.format("%s/%s/", RESOURCE_PATH, multiverseId);
		return get(path, "card", Card.class);
	}

	/**
	 * Returns all the available {@link Card}s as a list.
	 */
	public static List<Card> getAllCards() {
		return getList(RESOURCE_PATH, "cards", Card.class);
	}

	/**
	 * Returns a {@link List} of all card types as {@link String}s.
	 * 
	 * @see <a href="https://docs.magicthegathering.io/#card-types">
	 * 		https://docs.magicthegathering.io/#card-types</a>
	 */
	public static List<String> getAllCardTypes() {
		String path = "types";
		List<String> cardTypes = getList(path, "types", String.class);
		return cardTypes;
	}
}
