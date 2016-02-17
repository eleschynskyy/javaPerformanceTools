package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer2 {

	private static String FILE_NAME = "prepared_2nd_stage_dev_extract";
//	private static String FILE_NAME = "8_12_test";
	private static String FILE_EXT = "txt";
	private static String TEST_FOLDER = "10-Dec-2014";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	private static String NEEDED = ".html";
//	private static int TIME_RANGE_IN_MINUTES = 1;
	
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "." + FILE_EXT;
	private static String EXTRACTED_PROD_FILE = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_prod_extract" + "." + FILE_EXT;
	private static String EXTRACTED_DEV_FILE = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_dev_extract" + "." + FILE_EXT;
	private static String PARSED_FILE = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_map" + "." + FILE_EXT;
	private static int codes[] = new int[] {200, 301, 302, 304, 401, 404, 422, 500};

//	private static String LOG_LINE_PATTERN = "(\\d+\\/Dec\\/2014:\\d+:\\d+):\\d+\\tGET\\s(.*?\\/preview\\/.*?\\/((index)|([a-zA-Z0-9]*-[a-zA-Z0-9]*){4})\\.html)\\sHTTP\\/1\\.1\\t(\\d+)\\t(\\d+|-)";
	private static String LOG_LINE_PATTERN = "(\\d+\\/Dec\\/2014:\\d+:\\d+)\\t(.*?\\/preview\\/.*?\\/((index)|([a-zA-Z0-9]*-[a-zA-Z0-9]*){4})\\.html)\\t(\\d+)\\t(\\d+|-)";

	public static void main(String[] args) {
//		extractNeededRequests(NEEDED);
		
		HashMap<String, Integer> statusCodesMap;
		HashMap<String, HashMap<String, Integer>> responseCodesMap = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> statusCodesMapTime;
		HashMap<String, HashMap<String, Integer>> responseCodesMapTime = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> statusCodesMapTimeCount;
		HashMap<String, HashMap<String, Integer>> responseCodesMapTimeCount = new HashMap<String, HashMap<String, Integer>>();
		List<String> keySet = new ArrayList<String>();
		BufferedReader br;
		Pattern logLinePattern = Pattern.compile(LOG_LINE_PATTERN);
		Matcher mLogLinePattern;
		long startBasic = 0;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			String line;
			line = br.readLine();
			if(line != null){
				mLogLinePattern = logLinePattern.matcher(line);
				if(mLogLinePattern.matches()){
					String time = mLogLinePattern.group(1);
					Date date = new SimpleDateFormat("dd/MMM/yyyy:HH:mm", Locale.ENGLISH).parse(time);
					startBasic = date.getTime();
					keySet.add(mLogLinePattern.group(1));
					statusCodesMap = new HashMap<String, Integer>();
					statusCodesMapTime = new HashMap<String, Integer>();
					statusCodesMapTimeCount = new HashMap<String, Integer>();
					for(int code: codes){
						statusCodesMap.put(code + "", 0);
						statusCodesMapTime.put(code + "", 0);
						statusCodesMapTimeCount.put(code + "", 0);
					}
					statusCodesMap.put(mLogLinePattern.group(6), 1);
					if(!mLogLinePattern.group(7).equals("-")){
						statusCodesMapTime.put(mLogLinePattern.group(6), Integer.parseInt(mLogLinePattern.group(7)));
						statusCodesMapTimeCount.put(mLogLinePattern.group(6), 1);
					}
					responseCodesMap.put(mLogLinePattern.group(1), statusCodesMap);
					responseCodesMapTime.put(mLogLinePattern.group(1), statusCodesMapTime);
					responseCodesMapTimeCount.put(mLogLinePattern.group(1), statusCodesMapTimeCount);
				}
			}
			
			while ((line = br.readLine()) != null) {
				mLogLinePattern = logLinePattern.matcher(line);
				if (mLogLinePattern.matches()) {
					String time = mLogLinePattern.group(1);
					Date date = new SimpleDateFormat("dd/MMM/yyyy:HH:mm", Locale.ENGLISH).parse(time);
					long currentTime = date.getTime();
					if(currentTime == startBasic){
						statusCodesMap = responseCodesMap.get(mLogLinePattern.group(1));
						statusCodesMapTime = responseCodesMapTime.get(mLogLinePattern.group(1));
						statusCodesMapTimeCount = responseCodesMapTimeCount.get(mLogLinePattern.group(1));
						statusCodesMap.put(mLogLinePattern.group(6), statusCodesMap.get(mLogLinePattern.group(6)) + 1);
						if(!mLogLinePattern.group(7).equals("-")){
							statusCodesMapTime.put(mLogLinePattern.group(6), statusCodesMapTime.get(mLogLinePattern.group(6)) + Integer.parseInt(mLogLinePattern.group(7)));
							statusCodesMapTimeCount.put(mLogLinePattern.group(6), statusCodesMapTimeCount.get(mLogLinePattern.group(6)) + 1);
						}
						responseCodesMap.put(mLogLinePattern.group(1), statusCodesMap);
						responseCodesMapTime.put(mLogLinePattern.group(1), statusCodesMapTime);
						responseCodesMapTimeCount.put(mLogLinePattern.group(1), statusCodesMapTimeCount);
					} else {
						keySet.add(mLogLinePattern.group(1));
						statusCodesMap = new HashMap<String, Integer>();
						statusCodesMapTime = new HashMap<String, Integer>();
						statusCodesMapTimeCount = new HashMap<String, Integer>();
						for(int code: codes){
							statusCodesMap.put(code + "", 0);
							statusCodesMapTime.put(code + "", 0);
							statusCodesMapTimeCount.put(code + "", 0);
						}
						statusCodesMap.put(mLogLinePattern.group(6), 1);
						if(!mLogLinePattern.group(7).equals("-")){
							statusCodesMapTime.put(mLogLinePattern.group(6), Integer.parseInt(mLogLinePattern.group(7)));
							statusCodesMapTimeCount.put(mLogLinePattern.group(6), 1);
						}
						responseCodesMap.put(mLogLinePattern.group(1), statusCodesMap);
						responseCodesMapTime.put(mLogLinePattern.group(1), statusCodesMapTime);
						responseCodesMapTimeCount.put(mLogLinePattern.group(1), statusCodesMapTimeCount);
						startBasic = currentTime;
					}
					}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			File outputFile = new File(PARSED_FILE);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			for (String timeX : keySet) {
				List<String> keySetSorted = new ArrayList<String>(new HashSet<String>(responseCodesMap.get(timeX).keySet()));
				Collections.sort(keySetSorted);
				writer.write(timeX);
				for (String status: keySetSorted) {
//					writer.write(timeX + "|" + status + "|" + responseCodesMap.get(timeX).get(status) + "\n");
					writer.write("|" + responseCodesMap.get(timeX).get(status));
				}
//				writer.write("\t");
				/*
				for (String status: keySetSorted) {
					writer.write("|" + responseCodesMapTime.get(timeX).get(status));
				}
				writer.write("\t");
				for (String status: keySetSorted) {
					writer.write("|" + responseCodesMapTimeCount.get(timeX).get(status));
				}
				*/
//				writer.write("\tAVERAGE: ");
				for (String status: keySetSorted) {
					int time = responseCodesMapTime.get(timeX).get(status);
					int count = responseCodesMapTimeCount.get(timeX).get(status);
					double countToWrite;
					if(count == 0){
						countToWrite = 0;
					} else {
						countToWrite = (double)time/count;
					}
					writer.write("|" + countToWrite);
				}
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		
	}

	private static void extractNeededRequests(String template) {
		BufferedReader br;
		Pattern logLinePattern = Pattern.compile(LOG_LINE_PATTERN);
		Matcher mLogLinePattern;
		try {
			File outputFileProd = new File(EXTRACTED_PROD_FILE);
			if (outputFileProd.exists()) {
				outputFileProd.delete();
			}
			FileWriter writerProd = new FileWriter(outputFileProd);
			File outputFileDev = new File(EXTRACTED_DEV_FILE);
			if (outputFileDev.exists()) {
				outputFileDev.delete();
			}
			FileWriter writerDev = new FileWriter(outputFileDev);
			br = new BufferedReader(new FileReader(PATHNAME));
			String line;
			while ((line = br.readLine()) != null) {
				mLogLinePattern = logLinePattern.matcher(line);
				if(mLogLinePattern.matches()){
					if(mLogLinePattern.group(2).contains("/env/dev/page/")){
						writerDev.write(mLogLinePattern.group(1) + "\t" + 
								 mLogLinePattern.group(2) + "\t" +
								 mLogLinePattern.group(6) + "\t" +
								 mLogLinePattern.group(7) + "\n");
					} else {
						writerProd.write(mLogLinePattern.group(1) + "\t" + 
								 mLogLinePattern.group(2) + "\t" +
								 mLogLinePattern.group(6) + "\t" +
								 mLogLinePattern.group(7) + "\n");
					}
				}
			}
			br.close();
			writerProd.close();
			writerDev.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
				
	}
	
}
