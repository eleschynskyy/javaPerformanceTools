package com.performance.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformUncompiledScript {
	private static String TEST_FOLDER = "22-Sep-2016";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";
	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String OUTPUT_PATH = PATH + TEST_FOLDER + "/TEST_" + TEST_NUMBER + "/csv/";
	private static String OUTPUT_FILENAME = "T_UNCOMPILED_TMPL.jmx";

	public static void main(String[] args) {
		File file = new File(PATHNAME + "UNCOMPILED_TMPL.jmx");
		File outputFile = new File(OUTPUT_PATH + OUTPUT_FILENAME);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		File legendFile = new File(OUTPUT_PATH + "LEGEND.txt");
		if (legendFile.exists()) {
			legendFile.delete();
		}
		try {
			FileWriter outputWriter = new FileWriter(outputFile);
			FileWriter legendWriter = new FileWriter(legendFile);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			Pattern samplerNamePattern = Pattern.compile(".*<HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"(.+?)\" enabled=\"true\">.*");
			Matcher samplerNameMatcher;
			int iterator = 1;
			while ((line = reader.readLine()) != null) {
				samplerNameMatcher = samplerNamePattern.matcher(line);
				if(samplerNameMatcher.matches()){
					line = line.replaceAll("\\?", "");
					line = line.replaceAll(samplerNameMatcher.group(1), "" + iterator);
					outputWriter.write(line);
					legendWriter.write(iterator + " - " + samplerNameMatcher.group(1) + "\n");
					iterator++;
				} else {
					outputWriter.write(line);
				}

			}
			reader.close();
			outputWriter.close();
			legendWriter.close();
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
