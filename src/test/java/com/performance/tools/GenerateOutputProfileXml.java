package com.performance.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
import org.xml.sax.SAXException;

public class GenerateOutputProfileXml {

	public static void main(String[] args) {
		for(int i = 1; i <= 100; i++){
			processXml(i);
		}
		System.out.println("Done");
	}

	private static void processXml(int iteration) {
		String path = "D:/Xyleme/performance/products/xpe/cloud/25-Aug-2016/TEST_1/customOutputs/";
		File file = new File(path + "perf2_Static SCORM.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
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
			String guid = java.util.UUID.randomUUID().toString();
			File outputProfileXml = new File(path + guid + ".xml");
			if (outputProfileXml.exists()) {
				outputProfileXml.delete();
			}
			root.setAttribute("xy:guid", guid);
			root.getChildNodes().item(1).setTextContent(guid);
			root.getChildNodes().item(5).getAttributes().item(0).setTextContent(java.util.UUID.randomUUID().toString());
			root.getChildNodes().item(7).getAttributes().item(0).setTextContent(java.util.UUID.randomUUID().toString());
			root.getChildNodes().item(7).getChildNodes().item(1).getAttributes().item(0).setTextContent(java.util.UUID.randomUUID().toString());
			root.getChildNodes().item(7).getChildNodes().item(1).setTextContent("PerformanceOutput_" + iteration);
			root.getChildNodes().item(9).getChildNodes().item(1).getChildNodes().item(3).getAttributes().item(0).setTextContent(java.util.UUID.randomUUID().toString());
			root.getChildNodes().item(9).getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(1).getAttributes().item(0).setTextContent(java.util.UUID.randomUUID().toString());
			root.getChildNodes().item(9).getChildNodes().item(1).getChildNodes().item(3).getChildNodes().item(1).setTextContent("PerformanceSkin_" + iteration);
			root.getChildNodes().item(9).getChildNodes().item(1).getChildNodes().item(5).getAttributes().item(0).setTextContent(java.util.UUID.randomUUID().toString());
			//write to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(root);
			StreamResult result = new StreamResult(outputProfileXml);
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
	}

}
