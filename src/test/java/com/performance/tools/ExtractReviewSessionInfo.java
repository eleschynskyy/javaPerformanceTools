package com.performance.tools;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractReviewSessionInfo {

	private static String TEST_FOLDER = "22-Feb-2016";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/review_session/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";
	private static String FILENAME = "1000 (Web Course).xml";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/graph/data/";

	public static void main(String[] args) {
		processXml();
	}

	private static void processXml() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		File file = new File(PATHNAME + FILENAME);
		try {
			File containerGuidFile = new File(OUTPUT_PATH + "CONTAINERS.txt");
			File fragmentGuidFile = new File(OUTPUT_PATH + "FRAGMENTS.txt");
			if (containerGuidFile.exists()) {
				containerGuidFile.delete();
			}
			if (fragmentGuidFile.exists()) {
				fragmentGuidFile.delete();
			}
			FileWriter containerGuidWriter = new FileWriter(containerGuidFile);
			FileWriter fragmentGuidWriter = new FileWriter(fragmentGuidFile);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String xml = reader.readLine();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder xmlStringBuilder = new StringBuilder();
			xmlStringBuilder.append(xml);
			ByteArrayInputStream input = new ByteArrayInputStream(
					xmlStringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);
			Element root = doc.getDocumentElement();
			for (int i = 0; i <= root.getChildNodes().getLength() - 1; i++) {
				Node child = root.getChildNodes().item(i);
				if (child.getNodeName().equals("Container")) {
					if (child.getFirstChild().getTextContent()
							.startsWith("Container")) {
						for (int j = 1; j <= child.getChildNodes().getLength() - 1; j++) {
							Pattern guidPattern = Pattern
									.compile("xy:guid=\"(.+?)\"");
							Matcher guidMatcher;
							for (int k = 0; k <= child.getChildNodes().item(j)
									.getAttributes().getLength() - 1; k++) {
								guidMatcher = guidPattern.matcher(child
										.getChildNodes().item(j)
										.getAttributes().item(k)
										+ "");
								if (guidMatcher.matches()) {
									containerGuidWriter.write(guidMatcher
											.group(1) + "\n");
								}
							}
							for (int k = 0; k <= child.getChildNodes().item(j)
									.getFirstChild().getChildNodes()
									.getLength() - 1; k++) {
								if (child.getChildNodes().item(j)
										.getFirstChild().getChildNodes()
										.item(k).getNodeName()
										.equals("ParaBlock")) {
									for (int t = 0; t <= child.getChildNodes()
											.item(j).getFirstChild()
											.getChildNodes().item(k)
											.getAttributes().getLength() - 1; t++) {
										guidMatcher = guidPattern.matcher(child
												.getChildNodes().item(j)
												.getFirstChild()
												.getChildNodes().item(k)
												.getAttributes().item(t)
												+ "");
										if (guidMatcher.matches()) {
											fragmentGuidWriter
													.write(guidMatcher.group(1)
															+ "\n");
										}
									}
								}
							}
						}
					}
				}
			}
			reader.close();
			containerGuidWriter.close();
			fragmentGuidWriter.close();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
