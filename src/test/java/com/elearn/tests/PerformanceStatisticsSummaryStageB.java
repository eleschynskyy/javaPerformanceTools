package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PerformanceStatisticsSummaryStageB {

	private static String TEST_FOLDER = "04-Nov-2015";
	private static String TEST_NUMBER = "1";
	private static String BASE_PATH = "D:/Xyleme/performance/products/";
	private static String PATH = BASE_PATH + "sps/testing/";
	private static String SUMMARY_A_FILENAME = "SUMMARY_A.txt";
	private static String SUMMARY_B_FILENAME = "SUMMARY_B.txt";
	// private static String PATH = BASE_PATH + "xpe/review_session/";
	// private static String PATH = BASE_PATH + "xpe/cloud/";
	// private static String PATH = BASE_PATH + "msis/testing/";
	// private static String PATH = BASE_PATH + "lcms/testing/";
	// private static String PATH = BASE_PATH + "bcp/tests/";
	private static String SUMMARY_A = PATH + TEST_FOLDER + "/TEST_"	+ TEST_NUMBER + "/csv/" + SUMMARY_A_FILENAME;
	private static String SUMMARY_B = PATH + TEST_FOLDER + "/TEST_"	+ TEST_NUMBER + "/csv/" + SUMMARY_B_FILENAME;
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";

	public static void main(String[] args) {
		ArrayList<Map<String, HashMap<String, String>>> combinedSummary = new ArrayList<Map<String, HashMap<String, String>>>();
		Set<String> filesToProcess = new HashSet<String>();
		filesToProcess.add(SUMMARY_A);
		filesToProcess.add(SUMMARY_B);
		Iterator<String> filesToProcessIterator = filesToProcess.iterator();
		while (filesToProcessIterator.hasNext()) {
			String filePath = filesToProcessIterator.next();
			combinedSummary.add(readSummary(filePath));
		}

		// update summary
		Map<String, HashMap<String, String>> wholeStatA = combinedSummary.get(0);
		Map<String, HashMap<String, String>> wholeStatB = combinedSummary.get(1);

		for (String request : wholeStatB.keySet()) {
			wholeStatA.get(request).put("apd_baseline", wholeStatB.get(request).get("apd_baseline"));
			float extAvg = new Float(wholeStatA.get(request).get("ext_avg").replaceAll(",", "\\."));
			float apdBaseline = new Float(wholeStatB.get(request).get("apd_baseline").replaceAll(",", "\\."));
			wholeStatA.get(request).put("avg_to_baseline", String.format("%.2f", extAvg / apdBaseline));
			wholeStatA.get(request).put("apd_ttime", wholeStatB.get(request).get("apd_ttime"));
			wholeStatA.get(request).put("apd_satisfied_n", wholeStatB.get(request).get("apd_satisfied_n"));
			wholeStatA.get(request).put("apd_toleranted_n", wholeStatB.get(request).get("apd_toleranted_n"));
			wholeStatA.get(request).put("apd_frustrated_n", wholeStatB.get(request).get("apd_frustrated_n"));
			wholeStatA.get(request).put("apd_total_n", wholeStatB.get(request).get("apd_total_n"));
			wholeStatA.get(request).put("apd_satisfiedP", wholeStatB.get(request).get("apd_satisfiedP"));
			wholeStatA.get(request).put("apd_tolerantedP", wholeStatB.get(request).get("apd_tolerantedP"));
			wholeStatA.get(request).put("apd_frustratedP", wholeStatB.get(request).get("apd_frustratedP"));
			wholeStatA.get(request).put("apd_apdex", wholeStatB.get(request).get("apd_apdex"));
		}

		String outputFileName = OUTPUT_PATH + "COMBINED_SUMMARY.txt";
		try {
			String wholeStatisticHeader = "errors_n|total_n|percentage|ext_avg|apd_baseline|avg_to_baseline|apd_ttime|apd_satisfied_n|"
					+ "apd_toleranted_n|apd_frustrated_n|apd_total_n|apd_satisfiedP|apd_tolerantedP|apd_frustratedP|apd_apdex";
			String[] headerKeys = wholeStatisticHeader.split("\\|");
			File outputFile = new File(outputFileName);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			writer.write("request|" + "errors_n|" + "total_n|"
					+ "percentage|" + "ext_avg|"
					+ "apd_baseline|" + "avg_to_baseline|" + "apd_ttime|"
					+ "apd_satisfied_n|" + "apd_toleranted_n|" + "apd_frustrated_n|" + "apd_total_n|"
					+ "apd_satisfiedP|" + "apd_tolerantedP|" + "apd_frustratedP|"
					+ "apd_apdex|\n");
			for (String request : wholeStatA.keySet()) {
				writer.write(request + "|");
				for (int i = 0; i < headerKeys.length; i++) {
					String token = "";
					if (wholeStatA.get(request).containsKey(headerKeys[i])) {
						token = wholeStatA.get(request).get(headerKeys[i]);
					}
					writer.write(token + "|");
				}
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not write to " + outputFileName
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

	private static Map<String, HashMap<String, String>> readSummary(String filePath) {
		Map<String, HashMap<String, String>> wholeStat = new HashMap<String, HashMap<String, String>>();
		File file = new File(filePath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] keys = reader.readLine().split("\\|");
			if (keys != null) {
				String[] dataParts;
				String line;
				while ((line = reader.readLine()) != null) {
					HashMap<String, String> row = new HashMap<String, String>();
					dataParts = line.split("\\|");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					String request = row.get("request");
					row.remove(request);
					wholeStat.put(request, row);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + filePath + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + filePath
					+ " file.\n" + e.getStackTrace().toString());
		}
		return wholeStat;
	}

}
