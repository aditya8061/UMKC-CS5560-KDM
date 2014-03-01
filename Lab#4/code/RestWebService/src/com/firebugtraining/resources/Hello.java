package com.firebugtraining.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;


@Path("/json/metallica")
public class Hello {
	
	int RANDOM_KEY_LENGTH = 6;
	
	@GET
	@Path("/{userID}")
	@Produces(MediaType.TEXT_HTML)
	public String service(@PathParam("userID") String userID) throws JSONException, FileNotFoundException {
		
		Scanner ss = new Scanner(new File("C:/Users/Avinash/workspace-service/FirstRestWebService/input.dat"));
		String line = null;
		String result = null;
		RecommendationList rl = new RecommendationList();

		while(ss.hasNext()) {
			line = ss.nextLine();
			result += rl.getList(line, userID);
			System.out.println(result);
		}
		result = result.replaceAll("null", "");
		return result;
	}
 }