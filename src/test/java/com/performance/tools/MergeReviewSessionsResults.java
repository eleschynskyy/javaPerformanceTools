package com.performance.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MergeReviewSessionsResults {

	private static String TEST_FOLDER = "28-Mar-2016";
	private static String TEST_NUMBER = "1";
	private static String FILENAME_TEMPLATE = "test_";
	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
	// private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	// private static String PATH =
	// "D:/Xyleme/performance/products/xpe/review_session/";
	// private static String PATH =
	// "D:/Xyleme/performance/products/msis/testing/";
	// private static String PATH =
	// "D:/Xyleme/performance/products/lcms/testing/";
	// private static String PATH = "D:/Xyleme/performance/products/bcp/tests/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";
	private static String OUTPUT_FILENAME = "_MERGED.txt";

	public static void main(String[] args) {
		readAll();
	}

	private static void readAll() {
		File folder = new File(PATHNAME);
		File[] listOfFiles = folder.listFiles();
		File outputFile = new File(OUTPUT_PATH + OUTPUT_FILENAME);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try {
			BufferedReader reader;
			FileWriter writer = new FileWriter(outputFile);
			writer.write("timeStamp,elapsed,label,responseCode,responseMessage,threadName,dataType,success,bytes,grpThreads,allThreads,Latency,Hostname\n");
			for(File file: listOfFiles){
				if(file.getName().startsWith(FILENAME_TEMPLATE)){
					reader = new BufferedReader(new FileReader(file));
					String line;
					while ((line = reader.readLine()) != null) {
						writer.write(line + "\n");
					}
					reader.close();
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read from file" + " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}
}
