package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacementTemplate {

	private static String FILENAME = "PREVIEW2";
	private static String FILE_EXT = "jmx";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;
	private static String GUID = "5690b866-4edc-43e0-82ee-b177eb51dd2e";

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br;
		FileWriter writer;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File outputFile = new File(PATH + FILENAME + "_RPL.jmx");
			if (outputFile.exists()) {
				outputFile.delete();
			}
			writer = new FileWriter(outputFile);
			String line;
			while ((line = br.readLine()) != null) {
				writer.write(line.replaceAll("", ""));
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
