package io.magicthegathering.javasdk.api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public abstract class MTGAPI {
	protected final static String ENDPOINT = "https://api.magicthegathering.io/v1";
	protected static OkHttpClient CLIENT = new OkHttpClient();

	protected static <TYPE> TYPE get(String path, Class<TYPE> expectedClass) {
		String url = String.format("%s/%s", ENDPOINT, path);
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = CLIENT.newCall(request).execute();
			Gson deserializer = new GsonBuilder().create();
			JsonObject jsonObject = deserializer.fromJson(response.body()
					.string(), JsonObject.class);
			String resourceName = expectedClass.getSimpleName().toLowerCase();
			TYPE returnObject = (TYPE) deserializer.fromJson(jsonObject.get(resourceName), expectedClass);
			return returnObject;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
