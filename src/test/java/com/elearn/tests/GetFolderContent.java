package com.elearn.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetFolderContent {

	private static String TEST_FOLDER = "03-Jul-2015";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/msis/testing/";
//	private static String PATH = "D:/Xyleme/performance/products/lcms/testing/";
	private static String FOLDER = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String TTIME_FILE = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/t_time.txt";

	public static void main(String[] args) {
		File folder = new File(FOLDER + "apdex");
		File[] listOfFiles = folder.listFiles();
		File outputFile = new File(TTIME_FILE);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try{
			FileWriter outputWriter = new FileWriter(outputFile);
			outputWriter.write("file,time\n");
			for(File file: listOfFiles){
				outputWriter.write(file.getName() + ",1000000\n");
			}
			outputWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
