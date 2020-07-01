package io.magicthegathering.javasdk.api;

import io.magicthegathering.javasdk.exception.HttpRequestFailedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 */
public abstract class MTGAPI {
	protected static String ENDPOINT = "https://api.magicthegathering.io/v1";
	private static long connectTimeout = 10;
	private static long writeTimeout = 10;
	private static long readTimeout = 60;

	private static OkHttpClient client; // Lazy initialization of client, so is not created twice, if a timeout is set.
	private static String DELIM_LINK = ",";
	private static String DELIM_LINK_PARAM = ";";

	/**
	 * @return The HTTP-Client to use for new connections
	 */
	private static OkHttpClient getClient() {
		if (client == null) {
			recreateClient();
		}
		return client;
	}

	/**
	 * @return The connectTimeout for the HTTP-Client
	 * @see OkHttpClient.Builder#connectTimeout(long, TimeUnit)
	 */
	public static long getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * Sets the {@link OkHttpClient.Builder#connectTimeout(long, TimeUnit)} to the specified value and creates a new client using it
	 * @param connectTimeout The timeout to set
	 */
	public static void setConnectTimeout(long connectTimeout) {
		MTGAPI.connectTimeout = connectTimeout;
		if (client != null) { // Only change the timeout on the client, if the client was already created.
			recreateClient();
		}
	}

	/**
	 * @return The writeTimeout for the HTTP-Client
	 * @see OkHttpClient.Builder#writeTimeout(long, TimeUnit)
	 */
	public static long getWriteTimeout() {
		return writeTimeout;
	}

	/**
	 * Sets the {@link OkHttpClient.Builder#writeTimeout(long, TimeUnit)} to the specified value and creates a new client using it
	 * @param writeTimeout The timeout to set
	 */
	public static void setWriteTimeout(long writeTimeout) {
		MTGAPI.writeTimeout = writeTimeout;
		if (client != null) { // Only change the timeout on the client, if the client was already created.
			recreateClient();
		}
	}

	/**
	 * @return The readTimeout for the HTTP-Client
	 * @see OkHttpClient.Builder#writeTimeout(long, TimeUnit)
	 */
	public static long getReadTimeout() {
		return readTimeout;
	}

	/**
	 * Sets the {@link OkHttpClient.Builder#readTimeout(long, TimeUnit)} to the specified value and creates a new client using it
	 * @param readTimeout The timeout to set
	 */
	public static void setReadTimeout(long readTimeout) {
		MTGAPI.readTimeout = readTimeout;
		if (client != null) { // Only change the timeout on the client, if the client was already created.
			recreateClient();
		}
	}

	/**
	 * Creates a new {@link OkHttpClient.Builder}, sets the timeouts and builds a new {@link OkHttpClient} with it.
	 */
	private static void recreateClient() {
		OkHttpClient.Builder builder;
		if (client == null) {
			builder = new OkHttpClient.Builder();
		} else {
			builder = client.newBuilder();
		}
		client = builder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
				.writeTimeout(writeTimeout, TimeUnit.SECONDS)
				.readTimeout(readTimeout, TimeUnit.SECONDS)
				.build();
	}

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
		JsonObject jsonObject = getJsonObject(path, deserializer).get(0);
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
	 * @return a {@link List} of {@link TYPE}
	 */
	protected static <TYPE> List<TYPE> getList(String path, String key,
			Class<TYPE> expectedClass) {
		Gson deserializer = new GsonBuilder().create();
		List<TYPE> toReturn = new ArrayList<>();
		List<JsonObject> jsonObjectList = getJsonObject(path, deserializer);

		for(JsonObject jsonObject : jsonObjectList) {
			for (JsonElement jsonElement :
					jsonObject.get(key).getAsJsonArray()) {
				toReturn.add(deserializer.fromJson(
						jsonElement, expectedClass));
			}
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
	private static List<JsonObject> getJsonObject(String path, Gson deserializer) {
		String url = String.format("%s/%s", ENDPOINT, path);
		Request request = new Request.Builder().url(url).build();
		Response response;
		try {
			response = getClient().newCall(request).execute();

			ArrayList<JsonObject> objectList = new ArrayList<>();
			String linkHeader = response.headers().get("Link");
			if (linkHeader == null || linkHeader.isEmpty() || path.contains("page=")) {
				objectList.add(deserializer.fromJson(response.body()
						.string(), JsonObject.class));
				return objectList;
			} else {
				int numberOfPages = 0;
				String[] linkStrings = linkHeader.split(DELIM_LINK);
				List<String[]> paramList = new ArrayList<>();
				for (String link : linkStrings) {
					paramList.add(link.split(DELIM_LINK_PARAM));
				}
				for (String[] params : paramList) {
					if (params[1].contains("last")) {
						Matcher matcher = Pattern.compile("page=[0-9]+").matcher(params[0]);
						numberOfPages = (matcher.find()) ? Integer.parseInt(matcher.group().substring(5)) : 0;
					}
				}

				objectList.add(deserializer.fromJson(response.body().string(), JsonObject.class));

				if (!url.contains("?")) {
					url += "?";
				}

				for(int i = 2; i <= numberOfPages; i++){
					request = new Request.Builder().url(url + "&page=" + i).build();
					response = getClient().newCall(request).execute();
					objectList.add(deserializer.fromJson(response.body().string(), JsonObject.class));
				}

				return objectList;
			}
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
			Class<TYPE> expectedClass, List<String> filters) {
		StringBuilder tempPath = new StringBuilder(path);
		tempPath.append("?");

		for (String filter :
				filters) {
			tempPath.append(filter).append('&');
		}

		return getList(tempPath.substring(0, tempPath.length() - 1), key, expectedClass);
	}
}
