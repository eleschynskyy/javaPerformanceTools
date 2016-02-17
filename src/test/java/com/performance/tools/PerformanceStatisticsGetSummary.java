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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PerformanceStatisticsGetSummary {

	private static String TEST_FOLDER = "15-Feb-2016";
	private static String TEST_NUMBER = "1";
	private static String BASELINE_FILENAME = "baseline_1.jtl";
	private static String FILENAME = "regular_1.jtl";
	private static String FILTERS = "PAUSE|_AUX";
//	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	// private static String PATH = "D:/Xyleme/performance/products/xpe/review_session/";
	// private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
	// private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
	// private static String PATH = "D:/Xyleme/performance/products/bcp/tests/";
	private static float APDEX_T_TIME_RATE = 20;// %
	private static String DETAILED_STAT_FILENAME = "_DETAILED_STAT.txt";
	private static String APDEX_T_TIME_INITIAL_FILENAME = "_APDEX_T_TIME_INIT.txt";
	private static String APDEX_T_TIME_FILENAME = "_APDEX_T_TIME.txt";
	private static String ERROR_STAT_FILENAME = "_ERROR_STAT.txt";
	private static String APDEX_FILENAME = "_APDEX.txt";
	private static String SUMMARY_FILENAME = "_SUMMARY.txt";
	private static String FILTERED_SUMMARY_FILENAME = "_SUMMARY_FILTERED.txt";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String TTIME_FILE_INITIAL = PATHNAME	+ APDEX_T_TIME_INITIAL_FILENAME;
	private static String TTIME_FILE = PATHNAME + APDEX_T_TIME_FILENAME;
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String SATISFIED = "satisfied";
	private static String TOLERANTED = "toleranted";
	private static String FRUSTRATED = "frustrated";

	public static void main(String[] args) {
		processResults(BASELINE_FILENAME);
		generateTTime();
		cleanUp();
		processResults(FILENAME);
		calculateApdexInfo();
		makeSummary();
		cleanUp();
		filterSummary();
	}

	private static void filterSummary() {
		String[] filtersString = FILTERS.split("\\|");
		String filePath = PATHNAME + SUMMARY_FILENAME;
		File file = new File(filePath);
		String outputFileName = OUTPUT_PATH + FILTERED_SUMMARY_FILENAME;
		File outputFile = new File(outputFileName);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try{
			FileWriter writer = new FileWriter(outputFile);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			writer.write(line + "\n");
			String[] dataParts;
			while ((line = reader.readLine()) != null) {
				dataParts = line.split("\\|");
				boolean filterOut = false;
				for(String filter: filtersString){
					if(dataParts[0].contains(filter)){
						filterOut = true;
						break;
					}
				}
				if(!filterOut){
					writer.write(line + "\n");
				}
			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + filePath
					+ " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + filePath
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Filtering done");
	}

	private static void makeSummary() {
		Map<String, HashMap<String, String>> wholeStat = new HashMap<String, HashMap<String, String>>();
		Set<String> filesToProcess = new HashSet<String>();
		filesToProcess.add(PATHNAME + ERROR_STAT_FILENAME);
		filesToProcess.add(PATHNAME + DETAILED_STAT_FILENAME);
		filesToProcess.add(PATHNAME + APDEX_FILENAME);
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

		// add avg/baseline
		for (String request : wholeStat.keySet()) {
//			System.out.println(request);
			String avgToBaseline = "N/A";
			if (wholeStat.get(request).containsKey("ext_avg")) {
				float extAvg = new Float(wholeStat.get(request).get("ext_avg")
						.replaceAll(",", "."));
				wholeStat.get(request).put("ext_avg",
						String.format("%.2f", extAvg));
				if (!wholeStat.get(request).get("apd_ttime").equals("N/A")) {
					float apdBaseline = new Float(wholeStat.get(request)
							.get("apd_baseline").replaceAll(",", "."));
					avgToBaseline = String.format("%.2f", extAvg / apdBaseline);
					wholeStat.get(request).put("apd_baseline", String.format("%.2f", apdBaseline));
				}
				wholeStat.get(request).put("avg_to_baseline", avgToBaseline);
			} else {
				wholeStat.get(request).put("avg_to_baseline", "MISSING");
			}
		}

		String outputFileName = OUTPUT_PATH + SUMMARY_FILENAME;
		try {
			String wholeStatisticHeader = "errors_n|total_n|percentage|ext_avg|apd_baseline|avg_to_baseline|apd_ttime|apd_satisfied_n|"
					+ "apd_toleranted_n|apd_frustrated_n|apd_total_n|apd_satisfiedP|apd_tolerantedP|apd_frustratedP|apd_apdex";
			String[] headerKeys = wholeStatisticHeader.split("\\|");
			File outputFile = new File(outputFileName);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			writer.write("request|" + "errors_n|" + "total_n|" + "percentage|"
					+ "ext_avg|" + "apd_baseline|" + "avg_to_baseline|"
					+ "apd_ttime|" + "apd_satisfied_n|" + "apd_toleranted_n|"
					+ "apd_frustrated_n|" + "apd_total_n|" + "apd_satisfiedP|"
					+ "apd_tolerantedP|" + "apd_frustratedP|" + "apd_apdex|\n");
			for (String request : wholeStat.keySet()) {
				writer.write(request + "|");
				for (int i = 0; i < headerKeys.length; i++) {
					String token = "MISSING";
					if (wholeStat.get(request).containsKey(headerKeys[i])) {
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

		System.out.println("Summary done");
	}

	private static void cleanUp() {
		File folder = new File(PATHNAME);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().startsWith("__")) {
				file.delete();
			}
		}
	}

	private static void calculateApdexInfo() {
		Map<String, Integer> errors = new HashMap<String, Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(OUTPUT_PATH
					+ ERROR_STAT_FILENAME));
			String line;
			String[] dataParts;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				dataParts = line.split("\\|");
				errors.put(dataParts[0], new Integer(dataParts[1]));
			}
			br.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + ERROR_STAT_FILENAME
					+ " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + ERROR_STAT_FILENAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		// update pool with requests missing in baseline
		try {
			Set<String> allApdexFilesList = new HashSet<String>();
			File folder = new File(PATHNAME);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.getName().startsWith("__")) {
					allApdexFilesList.add(file.getName());
				}
			}
			File file = new File(TTIME_FILE_INITIAL);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			File outputFile = new File(TTIME_FILE);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			String line = reader.readLine();
			writer.write(line + "\n");
			String[] keys = line.split("\\|");
			if (keys != null) {
				String[] dataParts;
				while ((line = reader.readLine()) != null) {
					dataParts = line.split("\\|");
					String key = dataParts[0];
					if (allApdexFilesList.contains(key)) {
						allApdexFilesList.remove(key);
					}
					writer.write(line + "\n");
				}
				Iterator<String> allApdexFilesListIterator = allApdexFilesList
						.iterator();
				while (allApdexFilesListIterator.hasNext()) {
					writer.write(allApdexFilesListIterator.next() + "|"
							+ "-1|-1\n");
				}
			}
			writer.close();
			reader.close();
			file.delete();
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + TTIME_FILE_INITIAL
					+ " file.\n" + e.getStackTrace().toString());
		}
		// calculate apdex info
		try {
			Map<String, String> row = new HashMap<String, String>();
			File file = new File(TTIME_FILE);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String[] keys = line.split("\\|");
			if (keys != null) {
				File outputFile = new File(OUTPUT_PATH + APDEX_FILENAME);
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileWriter apdexWriter = new FileWriter(outputFile);
				String[] dataParts;
				apdexWriter
						.write("request|apd_baseline|apd_ttime|apd_satisfied_n|apd_toleranted_n|apd_frustrated_n|apd_total_n|apd_satisfiedP|apd_tolerantedP|apd_frustratedP|apd_apdex\n");
				while ((line = reader.readLine()) != null) {
					dataParts = line.split("\\|");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					Map<String, String> result = calculateApdex(
							row.get("file"),
							row.get("time"),
							errors.get(row.get("file").substring(2,
									row.get("file").length() - 4)));
					String baseline = row.get("baseline");
					String time = row.get("time");
					String apdex = result.get("apdex");
					if (baseline.equals("-1")) {
						baseline = "N/A";
						time = "N/A";
					} else {
						if (!apdex.equals("MISSING")) {
							apdex = String.format("%.2f", new Float(apdex));
						}
						time = String.format("%.2f",
								new Float(time.replaceAll(",", ".")));
					}
					apdexWriter.write(row.get("file").substring(2,
							row.get("file").length() - 4)
							+ "|"
							+ baseline
							+ "|"
							+ time
							+ "|"
							+ result.get(SATISFIED)
							+ "|"
							+ result.get(TOLERANTED)
							+ "|"
							+ result.get(FRUSTRATED)
							+ "|"
							+ result.get("total")
							+ "|"
							+ result.get("satisfiedP").replaceAll("\\.", ",")
							+ "|"
							+ result.get("tolerantedP").replaceAll("\\.", ",")
							+ "|"
							+ result.get("frustratedP").replaceAll("\\.", ",")
							+ "|" + apdex + "\n");
				}
				apdexWriter.close();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + TTIME_FILE
					+ " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + TTIME_FILE
					+ " file.\n" + e.getStackTrace().toString());
		}
	}

	private static void processResults(String fileToProcess) {
		Map<String, FileWriter> writers = new HashMap<String, FileWriter>();
		Map<String, List<Integer>> errors = new HashMap<String, List<Integer>>();
		File file = new File(PATHNAME + fileToProcess);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] keys = reader.readLine().split(",");
			if (keys != null) {
				String[] dataParts;
				String line;
				Set<String> successLabels = new HashSet<String>();
				while ((line = reader.readLine()) != null) {
					Map<String, String> row = new HashMap<String, String>();
					Pattern quotedValuePattern = Pattern
							.compile(".*(\".+?\").*");
					Matcher quotedValueMatcher = quotedValuePattern
							.matcher(line);
					String insideQuotedValue = "";
					if (quotedValueMatcher.matches()) {
						insideQuotedValue = quotedValueMatcher.group(1)
								.replaceAll(",", ";");
						line = line.replace(quotedValueMatcher.group(1),
								insideQuotedValue);
					}
					dataParts = line.split(",");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					List<Integer> errorsTotalPerRequest = new ArrayList<Integer>();
					if (!errors.containsKey(row.get("label"))) {
						if (!(row.get("responseCode").startsWith("2") || row
								.get("responseCode").startsWith("3"))) {
							errorsTotalPerRequest.add(new Integer(1));
						} else {
							errorsTotalPerRequest.add(new Integer(0));
						}
						errorsTotalPerRequest.add(new Integer(1));
						errors.put(row.get("label"), errorsTotalPerRequest);
					} else {
						List<Integer> labelErrList = errors.get(row
								.get("label"));
						if (!(row.get("responseCode").startsWith("2") || row
								.get("responseCode").startsWith("3"))) {
							labelErrList.set(0, new Integer(
									labelErrList.get(0) + 1));
						}
						labelErrList.set(1,
								new Integer(labelErrList.get(1) + 1));
						errors.put(row.get("label"), labelErrList);
					}
					if (row.get("responseCode").startsWith("2")
							|| row.get("responseCode").startsWith("3")) {
						if (!successLabels.contains(row.get("label"))) {
							successLabels.add(row.get("label"));
							File outputFile = new File(OUTPUT_PATH + "__"
									+ row.get("label") + ".txt");
							if (outputFile.exists()) {
								outputFile.delete();
							}
							FileWriter writer = new FileWriter(outputFile);
							writers.put(row.get("label"), writer);
							writer.write(row.get("elapsed") + "\n");
						} else {
							writers.get(row.get("label")).write(
									row.get("elapsed") + "\n");
						}
					}
				}
				File outputFile = new File(OUTPUT_PATH + ERROR_STAT_FILENAME);
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileWriter errorWriter = new FileWriter(outputFile);
				errorWriter
						.write("request|errors_n|total_n|percentage\n");
				for (String label : errors.keySet()) {
					float p = (float) errors.get(label).get(0)
							/ errors.get(label).get(1) * 100;
					String pString = new Float(p).toString().replaceAll("\\.",
							",");
					errorWriter.write(label + "|" + errors.get(label).get(0)
							+ "|" + errors.get(label).get(1) + "|" + pString
							+ "\n");
				}
				errorWriter.close();
			}
			reader.close();
			for (FileWriter writer : writers.values()) {
				writer.close();
			}
			// sort numbers and get statistic
			File outputStat = new File(OUTPUT_PATH + DETAILED_STAT_FILENAME);
			if (outputStat.exists()) {
				outputStat.delete();
			}
			FileWriter outputWriter = new FileWriter(outputStat);
			outputWriter
					.write("request|ext_median|ext_q1|ext_q3|ext_lower|ext_upper|ext_lfence|ext_ufence|ext_min|ext_max|ext_avg|ext_std|ext_errors\n");
			for (String fileName : writers.keySet()) {
//				System.out.println("Problem file: " + fileName);
				HashMap<String, String> result = new HashMap<String, String>();
				File requestInfoFile = new File(OUTPUT_PATH + "__" + fileName + ".txt");
				FileReader fileReader = new FileReader(requestInfoFile);
				BufferedReader br = new BufferedReader(fileReader);
				List<Integer> responseTimeList = new ArrayList<Integer>();

				// skip 1st iteration but if there's just single line in a file read it as is
				String line = br.readLine();
//				String line;
				while ((line = br.readLine()) != null) {
					responseTimeList.add(new Integer(line));
				}
				Collections.sort(responseTimeList);
				result = calculateStat(fileName, responseTimeList,
						errors.get(fileName));

				outputWriter.write(result.get("fileName") + "|"
						+ result.get("median") + "|" + result.get("quartile1")
						+ "|" + result.get("quartile3") + "|"
						+ result.get("lowerFence") + "|"
						+ result.get("upperFence") + "|"
						+ result.get("percentageLessThanLowerFence") + "|"
						+ result.get("percentageGreaterThanUpperFence") + "|"
						+ result.get("min") + "|" + result.get("max") + "|"
						+ result.get("avg") + "|" + result.get("std") + "|"
						+ result.get("errorPercentage") + "\n");
				br.close();
			}
			outputWriter.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("'" + fileToProcess + "'" + " preprocessing done");
	}

	private static HashMap<String, String> calculateStat(String fileName,
			List<Integer> list, List<Integer> errors) {
		HashMap<String, String> result = new HashMap<String, String>();
		float median;
		float quartile1;
		float quartile3;
		float iqr;
		double lowerFence;
		double upperFence;
		if (list.size() % 2 == 0) {
			median = (float) (list.get((list.size() / 2) - 1) + list.get(list
					.size() / 2)) / 2;
			if (((list.size() / 2) - 1) % 2 != 0) {
				quartile1 = (float) (list.get(list.size() / 4 - 1) + list
						.get(list.size() / 4)) / 2;
				quartile3 = (float) (list.get(list.size() / 2 + list.size() / 4
						- 1) + list.get(list.size() / 2 + list.size() / 4)) / 2;
			} else {
				quartile1 = list.get(list.size() / 4);
				quartile3 = (list.get((list.size() / 2) + list.size() / 4));
			}
		} else {
			median = list.get(list.size() / 2);
			if (((list.size() / 2) + 1) % 2 != 0) {
				quartile1 = (list.get(list.size() / 4));
				quartile3 = (list.get(list.size() / 2 + list.size() / 4));
			} else {
				quartile1 = (float) (list.get(list.size() / 4) + list.get(list
						.size() / 4 + 1)) / 2;
				quartile3 = (float) (list
						.get(list.size() / 2 + list.size() / 4) + list.get(list
						.size() / 2 + list.size() / 4 + 1)) / 2;
			}
		}
		iqr = quartile3 - quartile1;
		lowerFence = quartile1 - 1.5 * iqr;
		upperFence = quartile3 + 1.5 * iqr;
		int lessThanLowerFence = 0;
		int greaterThanUpperFence = 0;
		int min = list.get(0);
		int max = list.get(0);
		int sum = 0;
		double std = 0;
		for (Integer i : list) {
			if (i < lowerFence)
				lessThanLowerFence++;
			if (i > upperFence)
				greaterThanUpperFence++;
			if (i < min)
				min = i;
			if (i > max)
				max = i;
			sum = sum + i;
		}
		float avg = (float) sum / list.size();
		float percentageLessThanLowerFence = (float) lessThanLowerFence
				/ list.size() * 100;
		float percentageGreaterThanUpperFence = (float) greaterThanUpperFence
				/ list.size() * 100;
		for (Integer i : list) {
			std = std + (i - avg) * (i - avg);
		}
		std = Math.sqrt((float) std / list.size());
		float errorPercentage = (float) errors.get(0) / errors.get(1) * 100;
		result.put("fileName", fileName);
		result.put("median", new Float(median).toString().replace(".", ","));
		result.put("quartile1",
				new Float(quartile1).toString().replace(".", ","));
		result.put("quartile3",
				new Float(quartile3).toString().replace(".", ","));
		result.put("lowerFence",
				new Float(lowerFence).toString().replace(".", ","));
		result.put("upperFence",
				new Float(upperFence).toString().replace(".", ","));
		result.put("percentageLessThanLowerFence", new Float(
				percentageLessThanLowerFence).toString().replace(".", ","));
		result.put("percentageGreaterThanUpperFence", new Float(
				percentageGreaterThanUpperFence).toString().replace(".", ","));
		result.put("min", new Float(min).toString().replace(".", ","));
		result.put("max", new Float(max).toString().replace(".", ","));
		result.put("avg", new Float(avg).toString().replace(".", ","));
		result.put("std", new Float(std).toString().replace(".", ","));
		result.put("errorPercentage", new Float(errorPercentage).toString()
				.replace(".", ","));
		return result;
	}

	private static void generateTTime() {
		File file = new File(OUTPUT_PATH + DETAILED_STAT_FILENAME);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] keys = reader.readLine().split("\\|");
			if (keys != null) {
				String[] dataParts;
				String line;
				File tTimeFile = new File(OUTPUT_PATH
						+ APDEX_T_TIME_INITIAL_FILENAME);
				if (tTimeFile.exists()) {
					tTimeFile.delete();
				}
				FileWriter tTimeWriter = new FileWriter(tTimeFile);
				tTimeWriter.write("file|baseline|time\n");
				Map<String, String> row = new HashMap<String, String>();
				while ((line = reader.readLine()) != null) {
					dataParts = line.split("\\|");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					//
					float elapsed = Float.parseFloat(row.get("ext_avg")
							.replace(",", "."));
					int tTime = Math.round(elapsed + elapsed
							* APDEX_T_TIME_RATE / 100);
					tTimeWriter.write("__" + row.get("request") + ".txt|"
							+ Math.round(elapsed) + ",0|" + tTime + ",0\n");
					//
				}
				tTimeWriter.close();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + OUTPUT_PATH
					+ DETAILED_STAT_FILENAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + OUTPUT_PATH
					+ DETAILED_STAT_FILENAME + " file.\n"
					+ e.getStackTrace().toString());
		}
		System.out.println("Generating t-time done");
	}

	private static Map<String, String> calculateApdex(String fileName,
			String time, Integer error) {
		Map<String, String> result = new HashMap<String, String>();
		if (!time.equals("-1")) {
			File file = new File(OUTPUT_PATH + fileName);
			if (file.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					int satisfied = 0;
					int toleranted = 0;
					int frustrated = 0;
					while ((line = br.readLine()) != null) {
						switch (getResponseType(line,
								new Float(time.replaceAll(",", ".")))) {
						case 1:
							satisfied++;
							break;
						case 2:
							toleranted++;
							break;
						case 3:
							frustrated++;
							break;
						}
					}
					br.close();
					frustrated = frustrated + error;
					int total = satisfied + toleranted + frustrated;
					result.put(SATISFIED, new Integer(satisfied).toString());
					result.put(TOLERANTED, new Integer(toleranted).toString());
					result.put(FRUSTRATED, new Integer(frustrated).toString());
					result.put("satisfiedP", new Float((float) satisfied
							/ total * 100).toString());
					result.put("tolerantedP", new Float((float) toleranted
							/ total * 100).toString());
					result.put("frustratedP", new Float((float) frustrated
							/ total * 100).toString());
					result.put("total", new Integer(total).toString());
					result.put("apdex",
							new Float((satisfied + (float) toleranted / 2)
									/ total).toString());
				} catch (FileNotFoundException e) {
					throw new RuntimeException("File " + fileName
							+ " was not found.\n"
							+ e.getStackTrace().toString());
				} catch (IOException e) {
					throw new RuntimeException("Could not read " + fileName
							+ " file.\n" + e.getStackTrace().toString());
				}
			} else {
				result.put(SATISFIED, "MISSING");
				result.put(TOLERANTED, "MISSING");
				result.put(FRUSTRATED, "MISSING");
				result.put("satisfiedP", "MISSING");
				result.put("tolerantedP", "MISSING");
				result.put("frustratedP", "MISSING");
				result.put("total", "MISSING");
				result.put("apdex", "MISSING");
			}

		} else {
			result.put(SATISFIED, "N/A");
			result.put(TOLERANTED, "N/A");
			result.put(FRUSTRATED, "N/A");
			result.put("satisfiedP", "N/A");
			result.put("tolerantedP", "N/A");
			result.put("frustratedP", "N/A");
			result.put("total", "N/A");
			result.put("apdex", "N/A");
		}
		return result;
	}

	private static int getResponseType(String line, float time) {
		int rt = Integer.parseInt(line);
		if (rt <= time) {
			return 1;
		}
		if ((rt > time) && (rt <= 4 * time)) {
			return 2;
		} else {
			return 3;
		}
	}

}
