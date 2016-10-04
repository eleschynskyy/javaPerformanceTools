package com.performance.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BehaviourStat {

	private static String TEST_FOLDER = "22-Sep-2016";
	private static String TEST_NUMBER = "1";
	private static String FILENAME = "log.jtl";
//	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
//	private static String PATH = "D:/Xyleme/performance/products/xpe/review_session/";
//	private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
//  private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
//  private static String PATH = "D:/Xyleme/performance/products/bcp/tests/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/threads/";

	public static void main(String[] args) {
		splitThreads(FILENAME);
		getDetails();
	}

	private static void getDetails() {
		File outputFile = new File(OUTPUT_PATH + "details.txt");
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try {
			FileWriter writer = new FileWriter(outputFile);
			File folder = new File(OUTPUT_PATH);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.getName().startsWith("__")) {
					Map<String, String> totalStat = new HashMap<String, String>();
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String[] keys = reader.readLine().split(",");
					if (keys != null) {
						String[] dataParts;
						String line;
						while ((line = reader.readLine()) != null) {
							Map<String, String> row = new HashMap<String, String>();
							Pattern quotedValuePattern = Pattern.compile(".*(\".+?\").*");
							Matcher quotedValueMatcher = quotedValuePattern.matcher(line);
							String insideQuotedValue = "";
							if (quotedValueMatcher.matches()) {
								insideQuotedValue = quotedValueMatcher.group(1).replaceAll(",", ";");
								line = line.replace(quotedValueMatcher.group(1), insideQuotedValue);
							}
							dataParts = line.split(",");
							for (int i = 0; i < keys.length; i++) {
								row.put(keys[i], dataParts[i]);
							}
							if(row.containsKey("label")){
								if(row.get("label").equals("endSession_BHV")){
									List<String> sortedKeys = new ArrayList<String>(totalStat.size());
									sortedKeys.addAll(totalStat.keySet());
									Collections.sort(sortedKeys);
									for (String label : sortedKeys) {
										writer.write(">> " + label.substring(0, label.length() - 4) + ": " + totalStat.get(label) + "\n");
									}
									writer.write("==============================================================\n");
									totalStat = new HashMap<String, String>();
								} else {
									writer.write(row.get("label").substring(0, row.get("label").length() - 4) + "\n");
									if(!totalStat.containsKey(row.get("label"))){
										totalStat.put(row.get("label"), "1");
									} else {
										totalStat.put(row.get("label"), (Integer.parseInt(totalStat.get(row.get("label"))) + 1) + "");
									}
								}
							}
				        }
						reader.close();
			        }
				}
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not read file.\n" + e.getStackTrace().toString());
		}

	}

	private static void splitThreads(String fileToProcess) {
		Map<String, FileWriter> writers = new HashMap<String, FileWriter>();
		File file = new File(PATHNAME + fileToProcess);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String keyLine = reader.readLine();
			String[] keys = keyLine.split(",");
			int sessionCounter = 0;
			int concurrentUsers = 0;
			Map<String, String> totalStat = new HashMap<String, String>();
			if (keys != null) {
				String[] dataParts;
				String line;
				while ((line = reader.readLine()) != null) {
					Map<String, String> row = new HashMap<String, String>();
					Pattern quotedValuePattern = Pattern.compile(".*(\".+?\").*");
					Matcher quotedValueMatcher = quotedValuePattern.matcher(line);
					String insideQuotedValue = "";
					if (quotedValueMatcher.matches()) {
						insideQuotedValue = quotedValueMatcher.group(1).replaceAll(",", ";");
						line = line.replace(quotedValueMatcher.group(1), insideQuotedValue);
					}
					dataParts = line.split(",");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					if(row.containsKey("threadName") && row.get("label").endsWith("_BHV")){
						if(row.get("label").equals("endSession_BHV")){
							sessionCounter++;
						}
						if(!totalStat.containsKey(row.get("label"))){
							totalStat.put(row.get("label"), "1");
						} else {
							totalStat.put(row.get("label"), (Integer.parseInt(totalStat.get(row.get("label"))) + 1) + "");
						}
						String threadName = row.get("threadName");
						if(!writers.containsKey(threadName)){
							File outputFile = new File(OUTPUT_PATH + "__" + threadName + ".txt");
							if (outputFile.exists()) {
								outputFile.delete();
							}
							FileWriter writer = new FileWriter(outputFile);
							writers.put(threadName, writer);
							writer.write(keyLine + "\n" + line + "\n");
							concurrentUsers++;
						} else {
							writers.get(threadName).write(line + "\n");
						}
					}
				}
				reader.close();
				for (FileWriter writer : writers.values()) {
					writer.close();
				}
				totalStat.remove("endSession_BHV");
				File outputFile = new File(OUTPUT_PATH + "summary.txt");
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileWriter writer = new FileWriter(outputFile);
				writer.write("Expected concurrent users: " + concurrentUsers + "\n");
				writer.write("Total user sessions: " + sessionCounter + "\n");
				writer.write("Average frequency of user actions per session:\n");
				writer.write("==============================================\n");
				List<String> sortedKeys = new ArrayList<String>(totalStat.size());
				sortedKeys.addAll(totalStat.keySet());
				Collections.sort(sortedKeys);
				for (String label : sortedKeys) {
					System.out.println(label.substring(0, label.length() - 4) + ": " + totalStat.get(label) + "/" + sessionCounter +
							"=" + Float.parseFloat(totalStat.get(label)) / sessionCounter);
					writer.write(label.substring(0, label.length() - 4) + ": " + totalStat.get(label) + "/" + sessionCounter +
							"=" + Float.parseFloat(totalStat.get(label)) / sessionCounter + "\n");
				}
				writer.close();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("'" + fileToProcess + "'" + " preprocessing done");

	}

}
