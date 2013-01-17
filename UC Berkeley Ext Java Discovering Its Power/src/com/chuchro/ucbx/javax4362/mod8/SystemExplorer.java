package com.chuchro.ucbx.javax4362.mod8;

import java.util.*;
import java.io.File;

public class SystemExplorer {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		System.out.println("Starting at: " + startTime);
		
		Map<String,String> envVars = System.getenv();
		TreeMap<String, String> sortedEnvVars = new TreeMap<String, String>(envVars);
		System.out.println("Printing System Environment Variables");
		for (Map.Entry<String, String> entry : sortedEnvVars.entrySet())	{
			System.out.println("Key: " + entry.getKey() + "\tValue: " + entry.getValue());
		}
		System.out.println("--------------------------------");
		
		Properties props = System.getProperties();
		Set<String> propsKeySet = props.stringPropertyNames();
		ArrayList<String> propsKeyList = new ArrayList<String>(propsKeySet);
		Collections.sort(propsKeyList);
		for (String key : propsKeyList)	{
			System.out.println("Key: " + key + "\tValue: " + props.getProperty(key));
		}
		
		System.out.println("--------------------------------");

		System.out.println("java.io.File.seperator: " + File.separator);
		System.out.println("java.io.File.pathSeparator: " + File.pathSeparator);
		
		long endTime = System.nanoTime();
		System.out.println("Ending at: " + endTime);
		System.out.println("Elapsed time: " + (endTime-startTime));
		
	}

}
