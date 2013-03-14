package com.chuchro.ucbx.javax4362.mod9;

import java.io.*;
import java.util.*;
import javax.xml.stream.*;

public class DVD {
	public String id;
	public String title;
	public String[] performers;
	public String discs;
	public String price;
	public String release_year;

	public List<DVD> readXmlFile (String filename)	{
		List<DVD> dvdList = new ArrayList<DVD>();
		try {
			FileInputStream fileInputStream = new FileInputStream(filename);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(fileInputStream);
			while (xmlStreamReader.hasNext()) {
				dvdList = readDVDList(xmlStreamReader);
			}
			xmlStreamReader.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return dvdList;
	}		

	private List<DVD> readDVDList(XMLStreamReader reader) throws XMLStreamException {
		List<DVD> dvdList = new ArrayList<DVD>();
		while (reader.hasNext())	{
			DVD dvd = new DVD();
			int eventCode = reader.next();
			switch (eventCode) {
			case XMLStreamReader.START_ELEMENT:
				String key = reader.getLocalName();
				if (key.equals("DVD")) {
					dvd = readDVD(reader);
					dvdList.add(dvd);
				}
				break;
			}
		}
		return dvdList;
	}

	private DVD readDVD(XMLStreamReader reader) throws XMLStreamException {
		String name = "";
		String value = "";

		int nAttributes = reader.getAttributeCount();
		String avalue = reader.getAttributeValue(0);
		System.out.println("attribute  value: " + avalue + " attribute count: "	+ nAttributes);

		while (reader.hasNext()) {
			int eventCode = reader.next();
			switch (eventCode) {
			case XMLStreamReader.START_ELEMENT:
				name = reader.getLocalName();
				break;
			case XMLStreamReader.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals("DVD"))
					return;
				break;
			case XMLStreamReader.CHARACTERS:
				value = reader.getText();
				System.out.println("Element  name= " + name + " value=" + value);
				break;
			}
		}
		return;
	}	
	public void writeXmlFile (String filename)	{
		//		...
	}

}