package com.chuchro.ucbx.javax4362.mod8;

import java.io.*;
import java.net.*;

public class ReadThisResource {

	public static void main(String[] args)	{
		// Check to make sure arguments were provided
		if (args.length == 0 )	{
			System.out.println("No arguements provided");
			usageMessage();
			return;			
		}
		String resource = args[0];

		// Get the "Lines to Read" argument
		int lineNum = 0;  // default to print all lines of provided resource name
		if (args.length > 1 )	{
			String sLineNum = args[1];
			try	{
				lineNum = Integer.valueOf(sLineNum);
			} catch (NumberFormatException e) {
				System.out.println("Second Arguement must be an integer, you entered: " + sLineNum);
				usageMessage();
				return;
			}
		}
		
		// Get a URI base on the resource name provide
		URI uri = null;
		try {
			uri = new URI(resource);
		} catch (URISyntaxException e) {
			System.out.println("Could not format a URI with the resource name you provided: " + resource);
			usageMessage();
			return;
		}

		// Call getBufferedReader() to get a Buffered Reader based on the URI
		BufferedReader reader = getBufferedReader(uri);
		if (reader == null)	{
			System.out.println("Could not read the specified resource: " + resource);
			usageMessage();
			return;
		}
		
		// Now that we have our BufferedReader, let's read and print the contents
		// either all, first n, or last n lines depending on the second arguement provided (lineNum)
		String inputLine;
        try {
        	if (lineNum == 0)	{	// default is to print all lines of resource
        		while ((inputLine = reader.readLine()) != null) 
        			System.out.println(inputLine);
        	} else {
        		// print first n lines of resource
        		if (lineNum > 0)	{
            		while ((inputLine = reader.readLine()) != null && lineNum > 0)	{
            			System.out.println(inputLine);
            			lineNum--;
            		}
           		// print last n lines of resource
        		} else {
        			int linesInFile = linesInFile(getBufferedReader(uri));
        			int lastLinesStart = linesInFile + lineNum;
        			// first move the cursor to the n to last line in file
        			for (int i=0; i<lastLinesStart; i++)	{
        				reader.readLine();
        			}
            		while ((inputLine = reader.readLine()) != null )	{
            			System.out.println(inputLine);
            			lineNum--;
            		}
        		}
        	}
        	reader.close();
        } catch (IOException e) {
			System.out.println("Could not read specified resource: " + resource);
			e.printStackTrace();
			return;
        }
        
	}

	private static int linesInFile(BufferedReader br) throws IOException 	{
		int linesInFile = 0;
		while (br.readLine() != null)	{
			linesInFile++;
		}
		return linesInFile;	
	}
	
	private static BufferedReader getBufferedReader(URI uri) {
		BufferedReader br = null;
		try {
			// if the URI has no scheme (i.e. protocol), assume it's a local file
			if (uri.getScheme() == null)	{
				br = fileToBufferedReader(uri.toString());
			} else {
			// else get a buffered reader based on the scheme/protocol
				switch (uri.getScheme())	{
					case "http":	br = urlToBufferedReader(uri); break;
					case "file":	br = fileToBufferedReader(uri); break;
				}
			}
		} catch (FileNotFoundException e)	{
			System.out.println("Could not find the file with the name you provided: " + uri.toString());
		} catch (MalformedURLException e)	{
			System.out.println("Could not format a URL with the resource name you provided: " + uri.toString());
		} catch (IOException e)	{
			System.out.println("Access the URL you provided: " + uri.toString());
		} catch (IllegalArgumentException e)	{
			System.out.println("Could not process the URL: " + uri.toString());
			System.out.println("Here's the problem: " + e.getMessage());
		}
		return br;
	}
	
	// We have 3 methods that return a BufferedReader, the only difference being the input type: URI, String, or File
	private static BufferedReader fileToBufferedReader(URI fileUri) throws FileNotFoundException	{
		File f = new File(fileUri);
		return fileToBufferedReader(f);
	}

	private static BufferedReader fileToBufferedReader(String file) throws FileNotFoundException	{
		File f = new File(file);
		return fileToBufferedReader(f);
	}

	private static BufferedReader fileToBufferedReader(File file) throws FileNotFoundException	{
		FileReader fr = new FileReader(file);
		return new BufferedReader(fr);
	}

	private static BufferedReader urlToBufferedReader(URI webUri) throws IOException, MalformedURLException	{
		URL url = webUri.toURL();
		URLConnection connect = url.openConnection();
		InputStream is = connect.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		return new BufferedReader(isr);
	}

	private static void usageMessage()	{
		System.out.println("Usage: java com.chuchro.ucbx.javax4362.mod8.ReadThisResource <URI or filename> <lines to read>");
		System.out.println("URI should be like http://hostname/path/to/resource ex: http://www.google.com/search");
		System.out.println("or URI can be like file:///path/to/file/filename.ext ex: file:///c:/temp/testfile.txt");
		System.out.println("filename /path/to/file/filename.ext, where path to file is an optional relative or absolute path;  ex: c:/temp/testfile.txt or ../temp/testfile.txt or testfile.txt");
		System.out.println("lines to read is optional, and can be any integer, positive, negative, or zero");
		System.out.println("if lines to read is positive n, the first n lines are printed");
		System.out.println("if lines to read is negative n, the last n lines are printed");
		System.out.println("if lines to read is zero or not present, all lines are printed");
		return;
	}
}
