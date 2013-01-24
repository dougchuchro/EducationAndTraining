package com.chuchro.ucbx.javax4362.mod9;

import javax.xml.stream.*;
import java.io.*;
import java.util.*;

public class DVDDecadeSummarizer {
	public static void main(String[] args) {
		DVDDecadeSummarizer dvdSum = new DVDDecadeSummarizer();
		dvdSum.read("dvd.xml");
	}

	public void read(String filepath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(filepath);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(fileInputStream);
			while (xmlStreamReader.hasNext()) {
				readDVDList(xmlStreamReader);
			}
			xmlStreamReader.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void readDVDList(XMLStreamReader reader) throws XMLStreamException {
		String name = "";
		int releaseYear   = 0;
		int releaseDecade = 0;
		int count = 0;
		TreeMap<Integer, Integer> decadeSummary = new TreeMap<Integer, Integer>();

		int eventCode = reader.next();
		switch (eventCode) {
		case XMLStreamReader.START_ELEMENT:
			String key = reader.getLocalName();
			if (key.equals("DVD")) {
				while (reader.hasNext()) {
					eventCode = reader.next();
					switch (eventCode) {
					case XMLStreamReader.START_ELEMENT:
						name = reader.getLocalName();
						break;
					case XMLStreamReader.CHARACTERS:
						if (name.equals("release_year"))	{
							releaseYear = Integer.parseInt(reader.getText());
							releaseDecade = releaseYear/10 * 10;
							System.out.println("Element  name= " + name + " value=" + releaseYear + " release decade= " + releaseDecade);
							if (decadeSummary.containsKey(releaseDecade))	{
								count = (int)decadeSummary.get(releaseDecade);
								count++;
							} else {
								count = 1;
							}
							decadeSummary.put(releaseDecade,count);
							System.out.println("B:Decade Summary: " + decadeSummary);
						}
						break;
					}
				}
				return;
			}
			break;
		}
	}

}
