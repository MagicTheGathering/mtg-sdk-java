package io.magicthegathering.javasdk.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public abstract class MTGAPI {
	protected final static String ENDPOINT = "https://api.magicthegathering.io/v1";
	protected static OkHttpClient CLIENT = new OkHttpClient();

	protected static <TYPE> TYPE get(String path, String key,
			Class<TYPE> expectedClass) {
		Gson deserializer = new GsonBuilder().create();
		JsonObject jsonObject = getJsonObject(path, deserializer);
		TYPE returnObject = (TYPE) deserializer.fromJson(jsonObject.get(key),
				expectedClass);
		return returnObject;
	}

	protected static <TYPE> List<TYPE> getList(String path, String key,
			Class<TYPE> expectedClass) {
		Gson deserializer = new GsonBuilder().create();
		List<TYPE> toReturn = new ArrayList<>();
		JsonObject jsonObject = getJsonObject(path, deserializer);
		jsonObject
				.get(key)
				.getAsJsonArray()
				.forEach(
						jsonElement -> toReturn.add(deserializer.fromJson(
								jsonElement, expectedClass)));

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
			throw new RuntimeException(e);
		}

	}
}
