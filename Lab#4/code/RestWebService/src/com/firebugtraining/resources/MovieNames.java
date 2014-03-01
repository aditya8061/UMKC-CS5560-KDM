package com.firebugtraining.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MovieNames {

	public String getNames(String movieID) throws FileNotFoundException {
		
		Scanner ss = new Scanner(new File("C:/Users/Avinash/workspace-service/FirstRestWebService/movie.dat"));
		String line = null;
		
		while(ss.hasNext()) {
			line = ss.nextLine();
			String movieList[] = line.split(":");
			if(movieList[0].equals(movieID)) {
				System.out.println(movieList[0]);
				return movieList[1];
			}
		}

		
		return null;
	}

}
