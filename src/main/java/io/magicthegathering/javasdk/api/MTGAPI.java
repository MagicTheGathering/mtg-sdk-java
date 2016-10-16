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
	protected final static String ENDPOINT = "https://api.magicthegathering.io/v1";
	protected static OkHttpClient CLIENT = new OkHttpClient();

	/**
	 * Make an HTTP GET request to the given path and map the object under the
	 * given key in the JSON of the response to the Java {@link Class} of type
	 * {@link TYPE}.
	 */
	protected static <TYPE> TYPE get(String path, String key,
			Class<TYPE> expectedClass) {
		Gson deserializer = new GsonBuilder().create();
		JsonObject jsonObject = getJsonObject(path, deserializer);
		TYPE returnObject = (TYPE) deserializer.fromJson(jsonObject.get(key),
				expectedClass);
		return returnObject;
	}

	/**
	 * Make an HTTP GET request to the given path and map the array under the
	 * given key in the JSON of the response to a {@link List} of type
	 * {@link TYPE}.
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

	private static JsonObject getJsonObject(String path, Gson deserializer) {
		String url = String.format("%s/%s", ENDPOINT, path);
		Request request = new Request.Builder().url(url).build();
		Response response;
		try {
			response = CLIENT.newCall(request).execute();
			JsonObject jsonObject = deserializer.fromJson(response.body()
					.string(), JsonObject.class);
			return jsonObject;
		} catch (IOException e) {
			throw new HttpRequestFailedException(e);
		}

	}
}
