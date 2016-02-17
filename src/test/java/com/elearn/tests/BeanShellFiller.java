package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class BeanShellFiller {

	private static String FILENAME = "exl";
	private static String FILE_EXT = "csv";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br;
		FileWriter writer;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File loadOutputFile = new File(PATH + FILENAME + "_BS.txt");
			if (loadOutputFile.exists()) {
				loadOutputFile.delete();
			}
			writer = new FileWriter(loadOutputFile);
			String line;
			int iterator = 1;
			while ((line = br.readLine()) != null) {
				writer.write("vars.put(\"HTML_" + iterator + "\", \"" + line + "\" + \".html\");" + "\n");
				iterator++;
			}
			writer.write("vars.put(\"PAGE_COUNT\", " + (iterator - 2) + " + \"\");" + "\n");
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
