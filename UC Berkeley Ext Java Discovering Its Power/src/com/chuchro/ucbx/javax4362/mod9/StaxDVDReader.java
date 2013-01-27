package com.chuchro.ucbx.javax4362.mod9;

import javax.xml.stream.*;
import java.io.*;

public class StaxDVDReader {
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
		int eventCode = reader.next();
		switch (eventCode) {
		case XMLStreamReader.START_ELEMENT:
			String key = reader.getLocalName();
			if (key.equals("DVD")) {
				readDVD(reader);
			}
			break;
		}
	}

	private void readDVD(XMLStreamReader reader) throws XMLStreamException {
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

	public static void main(String[] args) {
		StaxDVDReader dvdReader = new StaxDVDReader();
		dvdReader.read("dvd.xml");
	}
}