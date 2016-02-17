package com.elearn.tests;

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

public class PerformanceStatisticsTotalMsis {

	private static String TEST_FOLDER = "22-Dec-2015";
	private static String TEST_NUMBER = "3";
	private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
	private static String FOLDER = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER
			+ "/csv/apdex";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";

	public static void main(String[] args) {
		System.out.println("Processing data per thread...");
		try {
			HashMap<String, String> requestStatistics = new HashMap<String, String>();
			File folder = new File(FOLDER);
			File[] listOfFiles = folder.listFiles();
			File outputStat = new File(OUTPUT_PATH + "requestStatistics.txt");
			if (outputStat.exists()) {
				outputStat.delete();
			}
			FileWriter outputWriter = new FileWriter(outputStat);
			outputWriter.write("request|min|max|median|std|avg|totalTime|numberOfRequests\n");
			for (File file : listOfFiles) {
				requestStatistics = calculateRequestStatistics(file.getAbsolutePath(), file.getName().substring(1, file.getName().length() - 4), true);
				outputWriter.write(requestStatistics.get("fileName") + "|" +
									String.format("%.2f", new Float(requestStatistics.get("min"))) + "|" +
									String.format("%.2f", new Float(requestStatistics.get("max"))) + "|" +
									String.format("%.2f", new Float(requestStatistics.get("median"))) + "|" +
									String.format("%.2f", new Float(requestStatistics.get("std"))) + "|" +
								    String.format("%.2f", new Float(requestStatistics.get("avg"))) + "|" +
								    String.format("%.2f", new Float(requestStatistics.get("totalSum"))) + "|" +
								    String.format("%.2f", new Float(requestStatistics.get("totalNumber"))) + "\n");
			}
			outputWriter.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Something went wrong\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong\n"
					+ e.getStackTrace().toString());
		}
		System.out.println("Processing data for all threads...");
		processDataAllThreads();
		System.out.println("Done");
	}

	private static void processDataAllThreads() {
		try{
			HashMap<String, String> requestStatistics = new HashMap<String, String>();
			Map<String, FileWriter> writers = new HashMap<String, FileWriter>();
			Map<String, List<Integer>> totalPerRequestList = new HashMap<String, List<Integer>>();
			File folder = new File(FOLDER);
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				String request = file.getName().substring(1, file.getName().length() - 6);
				FileReader fileReader = new FileReader(new File(file.getAbsolutePath()));
				BufferedReader br = new BufferedReader(fileReader);
				List<Integer> list = new ArrayList<Integer>();
				String line = br.readLine();
				while ((line = br.readLine()) != null) {
					list.add(new Integer(line));
				}
				br.close();
				if(!totalPerRequestList.containsKey(request)){
					totalPerRequestList.put(request, list);
				} else {
					totalPerRequestList.get(request).addAll(list);
				}
			}
			for(String request: totalPerRequestList.keySet()){
				File outputFile = new File(FOLDER + "/_" + request + ".txt");
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileWriter writer = new FileWriter(outputFile);
				writers.put(request, writer);
				for(int i: totalPerRequestList.get(request)){
					writer.write(i + "\n");
				}
				writer.close();
			}
			File outputStat = new File(OUTPUT_PATH + "allThreadsStatistics.txt");
			if (outputStat.exists()) {
				outputStat.delete();
			}
			FileWriter outputWriter = new FileWriter(outputStat);
			outputWriter.write("request|min|max|median|std|avg|totalTime|numberOfRequests\n");
			for(String request: totalPerRequestList.keySet()){
				requestStatistics = calculateRequestStatistics(FOLDER + "/_" + request + ".txt", request, false);
				outputWriter.write(requestStatistics.get("fileName") + "|" +
									String.format("%.2f", new Float(requestStatistics.get("min"))) + "|" +
									String.format("%.2f", new Float(requestStatistics.get("max"))) + "|" +
									String.format("%.2f", new Float(requestStatistics.get("median"))) + "|" +
									String.format("%.2f", new Float(requestStatistics.get("std"))) + "|" +
								    String.format("%.2f", new Float(requestStatistics.get("avg"))) + "|" +
								    String.format("%.2f", new Float(requestStatistics.get("totalSum"))) + "|" +
								    String.format("%.2f", new Float(requestStatistics.get("totalNumber"))) + "\n");
			}
			outputWriter.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Something went wrong\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong\n"
					+ " file.\n" + e.getStackTrace().toString());
		}
	}

	private static HashMap<String, String> calculateRequestStatistics(String filePath, String fileName, boolean skipFirstLine) {
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			FileReader fileReader = new FileReader(new File(filePath));
			BufferedReader br = new BufferedReader(fileReader);
			List<Integer> list = new ArrayList<Integer>();
			String line;
			if(skipFirstLine){
				line = br.readLine();
			}
			while ((line = br.readLine()) != null) {
				list.add(new Integer(line));
			}
			br.close();
			Collections.sort(list);
			float median;
			if (list.size() % 2 == 0) {
				median = (float) (list.get((list.size() / 2) - 1) + list
						.get(list.size() / 2)) / 2;
			} else {
				median = list.get(list.size() / 2);
			}
			int min = list.get(0);
			int max = list.get(0);
			int sum = 0;
			double std = 0;
			for (Integer i : list) {
				if (i < min)
					min = i;
				if (i > max)
					max = i;
				sum = sum + i;
			}
			float avg = (float) sum / list.size();
			for (Integer i : list) {
				std = std + (i - avg) * (i - avg);
			}
			std = Math.sqrt((float) std / list.size());
			result.put("fileName", fileName);
			result.put("median", new Float(median).toString());
			result.put("min", new Float(min).toString());
			result.put("max", new Float(max).toString());
			result.put("avg", new Float(avg).toString());
			result.put("std", new Float(std).toString());
			result.put("totalSum", new Integer(sum).toString());
			result.put("totalNumber", new Integer(list.size()).toString());
			return result;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + filePath + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + filePath
					+ " file.\n" + e.getStackTrace().toString());
		}
	}

}
