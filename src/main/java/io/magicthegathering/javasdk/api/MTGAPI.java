package io.magicthegathering.javasdk.api;

import io.magicthegathering.javasdk.exception.HttpRequestFailedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Base class for using the magicthegathering.io APIs.
 * 
 * @author thechucklingatom
 * @author nniklas
 *
 */
public abstract class MTGAPI {
	protected static String ENDPOINT = "https://api.magicthegathering.io/v1";
	protected static OkHttpClient CLIENT = new OkHttpClient();

	/**
	 * Make an HTTP GET request to the given path and map the object under the
	 * given key in the JSON of the response to the Java {@link Class} of type
	 * {@link TYPE}.
	 *
	 * @param path Controlled by what is calling the function, currently "sets" or "cards"
	 * depending on the calling class.
	 * @param key The key for the JSON element we are looking for.
	 * @param expectedClass The class type we are expecting to get back.
	 * @return Object of the requested type if found.
	 */
	protected static <TYPE> TYPE get(String path, String key,
			Class<TYPE> expectedClass) {
		Gson deserializer = new GsonBuilder().create();
		JsonObject jsonObject = getJsonObject(path, deserializer);
		return deserializer.fromJson(jsonObject.get(key),
				expectedClass);
	}

	/**
	 * Make an HTTP GET request to the given path and map the array under the
	 * given key in the JSON of the response to a {@link List} of type
	 * {@link TYPE}.
	 *
	 * @param path Controlled by what is calling the function, currently "sets" or "cards"
	 * depending on the calling class.
	 * @param key The key for the JSON element we are looking for.
	 * @param expectedClass The class type we are expecting to get back.
	 * @return
	 */
	protected static <TYPE> List<TYPE> getList(String path, String key,
			Class<TYPE> expectedClass) {
		Gson deserializer = new GsonBuilder().create();
		List<TYPE> toReturn = new ArrayList<>();
		JsonObject jsonObject = getJsonObject(path, deserializer);

		for (JsonElement jsonElement :
				jsonObject.get(key).getAsJsonArray()) {
			toReturn.add(deserializer.fromJson(
					jsonElement, expectedClass));
		}

		return toReturn;
	}

	/**
	 * Deserialize the object to the type expected.
	 *
	 * @param path Controlled by what is calling the function, currently "sets" or "cards"
	 * depending on the calling class.
	 * @param deserializer {@link Gson} object that will be used to deserialize the JSON returned
	 * from the web API.
	 * @return The parsed {@link JsonObject}
	 */
	private static JsonObject getJsonObject(String path, Gson deserializer) {
		String url = String.format("%s/%s", ENDPOINT, path);
		Request request = new Request.Builder().url(url).build();
		Response response;
		try {
			response = CLIENT.newCall(request).execute();
			return deserializer.fromJson(response.body()
					.string(), JsonObject.class);
		} catch (IOException e) {
			throw new HttpRequestFailedException(e);
		}

	}

	/**
	 * Get the list of objects with a filter if there is anything that matches the filters.
	 *
	 * @param path Controlled by what is calling the function, currently "sets" or "cards"
	 * depending on the calling class.
	 * @param key The key for the JSON element we are looking for.
	 * @param expectedClass The class type we are expecting to get back.
	 * @param filters List of filters we want to apply to the search, you can see a full list of
	 * accepted filters at <a href="https://docs.magicthegathering.io/#overview">the web API docs</a>
	 * @return List of found cards.
	 */
	protected static <TYPE> List<TYPE> getList(String path, String key,
			Class<TYPE> expectedClass, List<String> filters){
		path += "?";

		for (String filter :
				filters) {
			path += "&" + filter;
		}

		return getList(path, key, expectedClass);
	}
}
