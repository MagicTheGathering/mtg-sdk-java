package mtg.sdk.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	 * Get all the {@link Card}s or {@link Set}s.
	 *
	 * @return List of all the {@link Card}s or {@link Set}s
	 */
	public List<Resource> all(){
		String url = String.format("%s/%s", endpoint, type);

		Request request = new Request
				.Builder()
				.url(url)
				.build();

		List<Resource> toReturn = new ArrayList<>();

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
				jsonObject.get("sets")
						.getAsJsonArray()
						.forEach(
								jsonElement ->
										toReturn.add(deserializer
												.fromJson(jsonElement, Set.class)));

			} else if (type.equalsIgnoreCase("cards")) {
				jsonObject.get("cards")
						.getAsJsonArray()
						.forEach(
								jsonElement ->
										toReturn.add(deserializer
												.fromJson(jsonElement, Card.class)));
			}
			return toReturn;
		}catch(IOException ex){
			return null;
		}
	}

	/**
	 * Overloaded all, allows for passing the specific reference type that you want.
	 *
	 * @param resourceType "sets" or "cards"
	 *
	 * @return List of all the {@link Card}s or {@link Set}s
	 */
	public List<Resource> all(String resourceType){
		String url = String.format("%s/%s", endpoint, resourceType);

		Request request = new Request
				.Builder()
				.url(url)
				.build();

		List<Resource> toReturn = new ArrayList<>();

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
				jsonObject.get("sets")
						.getAsJsonArray()
						.forEach(
								jsonElement ->
										toReturn.add(deserializer
												.fromJson(jsonElement, Set.class)));

			} else if (type.equalsIgnoreCase("cards")) {
				jsonObject.get("cards")
						.getAsJsonArray()
						.forEach(
								jsonElement ->
										toReturn.add(deserializer
												.fromJson(jsonElement, Card.class)));
			}
			return toReturn;
		}catch(IOException ex){
			return null;
		}
	}

	/**
	 * Find a card or set based off its multiverse id or
	 * @param id The Multiverse id of the {@link Card} or the code of the {@link Set}.
	 * @return The {@link Card} or {@link Set} bundled under the {@link Resource} class.
	 */
	public Resource find(String id){
		String url = String.format("%s/%s/%s", endpoint, type, id);

		//may be moving all the Rest access to its own class for better testing.
		Request request = new Request
				.Builder()
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
				toReturn = deserializer.fromJson(jsonObject.get("set"), Set.class);
			} else if (type.equalsIgnoreCase("cards")){
				toReturn = deserializer.fromJson(jsonObject.get("card"), Card.class);
			}

			return toReturn;
		}catch(IOException ex){
			return null;
		}
	}

	/**
	 * Find a card or set based off its multiverse id or
	 * @param id The Multiverse id of the {@link Card} or the code of the {@link Set}.
	 * @param resourceType "sets" or "cards", allows filtering without needing to declare a type
	 *                     in the constructor.
	 * @return The {@link Card} or {@link Set} bundled under the {@link Resource} class.
	 */
	public Resource find(String id, String resourceType){
		String url = String.format("%s/%s/%s", endpoint, resourceType, id);

		//may be moving all the Rest access to its own class for better testing.
		Request request = new Request
				.Builder()
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
			if (resourceType.equalsIgnoreCase("sets")){
				toReturn = deserializer.fromJson(jsonObject.get("set"), Set.class);
			} else if (resourceType.equalsIgnoreCase("cards")){
				toReturn = deserializer.fromJson(jsonObject.get("card"), Card.class);
			}

			return toReturn;
		}catch(IOException ex){
			return null;
		}
	}

}
