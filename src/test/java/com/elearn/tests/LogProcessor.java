package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogProcessor {
	
	private static String TEST_FOLDER = "09-Dec-2014";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/" + "cloudXPE-1416391040-complete.jtl";
	private static String PARSED_FILE = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/" + "output_parsed.jtl";
	private static String THREAD_NAME = "WU";

	public static void main(String[] args) {
		BufferedReader br;
		FileWriter writer;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File loadOutputFile = new File(PARSED_FILE);
			if (loadOutputFile.exists()) {
				loadOutputFile.delete();
			}
			writer = new FileWriter(loadOutputFile);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.contains(THREAD_NAME + "2") && line.contains(THREAD_NAME)) {
					writer.write(line + "\n");
				}
			}
			br.close();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

}
