package com.chuchro.ucbx.javax4362.mod9;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import java.io.*;

public class DOMDVDReader {
	public void read(String filepath) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);

		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
			db.setErrorHandler(new ErrorHandler() {
				public void error(SAXParseException spe) {
					System.err.println(spe);
				}
				public void fatalError(SAXParseException spe) {
					System.err.println(spe);
				}
				public void warning(SAXParseException spe) {
					System.out.println(spe);
				}
			});
		} catch (ParserConfigurationException pce) {
			System.err.println(pce);
			System.exit(1);
		}

		Document doc = null;
		try {
			doc = db.parse(new File(filepath));
		} catch (SAXException se) {
			System.err.println(se);
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		NodeList nodeList = doc.getDocumentElement().getChildNodes();
		EchoNodes(nodeList);
	}

	public void  EchoNodes(NodeList nodeList) {
		if (nodeList == null)  return;
		for (int i = 0; i <  nodeList.getLength(); i++) {
			Node child_node = nodeList.item(i);
			if (child_node.getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element)child_node;
				System.out.println("Tag Name:" +  el.getTagName());
				NamedNodeMap attributes =  child_node.getAttributes();
				Node attribute = attributes.getNamedItem("id");
				if (attribute != null) System.out.println("Attr:  " + attribute.getNodeName() + " = " + attribute.getNodeValue());
			} else if  (child_node.getNodeType() == Node.TEXT_NODE) {
				Text tn = (Text)child_node;
				String text = tn.getWholeText().trim();
				if (text.length() > 0) System.out.println("Text:  " + text);
			}
			EchoNodes(child_node.getChildNodes());
		}
}
	
	public static void main(String[] args) {
		DOMDVDReader domDVDReader = new DOMDVDReader();
		domDVDReader.read("dvd.xml");
	}
}