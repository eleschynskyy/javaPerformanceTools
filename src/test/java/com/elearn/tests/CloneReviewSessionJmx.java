package com.elearn.tests;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class CloneReviewSessionJmx {

	public static void main(String[] args) {
		processXml();
	}

	private static HashMap<String, ArrayList<HashMap<String, String>>> readParameters() {
		String path = "D:/Xyleme/performance/products/xpe/review_session/parsedXmlInfo/";
		File file = new File(path + "ReviewSessionInfo.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			HashMap<String, ArrayList<HashMap<String, String>>> rsInfo = new HashMap<String, ArrayList<HashMap<String, String>>>();
			String[] keys = reader.readLine().split(",");
			if (keys != null) {
				String[] dataParts;
				String line;
				while ((line = reader.readLine()) != null) {
					HashMap<String, String> row = new HashMap<String, String>();
					dataParts = line.split(",");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					HashMap<String, String> reviewInfo = new HashMap<String, String>();
					reviewInfo.put("lcms", row.get("lcms"));
					reviewInfo.put("documentGuid", row.get("documentGuid"));
					reviewInfo.put("reviewGuid", row.get("reviewGuid"));
					if(!rsInfo.containsKey(row.get("lcms_client"))){
						ArrayList<HashMap<String, String>> reviews = new ArrayList<HashMap<String, String>>();
						reviews.add(reviewInfo);
						rsInfo.put(row.get("lcms_client"), reviews);
					} else {
						ArrayList<HashMap<String, String>> oldList = rsInfo.get(row.get("lcms_client"));
						oldList.add(reviewInfo);
						rsInfo.put(row.get("lcms_client"), oldList);
					}
				}
			}
			reader.close();
			return rsInfo;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void processXml() {
		HashMap<String, ArrayList<HashMap<String, String>>> rsInfo = readParameters();
		String path = "D:/Xyleme/performance/products/xpe/review_session/parsedXmlInfo/";
		File file = new File(path + "rsTemplate.jmx");
//		File file = new File(path + "TEST_RS_test.jmx");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			File jmxFile = new File(path + "TEST_RS.jmx");
			if (jmxFile.exists()) {
				jmxFile.delete();
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				xmlStringBuilder.append(line);
			}
			reader.close();
			ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			Element root = doc.getDocumentElement();

			//create ThreadGroup for each review session
			int globalRsNumber = 0;
			int argumentsIterator = 3;
			int beforeResultCollectorIterator = 5;
			Node parent = root.getChildNodes().item(1).getChildNodes().item(3);
			Node threadGroup = root.getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(1);
			Node separator = root.getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(2);
			Node hashTree = root.getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(3);
			for(String key: rsInfo.keySet()){
				ArrayList<HashMap<String, String>> reviews = rsInfo.get(key);
				for(int i = 1; i <= reviews.size(); i++){
					globalRsNumber++;
					String lcms = reviews.get(i - 1).get("lcms");
					String documentGuid = reviews.get(i - 1).get("documentGuid");
					String reviewGuid = reviews.get(i - 1).get("reviewGuid");
//					System.out.println(key + "|" + reviews.get(i - 1).get("lcms") + "|" + reviews.get(i - 1).get("documentGuid") + "|" + reviews.get(i - 1).get("reviewGuid"));
					Node arguments = root.getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(argumentsIterator).getChildNodes().item(1).getChildNodes().item(1);
					//set attributes
					arguments.getChildNodes().item(1).getChildNodes().item(3).setTextContent(lcms);
					arguments.getChildNodes().item(3).getChildNodes().item(3).setTextContent(reviewGuid);
					arguments.getChildNodes().item(5).getChildNodes().item(3).setTextContent(documentGuid);
					//clone nodes
					Node beforeResultCollector = root.getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(beforeResultCollectorIterator);
					Node cloneHashTree = hashTree.cloneNode(true);
					Node cloneSeparator = separator.cloneNode(true);
					Node cloneThreadGroup = threadGroup.cloneNode(true);
					//change ThreadGroup title
					cloneThreadGroup.getAttributes().getNamedItem("testname").setTextContent("GO RS " + globalRsNumber);
					//insert nodes
					Node newHashTree = parent.insertBefore(cloneHashTree, beforeResultCollector);
					Node newSeparator = parent.insertBefore(cloneSeparator, newHashTree);
					Node newThreadGroup = parent.insertBefore(cloneThreadGroup, newSeparator);
					argumentsIterator = argumentsIterator + 4;
					beforeResultCollectorIterator = beforeResultCollectorIterator + 3;
				}
			}
			threadGroup.getAttributes().getNamedItem("enabled").setTextContent("false");
			//write to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(root);
			StreamResult result = new StreamResult(jmxFile);
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
