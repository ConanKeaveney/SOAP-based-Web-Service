package com;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.*;
//http://localhost:8080/SOAPExample2Client/sampleApiProxy/TestClient.jsp?endpoint=http://localhost:14036/SOAPExample2/services/api
public class api {
	public String start(String station) throws Exception {
		// Origin, Due Time, Destination, Departure Time, Arrival Time.
		URL url = new URL(
				"http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML_WithNumMins?StationCode=MYNTH&NumMins=90&format=xml");
		
		
		switch (station) {
		case "Maynooth":
			url = new URL(
					"http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML_WithNumMins?StationCode=MYNTH&NumMins=90&format=xml");
			break;
		case "Connolly":
			url = new URL(
					"http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML_WithNumMins?StationCode=CNLLY&NumMins=90&format=xml");
			break;
		default:
			return "this station cannot be found";
		}

		URLConnection connection = url.openConnection();

		Document doc = parseXML(connection.getInputStream());
		NodeList orNodes = doc.getElementsByTagName("Origin");
		NodeList dueNodes = doc.getElementsByTagName("Duein");
		NodeList destNodes = doc.getElementsByTagName("Destination");
		NodeList deptNodes = doc.getElementsByTagName("Origintime");
		NodeList arrNodes = doc.getElementsByTagName("Destinationtime");

		ArrayList<String[]> outerArr = new ArrayList<String[]>();
		String[] col1 = { "id", "Origin", "Due In", "Destination", "Departure Time", "Arrival Time" };
		outerArr.add(col1);

		for (int i = 0; i < destNodes.getLength(); i++)

		{
			String[] toadd = { Integer.toString(i), orNodes.item(i).getTextContent(),
					dueNodes.item(i).getTextContent() + " min", destNodes.item(i).getTextContent(),
					deptNodes.item(i).getTextContent(), arrNodes.item(i).getTextContent() };
			outerArr.add(toadd);
			System.out.println("Origin: " + orNodes.item(i).getTextContent() + "\n" + "Due Time: "
					+ dueNodes.item(i).getTextContent() + "mins" + "\n" + "Destination: "
					+ destNodes.item(i).getTextContent() + "\n" + "Departure Time: "
					+ deptNodes.item(i).getTextContent() + "\n" + "Arrival Time: " + arrNodes.item(i).getTextContent()
					+ "\n");

		}

		String result = "The next train is due in " + dueNodes.item(0).getTextContent() + " minutes. It is going from "
				+ orNodes.item(0).getTextContent() + " to " + destNodes.item(0).getTextContent();

		return result;
	}

	private Document parseXML(InputStream stream) throws Exception {
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

			doc = objDocumentBuilder.parse(stream);
		} catch (Exception ex) {
			throw ex;
		}

		return doc;
	}
	
	
	public String add(int a,int b){
		return String.valueOf(a+b);
	}
	public String sub(int a,int b){
		return String.valueOf(a-b);
	}

}
