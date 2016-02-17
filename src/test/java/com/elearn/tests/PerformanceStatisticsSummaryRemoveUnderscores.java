package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PerformanceStatisticsSummaryRemoveUnderscores {

	private static String TEST_FOLDER = "22-Dec-2015";
	private static String TEST_NUMBER = "3";
	private static String BASE_PATH = "D:/Xyleme/performance/products/";
	private static String PATH = BASE_PATH + "msis/testing/";
//	private static String SUMMARY_FILENAME = "SUMMARY.txt";
	private static String SUMMARY_FILENAME = "allTests.jtl";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";

	public static void main(String[] args) {
		File file = new File(OUTPUT_PATH + SUMMARY_FILENAME);
//		String outputFileName = OUTPUT_PATH + "SUMMARY_WITHOUT_UNDERSCORES.txt";
		String outputFileName = OUTPUT_PATH + "allTests_WITHOUT_UNDERSCORES.txt";
		try {
			File outputFile = new File(outputFileName);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.replaceAll("_\\d+", "");
				writer.write(line + "\n");
			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + OUTPUT_PATH + SUMMARY_FILENAME
					+ " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + OUTPUT_PATH + SUMMARY_FILENAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

}
