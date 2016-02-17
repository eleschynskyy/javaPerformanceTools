package com.elearn.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer3 {

	private static String FILE_NAME = "prepared_2nd_stage_prod_extract_map";
	private static String FILE_EXT = "txt";
	private static String TEST_FOLDER = "10-Dec-2014";
	private static String MONTH = "Dec";
	private static String YEAR = "2014";
	private static int CODES_COUNT = 8;
	private static String EMPTY = "|0|0|0|0|0|0|0|0|0,0|0,0|0,0|0,0|0,0|0,0|0,0|0,0";
	private static String TEST_NUMBER = "1";
	private static String PATH = "D:/Xyleme/performance/products/xpe/cloud/";

	private static String PATHNAME = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "." + FILE_EXT;
	private static String PARSED_FILE = PATH + TEST_FOLDER + "/TEST_"
			+ TEST_NUMBER + "/logs/" + FILE_NAME + "_extended" + "." + FILE_EXT;
	private static String LOG_LINE_PATTERN = "(\\d+\\/" + MONTH + "\\/" + YEAR
			+ ":\\d+:\\d+)\\|([0-9]+\\|){" + CODES_COUNT
			+ "}([0-9]+,[0-9]+\\|){" + (CODES_COUNT - 1) + "}[0-9]+,[0-9]+";

	public static void main(String[] args) {
		BufferedReader br;
		Pattern logLinePattern = Pattern.compile(LOG_LINE_PATTERN);
		Matcher mLogLinePattern;
		try {
			File outputFile = new File(PARSED_FILE);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileWriter writer = new FileWriter(outputFile);
			br = new BufferedReader(new FileReader(PATHNAME));
			String line;
			Date date = new Date();
			Date dateNext;
			DateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm", Locale.ENGLISH);
			if ((line = br.readLine()) != null) {
				line = line.replaceAll("\\.", ",");
				mLogLinePattern = logLinePattern.matcher(line);
				if (mLogLinePattern.matches()) {
					date = format.parse(mLogLinePattern.group(1));
					writer.write(line + "\n");
				}
			}
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("\\.", ",");
				mLogLinePattern = logLinePattern.matcher(line);
				if (mLogLinePattern.matches()) {
					dateNext = format.parse(mLogLinePattern.group(1));
					while (dateNext.getTime() - date.getTime() >= 60000 * 2) {
						date = new Date(date.getTime() + 60000);
						writer.write(format.format(date) + EMPTY + "\n");
					}
					writer.write(line + "\n");
					date = dateNext;
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
