package com.chuchro.ucbx.javax4362.mod9;

import  javax.xml.stream.*;
import java.io.*;

public class StaxWriter {

	public static void main(String[] args) {
		XMLStreamWriter xw =  null; 
		XMLOutputFactory xof = XMLOutputFactory.newInstance(); 
		try {
			xw =  xof.createXMLStreamWriter(new FileWriter("dvd2.xml"));
			xw.writeStartDocument("1.0");
			xw.writeDTD("<!DOCTYPE  DVDS SYSTEM \"dvd2.dtd\">");
			xw.writeStartElement("DVDS");
				xw.writeStartElement("DVD");
				xw.writeAttribute("id",  "4");

					xw.writeStartElement("title");
					xw.writeCharacters("Star  Wars: Attack of the Clones");
					xw.writeEndElement();

					String []  performers = {
							"Ewan  McGregor",
							"Natalie  Portman", 
							"Hayden  Christensen"
					};
					xw.writeStartElement("performers");
					for (String  performer : performers) {
						xw.writeStartElement("performer");
						xw.writeCharacters(performer);
						xw.writeEndElement();
					}
					xw.writeEndElement();
					
					xw.writeStartElement("discs");
					xw.writeCharacters("2");
					xw.writeEndElement();
					
					xw.writeStartElement("price");
					xw.writeCharacters("14.99");
					xw.writeEndElement();
					
				xw.writeEndElement();  // close the DVD tag
			xw.writeEndElement();  // close the DVDS tag

			xw.writeEndDocument();
			xw.flush();
			xw.close(); 
		} catch  (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e)  {
			e.printStackTrace();
		}
	}

}
