package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer {
	
	/* to analyze prod localhost_access_log */

	private static String FILE_NAME = "all";
	private static String FILE_EXT = "txt";
	private static String TEST_FOLDER = "10-Dec-2014";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "." + FILE_EXT;
	private static String PARSED_FILE = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_parsed" + "." + FILE_EXT;
	private static String UNIQUE_OUTPUT = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_output" + "." + FILE_EXT;
	private static String UNIQUE_ENV = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_env" + "." + FILE_EXT;
	private static String UNIQUE_RES = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_res" + "." + FILE_EXT;

	private static String LOG_LINE_PATTERN = "\\d+\\.\\d+\\.\\d+\\.\\d+\\s-\\s-\\s\\[.*?]\\s\"(.*?)\\s(.*?)\\s.*?\"\\s(\\d+)\\s(\\d+|-)";
	private static String ENV_PATTERN = ".*\\/(env\\/.*?)\\/.*";

	public static void main(String[] args) {
		HashMap<LogRecord, Integer> logs = new HashMap<LogRecord, Integer>();
//		HashMap<String, Integer> outputProfile = new HashMap<String, Integer>();
//		HashMap<String, Integer> resourse = new HashMap<String, Integer>();
//		HashMap<String, Integer> env = new HashMap<String, Integer>();
		BufferedReader br;
		Pattern logLinePattern = Pattern.compile(LOG_LINE_PATTERN);
		Pattern outputProfilePattern = Pattern.compile(".*?\\/(\\(.*?\\))\\/.*");
		Pattern envPattern = Pattern.compile(ENV_PATTERN);
		Matcher mLogLinePattern;
		Matcher mOutputProfilePattern;
//		Matcher mEnvPattern;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			String line;
			while ((line = br.readLine()) != null) {
				mLogLinePattern = logLinePattern.matcher(line);
				if (mLogLinePattern.matches()) {
					LogRecord logRecord = new LogRecord()
							.setType(mLogLinePattern.group(1))
							.setPath(mLogLinePattern.group(2))
							.setStatus(mLogLinePattern.group(3));
					String path = mLogLinePattern.group(2);
					/*
					String[] pathComponents = path.split("/");
					if(pathComponents.length > 0){
						if (resourse.containsKey(pathComponents[pathComponents.length - 1])) {
							resourse.put(pathComponents[pathComponents.length - 1], resourse.get(pathComponents[pathComponents.length - 1]) + 1);
						} else {
							resourse.put(pathComponents[pathComponents.length - 1], 1);
						}
//						System.out.println(pathComponents[pathComponents.length - 1]);
					}
					*/
//					mOutputProfilePattern = outputProfilePattern.matcher(path);
//					System.out.println(path);
					/*
					if(mOutputProfilePattern.matches()) {
//						System.out.println(mOutputProfilePattern.group(1));
						if (outputProfile.containsKey(mOutputProfilePattern.group(1))) {
							outputProfile.put(mOutputProfilePattern.group(1), outputProfile.get(mOutputProfilePattern.group(1)) + 1);
						} else {
							outputProfile.put(mOutputProfilePattern.group(1), 1);
						}
					}
					*/
					
					/*
					mEnvPattern = envPattern.matcher(path);
					if(mEnvPattern.matches()) {
//						System.out.println(mOutputProfilePattern.group(1));
						if (env.containsKey(mEnvPattern.group(1))) {
							env.put(mEnvPattern.group(1), env.get(mEnvPattern.group(1)) + 1);
						} else {
							env.put(mEnvPattern.group(1), 1);
						}
					}
					*/
					if (logs.containsKey(logRecord)) {
						logs.put(logRecord, logs.get(logRecord) + 1);
					} else {
						logs.put(logRecord, 1);
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
		}
		try {
			
			File outputFile = new File(PARSED_FILE);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			
			FileWriter writer = new FileWriter(outputFile);
			
			for (LogRecord key : logs.keySet()) {
				writer.write(key + "|" + logs.get(key) + "\n");
			}
			writer.close();
			
			/*
			File outputFile = new File(UNIQUE_RES);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			for (String key : resourse.keySet()) {
				writer.write(key + "|" + resourse.get(key) + "\n");
			}
			writer.close();
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
