package io.magicthegathering.javasdk.api;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.magicthegathering.javasdk.resource.Card;
import io.magicthegathering.javasdk.resource.Resource;
import io.magicthegathering.javasdk.resource.Set;

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
		return get(path, Card.class);
	}


	
}
