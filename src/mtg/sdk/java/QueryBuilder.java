package mtg.sdk.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This file is part of mtgsdk.
 * https://github.com/MagicTheGathering/mtg-sdk-java
 * <p>
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT-license
 * <p>
 * Created by thechucklingatom on 8/10/2016.
 *
 * @author thechucklingatom
 */
public class QueryBuilder {

	private String type;
	private OkHttpClient client;

	//currently just in this file, if I end up using it a bunch or in more than one file
	//I will move to a resource file.
	private final String endpoint = "https://api.magicthegathering.io/v1";

	/**
	 * Creates a new QueryBuilder, used to build the string that we query the
	 * web api with.
	 *
	 * @param type "sets" or "cards" tells the builder so it can request the correct resource
	 *             from the api.
	 */
	public QueryBuilder(String type) {
		this.type = type;
		client = new OkHttpClient();
	}

	/**
	 * Find a card or set based off its multiverse id or
	 * @param id
	 * @return
	 */
	public Resource find(String id){
		String url = String.format("%s/%s/%s", endpoint, type, id);

		//may be moving all the Rest access to its own class for better testing.
		Request request = new Request.Builder()
				.url(url)
				.build();

		Resource toReturn = null;

		try {
			Response response = client.newCall(request).execute();

			Gson deserializer = new GsonBuilder().create();

			JsonObject jsonObject = deserializer.fromJson(
					response.body()
					.string(),
					JsonObject.class);

			//looking at way to make generic, I know there is a way but I want to stick with
			//just the one JSON library. I also think I know the way just trying to get
			//something testable.
			if (type.equalsIgnoreCase("sets")){
				toReturn = deserializer.fromJson(response.body().string(), Set.class);
			} else if (type.equalsIgnoreCase("cards")){
				toReturn = deserializer.fromJson(jsonObject.get("card"), Card.class);
			}

			return toReturn;
		}catch(IOException ex){
			return null;
		}
	}

}
