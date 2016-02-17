package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PerformanceStatisticsSummaryStageA {

	private static String TEST_FOLDER = "19-Jan-2016";
	private static String TEST_NUMBER = "1";
	private static String BASE_PATH = "D:/Xyleme/performance/products/";
	private static String PATH = BASE_PATH + "sps/testing/";
//	private static String PATH = BASE_PATH + "xpe/cloud/";
//	private static String PATH = BASE_PATH + "msis/testing/";
	private static String ERROR_STATISTICS_FILENAME = "_errorStatistic.txt";
	private static String EXTENDED_STATISTICS_FILENAME = "output.txt";
	private static String APDEX_STATISTICS_FILENAME = "_apdex.txt";
	// private static String PATH = BASE_PATH + "xpe/review_session/";
	// private static String PATH = BASE_PATH + "xpe/cloud/";
	// private static String PATH = BASE_PATH + "msis/testing/";
	// private static String PATH = BASE_PATH + "lcms/testing/";
	// private static String PATH = BASE_PATH + "bcp/tests/";
	private static String ERROR_STATISTICS_PATHNAME = PATH + TEST_FOLDER
			+ "/TEST_" + TEST_NUMBER + "/csv/" + ERROR_STATISTICS_FILENAME;
	private static String EXTENDED_STATISTICS_PATHNAME = PATH + TEST_FOLDER
			+ "/TEST_" + TEST_NUMBER + "/csv/" + EXTENDED_STATISTICS_FILENAME;
	private static String APDEX_STATISTICS_PATHNAME = PATH + TEST_FOLDER
			+ "/TEST_" + TEST_NUMBER + "/csv/" + APDEX_STATISTICS_FILENAME;
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";

	public static void main(String[] args) {
		Map<String, HashMap<String, String>> wholeStat = new HashMap<String, HashMap<String, String>>();
		Set<String> filesToProcess = new HashSet<String>();
		filesToProcess.add(ERROR_STATISTICS_PATHNAME);
		filesToProcess.add(EXTENDED_STATISTICS_PATHNAME);
		filesToProcess.add(APDEX_STATISTICS_PATHNAME);
		Iterator<String> filesToProcessIterator = filesToProcess.iterator();
		while (filesToProcessIterator.hasNext()) {
			String filePath = filesToProcessIterator.next();
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
						if (!wholeStat.containsKey(request)) {
							wholeStat.put(request, row);
						} else {
							HashMap<String, String> existingRequestStat = wholeStat
									.get(request);
							existingRequestStat.putAll(row);
						}
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				throw new RuntimeException("File " + filePath
						+ " was not found.\n" + e.getStackTrace().toString());
			} catch (IOException e) {
				throw new RuntimeException("Could not read " + filePath
						+ " file.\n" + e.getStackTrace().toString());
			}
		}

		//add avg/baseline
		for(String request: wholeStat.keySet()){
//			System.out.println(request);
			String avgToBaseline = "N/A";
			float extAvg = new Float(wholeStat.get(request).get("ext_avg"));
			wholeStat.get(request).put("ext_avg", String.format("%.2f", extAvg));
			if(!wholeStat.get(request).get("apd_ttime").equals("N/A")){
				float apdBaseline = new Float(wholeStat.get(request).get("apd_baseline"));
				avgToBaseline = String.format("%.2f", extAvg / apdBaseline);
				wholeStat.get(request).put("apd_baseline", String.format("%.2f", apdBaseline));
			}
			wholeStat.get(request).put("avg_to_baseline", avgToBaseline);
		}

		String outputFileName = OUTPUT_PATH + "SUMMARY.txt";
		try {
			String wholeStatisticHeader = "errors_n|total_n|percentage|ext_avg|apd_baseline|avg_to_baseline|apd_ttime|apd_satisfied_n|" +
										  "apd_toleranted_n|apd_frustrated_n|apd_total_n|apd_satisfiedP|apd_tolerantedP|apd_frustratedP|apd_apdex";
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
			for(String request: wholeStat.keySet()){
				writer.write(request + "|");
				for(int i = 0; i < headerKeys.length; i++){
					String token = "";
					if(wholeStat.get(request).containsKey(headerKeys[i])){
						token = wholeStat.get(request).get(headerKeys[i]);
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

}
