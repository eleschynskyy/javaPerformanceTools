package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PerformanceStatisticsBoxPlotParser {

	private static String TEST_FOLDER = "23-Feb-2016";
	private static String TEST_NUMBER = "1";
//	private static String PATH = "D:/Xyleme/performance/products/xpe/review_session/";
//	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/bcp/tests/";
//	private static String FILENAME = "baseline.jtl";
	private static String FILENAME = "regular.jtl";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/" + FILENAME;
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";

	public static void main(String[] args) {
		Map<String, FileWriter> writers = new HashMap<String, FileWriter>();
		Map<String, List<Integer>> errors = new HashMap<String, List<Integer>>();
		File file = new File(PATHNAME);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String[] keys = reader.readLine().split(",");
			if (keys != null) {
				String[] dataParts;
				String line;
				Set<String> successLabels = new HashSet<String>();
				while ((line = reader.readLine()) != null) {
					Map<String, String> row = new HashMap<String, String>();
					Pattern value = Pattern.compile(".*(\".+?\").*");
					Matcher valueMatcher = value.matcher(line);
					String inside = "";
					if(valueMatcher.matches()){
						inside = valueMatcher.group(1).replaceAll(",", ";");
						line = line.replace(valueMatcher.group(1), inside);
					}
					dataParts = line.split(",");
					for (int i = 0; i < keys.length; i++) {
						row.put(keys[i], dataParts[i]);
					}
					List<Integer> errTot = new ArrayList<Integer>();
					if(!errors.containsKey(row.get("label"))){
						if(!(row.get("responseCode").startsWith("2") || row.get("responseCode").startsWith("3"))){
							errTot.add(new Integer(1));
						} else {
							errTot.add(new Integer(0));
						}
						errTot.add(new Integer(1));
						errors.put(row.get("label"), errTot);
					} else {
						List<Integer> labelErrList = errors.get(row.get("label"));
						if(!(row.get("responseCode").startsWith("2") || row.get("responseCode").startsWith("3"))){
							labelErrList.set(0, new Integer(labelErrList.get(0) + 1));
						}
						labelErrList.set(1, new Integer(labelErrList.get(1) + 1));
						errors.put(row.get("label"), labelErrList);
					}
					if(row.get("responseCode").startsWith("2") || row.get("responseCode").startsWith("3")){
						if(!successLabels.contains(row.get("label"))){
							successLabels.add(row.get("label"));
							File outputFile = new File(OUTPUT_PATH + "_" + row.get("label") + ".txt");
							if (outputFile.exists()) {
								outputFile.delete();
							}
							FileWriter writer = new FileWriter(outputFile);
							writers.put(row.get("label"), writer);
							writer.write(row.get("elapsed") + "\n");
						} else {
							writers.get(row.get("label")).write(row.get("elapsed") + "\n");
						}
					}
				}
				File outputFile = new File(OUTPUT_PATH + "_errorStatistic.txt");
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileWriter errorWriter = new FileWriter(outputFile);
				errorWriter.write("request|errors_n|total_n|percentage\n");
				for(String label: errors.keySet()){
					float p = (float) errors.get(label).get(0) / errors.get(label).get(1) * 100;
//					String pString = new Float(p).toString().replaceAll("\\.", ",");
					errorWriter.write(label + "|" + errors.get(label).get(0) + "|" + errors.get(label).get(1) + "|" + String.format("%.2f", p) + "\n");
				}
				errorWriter.close();
			}
			reader.close();
			for(FileWriter writer: writers.values()){
				writer.close();
			}
			//sort numbers and get statistic
			File outputStat = new File(OUTPUT_PATH + "output.txt");
			if (outputStat.exists()) {
				outputStat.delete();
			}
			FileWriter outputWriter = new FileWriter(outputStat);
			outputWriter.write("request|ext_median|ext_q1|ext_q3|ext_lower|ext_upper|ext_lfence|ext_ufence|ext_min|ext_max|ext_avg|ext_std|ext_errors\n");
//			System.out.println("REQUEST,MEDIAN,Q1,Q3,LOWER,UPPER,LFENCE%,UFENCE%,MIN,MAX,AVG,STD,ERRORS%");
			for(String fileName: writers.keySet()){
				HashMap<String, String> result = new HashMap<String, String>();
				File fileToDelete = new File(OUTPUT_PATH + "_" + fileName + ".txt");
				FileReader fileReader = new FileReader(fileToDelete);
				BufferedReader br = new BufferedReader(fileReader);
				List<Integer> responseTimeList = new ArrayList<Integer>();
//				String line;
				//skip 1st line
				String line = br.readLine();
				while ((line = br.readLine()) != null) {
					responseTimeList.add(new Integer(line));
				}
				Collections.sort(responseTimeList);
				result = calculateStat(fileName, responseTimeList, errors.get(fileName));
				outputWriter.write(
						result.get("fileName") + "|" +
						result.get("median") + "|" +
						result.get("quartile1") + "|" +
						result.get("quartile3") + "|" +
						result.get("lowerFence") + "|" +
						result.get("upperFence") + "|" +
						result.get("percentageLessThanLowerFence") + "|" +
						result.get("percentageGreaterThanUpperFence") + "|" +
						result.get("min") + "|" +
						result.get("max") + "|" +
						result.get("avg") + "|" +
						result.get("std") + "|" +
						result.get("errorPercentage") + "\n");
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
		System.out.println("Done");
	}

	private static HashMap<String, String> calculateStat(String fileName, List<Integer> list, List<Integer> errors) {
		HashMap<String, String> result = new HashMap<String, String>();
		float median;
		float quartile1;
		float quartile3;
		float iqr;
		double lowerFence;
		double upperFence;
		if(list.size() % 2 == 0 ){
			median = (float) (list.get((list.size() / 2) - 1) + list.get(list.size() / 2)) / 2;
			if(((list.size() / 2) - 1) % 2 != 0){
				quartile1 = (float) (list.get(list.size() / 4 - 1) + list.get(list.size() / 4)) / 2;
				quartile3 = (float) (list.get(list.size() / 2 + list.size() / 4 - 1) + list.get(list.size() / 2 + list.size() / 4)) / 2;
			} else {
				quartile1 = list.get(list.size() / 4);
				quartile3 = (list.get((list.size() / 2) + list.size() / 4));
			}
		} else {
			median = list.get(list.size() / 2);
			if(((list.size() / 2) + 1) % 2 != 0){
				quartile1 = (list.get(list.size() / 4));
				quartile3 = (list.get(list.size() / 2 + list.size() / 4));
			} else {
				quartile1 = (float) (list.get(list.size() / 4) + list.get(list.size() / 4 + 1)) / 2;
				quartile3 = (float) (list.get(list.size() / 2 + list.size() / 4) + list.get(list.size() / 2 + list.size() / 4 + 1)) / 2;
			}
		}
		iqr = quartile3 - quartile1;
		lowerFence = quartile1 - 1.5 * iqr;
		upperFence = quartile3 + 1.5 * iqr;
		//
		int lessThanLowerFence = 0;
		int greaterThanUpperFence = 0;
		//TODO: change min/max initialization
		int min = list.get(0);
		int max = list.get(0);
		int sum = 0;
		double std = 0;
		for(Integer i: list){
			if(i < lowerFence) lessThanLowerFence++;
			if(i > upperFence) greaterThanUpperFence++;
			if(i < min) min = i;
			if(i > max) max = i;
			sum = sum + i;
		}
		float avg = (float) sum / list.size();
		float percentageLessThanLowerFence = (float) lessThanLowerFence / list.size() * 100;
		float percentageGreaterThanUpperFence = (float) greaterThanUpperFence / list.size() * 100;
		for(Integer i: list){
			std = std + (i - avg) * (i - avg);
		}
		std = Math.sqrt((float) std / list.size());
		float errorPercentage = (float) errors.get(0) / errors.get(1) * 100;
		/*
		System.out.println(
				fileName + "," +
				median + "," +
				quartile1 + "," +
				quartile3 + "," +
				lowerFence + "," +
				upperFence + "," +
				percentageLessThanLowerFence + "," +
				percentageGreaterThanUpperFence + "," +
				min + "," +
				max + "," +
				avg + "," +
				std + "," +
				errorPercentage);
		*/
		result.put("fileName", fileName);
		result.put("median", new Float(median).toString());
		result.put("quartile1", new Float(quartile1).toString());
		result.put("quartile3", new Float(quartile3).toString());
		result.put("lowerFence", new Float(lowerFence).toString());
		result.put("upperFence", new Float(upperFence).toString());
		result.put("percentageLessThanLowerFence", new Float(percentageLessThanLowerFence).toString());
		result.put("percentageGreaterThanUpperFence", new Float(percentageGreaterThanUpperFence).toString());
		result.put("min", new Float(min).toString());
		result.put("max", new Float(max).toString());
		result.put("avg", new Float(avg).toString());
		result.put("std", new Float(std).toString());
		result.put("errorPercentage", new Float(errorPercentage).toString());
		return result;
	}

}
