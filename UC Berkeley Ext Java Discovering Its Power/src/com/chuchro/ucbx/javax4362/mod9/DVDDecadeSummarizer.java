package com.chuchro.ucbx.javax4362.mod9;

import javax.xml.stream.*;
import java.util.*;
import java.io.*;

public class DVDDecadeSummarizer {
	public static void main(String[] args) {
		DVDDecadeSummarizer dvdSum = new DVDDecadeSummarizer();
		dvdSum.read("dvd.xml");
	}

	public void read(String filepath) {
		// This TreeMap will hold the decade summary count data until we write it to the output XML file
		// The TreeMap key is the decade and the value is the count of DVDs released in that decade
		TreeMap<Integer, Integer> decadeSummary = new TreeMap<Integer, Integer>();

		// Open the input file, parse the XML and populate the TreeMap with the decade count summary info
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(fileInputStream);
			decadeSummary = readDVDList(xmlStreamReader);
			xmlStreamReader.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Write the decade count summary TreeMap to an output XML file
		try {
			writeXMLDVDSummary(decadeSummary);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	private TreeMap<Integer, Integer> readDVDList(XMLStreamReader reader) throws XMLStreamException {
		String name = "";
		int releaseYear   = 0;
		int releaseDecade = 0;
		int count = 0;
		TreeMap<Integer, Integer> decadeSummary = new TreeMap<Integer, Integer>();

		while (reader.hasNext())	{
			int eventCode = reader.next();
			switch (eventCode) {
			case XMLStreamReader.START_ELEMENT:
				name = reader.getLocalName();
				break;
			case XMLStreamReader.CHARACTERS:
				if (name.equals("release_year"))	{
					releaseYear = Integer.parseInt(reader.getText());
					// Calculate the release decade be subtracting the remainder of the 
					// release year when divided by 10 from the release year itself
					releaseDecade = releaseYear - (releaseYear%10);
					// Determine if the current decade is already in the TreeMap
					if (decadeSummary.containsKey(releaseDecade))	{
						// if so, then increment the count
						count = decadeSummary.get(releaseDecade);
						count++;
					} else {
						// if not, this must be the first DVD of the decade so set the count to 1
						count = 1;
					}
					decadeSummary.put(releaseDecade,count);
				}
				break;
			}
		}
		return decadeSummary;
	}

	private void writeXMLDVDSummary(TreeMap<Integer, Integer> decadeSummary) throws XMLStreamException {
		XMLStreamWriter xw =  null; 
		XMLOutputFactory xof = XMLOutputFactory.newInstance(); 
		try {
			File file = new File("DVDSummary.xml");
			FileWriter fw = new FileWriter(file);
			xw =  xof.createXMLStreamWriter(fw);
			xw.writeStartDocument("1.0");
			xw.writeStartElement("DVD");
				xw.writeStartElement("summary");
					for (Map.Entry<Integer, Integer> entry : decadeSummary.entrySet())	{
						xw.writeStartElement("count");
						xw.writeAttribute("decade", entry.getKey().toString());
						xw.writeCharacters(entry.getValue().toString());
						xw.writeEndElement();
					}
				xw.writeEndElement();	// close the "summary" element
			xw.writeEndElement();		// close the "DVD" element
			xw.writeEndDocument();
			xw.flush();
			xw.close();
			System.out.println("Wrote DVD Summary XML doc to : " + file.getAbsolutePath());
		} catch  (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e)  {
			e.printStackTrace();
		}
	}

}
