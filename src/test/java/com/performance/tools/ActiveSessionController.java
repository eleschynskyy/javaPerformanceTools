package com.performance.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ActiveSessionController {

//	private static String TEST_FOLDER = "31-Mar-2016";
//	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/sandbox/";
	private static String OUTPUT_PATH = PATH;
	private static String OUTPUT_FILENAME = "ActiveUsers.txt";

	public static void main(String[] args) {
		int minUsers = 10;
		int maxUsers = 50;
		int totalTimeInSec = 10;
		long startMs = System.currentTimeMillis();
		System.out.println("Start: " + startMs);
		long curMs;
		long elapsed;
		long absoluteElapsed;
		File outputFile = new File(OUTPUT_PATH + OUTPUT_FILENAME);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		try {
			FileWriter writer = new FileWriter(outputFile);
			for (int i = 1; i <= 800; i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				curMs = System.currentTimeMillis();
				elapsed = (curMs - startMs) / 1000;
				absoluteElapsed = elapsed;
				int t = (int)Math.ceil(elapsed) / (int)Math.ceil(totalTimeInSec);
//				System.out.println(absoluteElapsed + " " + t);
				elapsed = elapsed - totalTimeInSec * t;
				double radian = Math.PI / 50 * (100 * elapsed / totalTimeInSec);
				double x = Math.sin(radian / 2 - Math.PI / 2);
				double y = x * (maxUsers - minUsers) / 2 + (minUsers + maxUsers) / 2;
				if(t % 2 != 0){
					y = maxUsers - y + minUsers;
				}
				System.out.println(absoluteElapsed + " " + y);
				writer.write(elapsed + "," + (int)Math.ceil(y) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endMs = System.currentTimeMillis();
		System.out.println("End: " + endMs);
		System.out.println("Duration: " + (endMs - startMs) + "ms");
		System.out.println("Done");

	}

}
