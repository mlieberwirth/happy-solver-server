package happysolverserver.service;

import static io.micrometer.core.instrument.util.StringUtils.isBlank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonSerializer {

	public static <T> T fromJson(String data, Class<T> clazz) {

		if (isBlank(data)) {
			return null;
		}

		try {
			Gson gson = createGson(false);
			return gson.fromJson(data, clazz);
		} catch (JsonSyntaxException e) {
			log.error("Unable to parse string. " + data, e);
			return null;
		}
	}

	public static String toJson(Object data) {
		return toJson(data, true);
	}

	public static String toJson(Object data, boolean isPretty) {
		if (data == null) {
			return "";
		}

		try {
			Gson gson = createGson(isPretty);
			return gson.toJson(data);
		} catch (JsonSyntaxException e) {
			log.error("Unable to create string.", e);
			return null;
		}
	}

	private static Gson createGson(boolean isPretty) {
		GsonBuilder builder = new GsonBuilder();
		if (isPretty) {
			builder.setPrettyPrinting();
		}
		return builder.create();
	}
}
