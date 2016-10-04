package com.performance.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ActiveSessionController {

	private static String TEST_FOLDER = "31-Mar-2016";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/sps/testing/";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/csv/";
	private static String OUTPUT_FILENAME = "ActiveUsers.txt";

	public static void main(String[] args) {
		int minUsers = 10;
		int maxUsers = 50;
		int totalTimeInSec = 60;
		long startMs = System.currentTimeMillis();
		System.out.println("Start: " + startMs);
		long curMs;
		long elapsed;
		File outputFile = new File(OUTPUT_PATH + OUTPUT_FILENAME);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try {
			FileWriter writer = new FileWriter(outputFile);
			for (int i = 1; i <= 1000; i++) {
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				curMs = System.currentTimeMillis();
				elapsed = (curMs - startMs) / 1000;
				double radian = Math.PI / 50 * (100 * elapsed / totalTimeInSec);
				double x = Math.sin(3 * radian - Math.PI / 2);
				double y = x * (maxUsers - minUsers) / 2 + (minUsers + maxUsers) / 2;
				writer.write(elapsed + "," + (int)Math.ceil(y) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");

	}

}
