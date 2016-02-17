package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class CustomParametersMaker {

	private static String FILENAME1 = "1000-page-course_TOPIC";
	private static String FILENAME2 = "1000-page-course_PARABLOCK1";
	private static String FILENAME3 = "1000-page-course_PARABLOCK2";
	private static String FILE_EXT = "txt";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME1 = PATH + FILENAME1 + "." + FILE_EXT;
	private static String PATHNAME2 = PATH + FILENAME2 + "." + FILE_EXT;
	private static String PATHNAME3 = PATH + FILENAME3 + "." + FILE_EXT;

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br1;
		BufferedReader br2;
		BufferedReader br3;
		FileWriter writerPages;
		try {
			br1 = new BufferedReader(new FileReader(PATHNAME1));
			br2 = new BufferedReader(new FileReader(PATHNAME2));
			br3 = new BufferedReader(new FileReader(PATHNAME3));
			File loadOutputFilePages = new File(PATH + FILENAME1 + "_JMX_rangeendpage_.txt");
			if (loadOutputFilePages.exists()) {
				loadOutputFilePages.delete();
			}
			writerPages = new FileWriter(loadOutputFilePages);
			String line1, line2, line3;
			int iterator = 0;
			while ((line1 = br1.readLine()) != null) {
				line2 = br2.readLine();
				line3 = br3.readLine();
				writerPages.write("rangeendpage_" + (iterator + 1) + "\t" + "465%2C74" + "\n");
				iterator++;
				writerPages.write("rangeendpage_" + (iterator + 1) + "\t" + "456.5%2C118" + "\n");
				iterator++;
				writerPages.write("rangeendpage_" + (iterator + 1) + "\t" + "456.5%2C135" + "\n");
				iterator++;
			}
			br1.close();
			br2.close();
			br3.close();
			writerPages.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME1 + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME1
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

}
