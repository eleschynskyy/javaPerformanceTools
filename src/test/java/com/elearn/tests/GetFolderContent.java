package com.elearn.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetFolderContent {

	private static String TEST_FOLDER = "25-Aug-2016";
	private static String TEST_NUMBER = "1";
//	private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	private static String FOLDER = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/outputs/";
	private static String TTIME_FILE = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/t_time.txt";

	public static void main(String[] args) {
		File folder = new File(FOLDER);
		File[] listOfFiles = folder.listFiles();
		File outputFile = new File(TTIME_FILE);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try{
			FileWriter outputWriter = new FileWriter(outputFile);
//			outputWriter.write("file,time\n");
			int iteration = 1;
			for(File file: listOfFiles){
				outputWriter.write("OUTPUT_" + iteration + "\t" + file.getName().substring(0, file.getName().length() - 4) + "\n");
				iteration++;
			}
			outputWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
