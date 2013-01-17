package com.chuchro.ucbx.javax4362.mod8;

import java.net.*;
import java.io.*;

public class ReadURL {
    public static void main(String[] args) {
//        for (String url : args) {
            try {
            	readURL("http://ubuntu/");
//                readURL(url);
            } catch (Exception e) {
//                System.err.println(url + ":");
                e.printStackTrace();
            }
//        }
    }

	private static void readURL(String name)
    		throws MalformedURLException, IOException
	{
        URL url = new URL(name);
        URLConnection connect = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) 
        	System.out.println(inputLine);
        in.close();
    }
}

