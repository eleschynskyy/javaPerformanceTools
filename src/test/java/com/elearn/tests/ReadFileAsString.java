package com.elearn.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileAsString {

	private static String FILENAME = "testimport";
	private static String FILE_EXT = "xml";
	private static String PATH = "D:/Xyleme/performance/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;

	public static void main(String[] args) {
		StringBuilder content = new StringBuilder();
		File file = new File(PATHNAME);
		InputStreamReader fstream = null;
		try {
			fstream = new InputStreamReader(new FileInputStream(file), "UTF-8");
			int data;
			while ((data = fstream.read()) != -1) {
				content.append((char) data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fstream != null)
					fstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(content.toString());
	}

}
