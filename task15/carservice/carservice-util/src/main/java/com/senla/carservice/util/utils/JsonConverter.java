package com.senla.carservice.util.utils;

import java.io.BufferedReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.senla.carservice.model.beans.Garage;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.beans.Order;
import com.senla.carservice.model.beans.abstractentity.Entity;

public class JsonConverter {
	private static Logger log = Logger.getLogger(JsonConverter.class.getName());
	private static final Gson gson = new GsonBuilder().create();

	public static String convertToJson(List<? extends Entity> entities) {

		JsonArray jarray = gson.toJsonTree(entities).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("entities", jarray);

		return jsonObject.toString();
	}

	public static String convertToJson(Master master) {

		Gson gson = new Gson();
		String json = gson.toJson(master);
		return json;
	}

	public static String convertToJson(Garage garage) {

		Gson gson = new Gson();
		String json = gson.toJson(garage);
		return json;
	}

	public static String convertToJson(Order order) {

		Gson gson = new Gson();
		String json = gson.toJson(order);
		return json;
	}

	public static JSONObject convertFromJson(BufferedReader reader) {
		StringBuffer buffer = new StringBuffer();
		String line = null;
		try {
			while ((line = reader.readLine()) != null)
				buffer.append(line);

		} catch (Exception e) {
			log.error("Exception", e);
		}
		try {
			JSONObject jsonObject = new JSONObject(buffer.toString());
			return jsonObject;
		} catch (JSONException e) {
			log.error("JSONException", e);
		}
		return null;
	}
}
