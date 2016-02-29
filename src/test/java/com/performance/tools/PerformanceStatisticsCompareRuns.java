package com.performance.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PerformanceStatisticsCompareRuns {

	private static String TEST_FOLDER = "23-Feb-2016";
	private static String TEST_NUMBER = "1";
	private static String FILENAME_TEMPLATE = "_SUMMARY_FILTERED_";
	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	// private static String PATH = "D:/Xyleme/performance/products/xpe/review_session/";
	// private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
	// private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
	// private static String PATH = "D:/Xyleme/performance/products/bcp/tests/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String OUTPUT_FILENAME = "_COMPARE.txt";
	private static String DEFAULT_VALUE_IF_MISSING = "";
	private static String DEFAULT_VALUE_IF_MISSING_PRCNTG = "";
	private static int SCALE_THRESHOLD_MS = 5000;
	private static float SCALE_RATE = (float)0.01;

	public static void main(String[] args) {
		readAll();
	}

	private static void readAll() {
		Map<String, HashMap<String, String>> responseTime = new HashMap<String, HashMap<String, String>>();
		Map<String, HashMap<String, String>> success = new HashMap<String, HashMap<String, String>>();
		Map<String, HashMap<String, String>> requestsTotal = new HashMap<String, HashMap<String, String>>();
		Map<String, String> allRequests = new HashMap<String, String>();
		File folder = new File(PATHNAME);
		File[] listOfFiles = folder.listFiles();
		for(File file: listOfFiles){
			if(file.getName().startsWith(FILENAME_TEMPLATE)){
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String[] keys = reader.readLine().split("\\|");
					if (keys != null) {
						String[] dataParts;
						String line;
						HashMap<String, String> requestAveragePair = new HashMap<String, String>();
						HashMap<String, String> requestPercentagePair = new HashMap<String, String>();
						HashMap<String, String> requestTotalRequestsPair = new HashMap<String, String>();
						while ((line = reader.readLine()) != null) {
							HashMap<String, String> row = new HashMap<String, String>();
							dataParts = line.split("\\|");
							for (int i = 0; i < keys.length; i++) {
								row.put(keys[i], dataParts[i]);
							}
							String percentageSuccess = calculatePercentageSuccess(row.get("percentage"));
							requestPercentagePair.put(row.get("request"), percentageSuccess);
							requestAveragePair.put(row.get("request"), row.get("ext_avg"));
							requestTotalRequestsPair.put(row.get("request"), row.get("total_n"));
							if(allRequests.containsKey(row.get("request"))){
								String currentStatus = allRequests.get(row.get("request"));
								if(currentStatus.equals("false") && needScaling(row.get("ext_avg"))){
									allRequests.put(row.get("request"), "true");
								}
							} else {
								if(needScaling(row.get("ext_avg"))){
									allRequests.put(row.get("request"), "true");
								} else {
									allRequests.put(row.get("request"), "false");
								}
							}
						}
						responseTime.put(file.getName().substring(0, file.getName().length() - 4), requestAveragePair);
						success.put(file.getName().substring(0, file.getName().length() - 4), requestPercentagePair);
						requestsTotal.put(file.getName().substring(0, file.getName().length() - 4), requestTotalRequestsPair);
					}
					reader.close();
				} catch (FileNotFoundException e) {
					throw new RuntimeException("File " + file.getName()
							+ " was not found.\n" + e.getStackTrace().toString());
				} catch (IOException e) {
					throw new RuntimeException("Could not read " + file.getName()
							+ " file.\n" + e.getStackTrace().toString());
				}
			}

		}
		for(String globalKey: allRequests.keySet()){
			for(String file: responseTime.keySet()){
				if(responseTime.get(file).containsKey(globalKey)){
					if(allRequests.get(globalKey).equals("true")){
						responseTime.get(file).put(globalKey, scaleValue(responseTime.get(file).get(globalKey)));
					} else{
						responseTime.get(file).put(globalKey, removeMissing(responseTime.get(file).get(globalKey)));
					}
				} else {
					responseTime.get(file).put(globalKey, DEFAULT_VALUE_IF_MISSING);
					success.get(file).put(globalKey, DEFAULT_VALUE_IF_MISSING);
					requestsTotal.get(file).put(globalKey, DEFAULT_VALUE_IF_MISSING);
				}
			}
		}
		File outputFile = new File(OUTPUT_PATH + OUTPUT_FILENAME);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try {
			FileWriter writer = new FileWriter(outputFile);
			for(String request: responseTime.get(FILENAME_TEMPLATE + 1).keySet()){
				String title = request;
				if(allRequests.get(request).equals("true")){
					title = title + "_SCALED_BY_" + SCALE_RATE;
				}
				writer.write(title + "|");
				for(int i = 1; i <= responseTime.keySet().size(); i++){
					writer.write(responseTime.get(FILENAME_TEMPLATE + i).get(request) + "|");
				}
				for(int i = 1; i <= success.keySet().size(); i++){
					writer.write(removeMissing(success.get(FILENAME_TEMPLATE + i).get(request)) + "|");
				}
				for(int i = 1; i <= requestsTotal.keySet().size(); i++){
					writer.write(removeMissing(requestsTotal.get(FILENAME_TEMPLATE + i).get(request)) + "|");
				}
				writer.write(request + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Summary done");
	}

	private static String calculatePercentageSuccess(String value) {
		if(value.equals("MISSING")){
			return DEFAULT_VALUE_IF_MISSING_PRCNTG;
		}
		return ("" + ((float)100.0 - Float.parseFloat(value.replaceAll(",", ".")))).replaceAll("\\.", ",");
	}

	private static String scaleValue(String value) {
		if(value.equals("MISSING")){
			return DEFAULT_VALUE_IF_MISSING;
		}
		float valueToScale = Float.parseFloat(value.replaceAll(",", "."));
		valueToScale = valueToScale * SCALE_RATE;
		return ("" + valueToScale).replaceAll("\\.", ",");
	}

	private static boolean needScaling(String value) {
		if(!value.equals("MISSING")){
			float time = Float.parseFloat(value.replaceAll(",", "."));
			if(time >= SCALE_THRESHOLD_MS){
				return true;
			}
		}
		return false;
	}

	private static String removeMissing(String value) {
		if(value.equals("MISSING")){
			return DEFAULT_VALUE_IF_MISSING;
		}
		return value;
	}

}
