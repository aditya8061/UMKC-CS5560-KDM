package com.firebugtraining.resources;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class RecommendationList {

	public String getList(String line, String userID) throws FileNotFoundException, JSONException {
		
		HashMap<String, String> map = new HashMap<>();
		String list = "";
		String array[] = line.split(" : ");
		
		MovieNames mn = new MovieNames();
		JSONObject j = new JSONObject();
		JSONArray a;
		if(userID.equalsIgnoreCase(array[0])) {
			String recommendedList[] = array[1].split(" ");
			for(String movieID : recommendedList) {
				map.put(movieID, mn.getNames(movieID));
				System.out.println("Movie Name is " + mn.getNames(movieID));
				list += ", "+ mn.getNames(movieID);
			}
		}
		return list;
		
	}

}
