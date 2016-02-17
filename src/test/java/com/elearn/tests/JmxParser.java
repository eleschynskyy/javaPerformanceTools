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

public class JmxParser {

	private static String FILENAME = "AutoCAD_RS";
	private static String FILE_EXT = "jmx";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;

	private static FileWriter containerguidTextWriter;
	private static FileWriter fragmentguidTextWriter;
	private static FileWriter rangestartpathTextWriter;
	private static FileWriter rangeendpathTextWriter;
	private static FileWriter rangestartindexTextWriter;
	private static FileWriter rangeendindexTextWriter;
	private static FileWriter selectiontextTextWriter;
	private static FileWriter rangestartpageTextWriter;
	private static FileWriter rangeendpageTextWriter;

	private static FileWriter containerguidImageWriter;
	private static FileWriter fragmentguidImageWriter;
	private static FileWriter rangestartpageImageWriter;
	private static FileWriter rangeendpageImageWriter;

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			createOutputFiles();
//			Pattern requestTextPattern = Pattern.compile(".*?<stringProp name=\"HTTPSampler.path\">lcms-isolated-\\d+\\/review\\/.+?\\/.+?\\/page\\/resources\\/sdoc-review\\/add\\?containerguid=(.+?)&amp;fragmentguid=(.+?)&amp;replyto=&amp;sessionguid=.+?&amp;status=Open&amp;type=2&amp;rangestartguid=.+?&amp;rangeendguid=.+?&amp;rangestartpath=(.+?)&amp;rangeendpath=(.+?)&amp;rangestartindex=(\\d+?)&amp;rangeendindex=(\\d+?)&amp;selectiontext=(.+?)&amp;commenttext=.+?&amp;rangestart-path=.+?&amp;rangeend-path=.+?&amp;rangestart-guid=.+?&amp;rangeend-guid=.+?&amp;rangestart-index=\\d+?&amp;rangeend-index=\\d+?&amp;fragment-guid=.+?&amp;comment-text=.+?&amp;selection-text=.+?&amp;comment-id=\\d+?&amp;container-guid=.+?&amp;author-id=.+?&amp;date=.+?&amp;comment-guid=fakeguid&amp;rangestartpage=(\\d+?)&amp;rangeendpage=(.+?)&amp;.+?<\\/stringProp>.*");
//			Pattern requestImagePattern = Pattern.compile(".*?<stringProp name=\"HTTPSampler.path\">lcms-isolated-\\d+\\/review\\/.+?\\/.+?\\/page\\/resources\\/sdoc-review\\/add\\?containerguid=(.+?)&amp;fragmentguid=(.+?)&amp;replyto=&amp;sessionguid=.+?&amp;status=Open&amp;type=2&amp;commenttext=.+?&amp;fragment-guid=.+?&amp;comment-text=.+?&amp;comment-id=\\d+&amp;container-guid=.+?&amp;author-id=.+?&amp;date=.+?&amp;comment-guid=fakeguid&amp;rangestartpage=(.+?)&amp;rangeendpage=(.+?)&amp;.+?<\\/stringProp>.*");
			Matcher requestTextMatcher;
			Pattern requestTextPattern = Pattern.compile(".*?<stringProp name=\"HTTPSampler.path\">lcms-isolated-\\d+\\/review\\/.+?\\/.+?\\/page\\/resources\\/sdoc-review\\/add\\?rangestartguid=.+?&amp;rangeendguid=.+?&amp;rangestartpath=(.+?)&amp;rangeendpath=(.+?)&amp;rangestartindex=(\\d+?)&amp;rangeendindex=(\\d+?)&amp;selectiontext=(.+?)&amp;commenttext=.+?&amp;containerguid=(.+?)&amp;fragmentguid=(.+?)&amp;replyto=&amp;sessionguid=.+?&amp;status=Open&amp;type=2&amp;rangestart-path=.+?&amp;rangeend-path=.+?&amp;rangestart-guid=.+?&amp;rangeend-guid=.+?&amp;rangestart-index=\\d+?&amp;rangeend-index=\\d+?&amp;fragment-guid=.+?&amp;comment-text=.+?&amp;selection-text=.+?&amp;comment-id=\\d+?&amp;container-guid=.+?&amp;author-id=.+?&amp;date=.+?&amp;comment-guid=fakeguid&amp;rangestartpage=(\\d+?)&amp;rangeendpage=(.+?)&amp;.+?<\\/stringProp>.*");
			Pattern requestImagePattern = Pattern.compile(".*?<stringProp name=\"HTTPSampler.path\">lcms-isolated-\\d+\\/review\\/.+?\\/.+?\\/page\\/resources\\/sdoc-review\\/add\\?fragmentguid=(.+?)&amp;commenttext=.+?&amp;containerguid=(.+?)&amp;replyto=&amp;sessionguid=.+?&amp;status=Open&amp;type=2&amp;fragment-guid=.+?&amp;comment-text=.+?&amp;comment-id=\\d+&amp;container-guid=.+?&amp;author-id=.+?&amp;date=.+?&amp;comment-guid=fakeguid&amp;rangestartpage=(.+?)&amp;rangeendpage=(.+?)&amp;.+?<\\/stringProp>.*");
			Matcher requestImageMatcher;
			String line;
			int textIterator = 0;
			int imageIterator = 0;
			while ((line = br.readLine()) != null) {
				requestTextMatcher = requestTextPattern.matcher(line);
				requestImageMatcher = requestImagePattern.matcher(line);
				if (requestTextMatcher.matches()) {
					textIterator++;
					containerguidTextWriter.write("vars.put(\"containerguid_" + textIterator + "\", \"" + requestTextMatcher.group(6) + "\");\n");
					fragmentguidTextWriter.write("vars.put(\"fragmentguid_" + textIterator + "\", \"" + requestTextMatcher.group(7) + "\");\n");
					rangestartpathTextWriter.write("vars.put(\"rangestartpath_" + textIterator + "\", \"" + requestTextMatcher.group(1) + "\");\n");
					rangeendpathTextWriter.write("vars.put(\"rangeendpath_" + textIterator + "\", \"" + requestTextMatcher.group(2) + "\");\n");
					rangestartindexTextWriter.write("vars.put(\"rangestartindex_" + textIterator + "\", \"" + requestTextMatcher.group(3) + "\");\n");
					rangeendindexTextWriter.write("vars.put(\"rangeendindex_" + textIterator + "\", \"" + requestTextMatcher.group(4) + "\");\n");
					selectiontextTextWriter.write("vars.put(\"selectiontext_" + textIterator + "\", \"" + requestTextMatcher.group(5) + "\");\n");
					rangestartpageTextWriter.write("vars.put(\"rangestartpage_" + textIterator + "\", \"" + requestTextMatcher.group(8) + "\");\n");
					rangeendpageTextWriter.write("vars.put(\"rangeendpage_" + textIterator + "\", \"" + requestTextMatcher.group(9) + "\");\n");
			    }
				if (requestImageMatcher.matches()) {
					imageIterator++;
					containerguidImageWriter.write("vars.put(\"containerguid_" + imageIterator + "\", \"" + requestImageMatcher.group(2) + "\");\n");
					fragmentguidImageWriter.write("vars.put(\"fragmentguid_" + imageIterator + "\", \"" + requestImageMatcher.group(1) + "\");\n");
					rangestartpageImageWriter.write("vars.put(\"rangestartpage_" + imageIterator + "\", \"" + requestImageMatcher.group(3) + "\");\n");
					rangeendpageImageWriter.write("vars.put(\"rangeendpage_" + imageIterator + "\", \"" + requestImageMatcher.group(4) + "\");\n");
			    }
			}
			fragmentguidTextWriter.write("vars.put(\"FRAGMENTS_NUMBER" + "\", \"" + textIterator + "\");\n");
			fragmentguidImageWriter.write("vars.put(\"FRAGMENTS_NUMBER" + "\", \"" + imageIterator + "\");\n");
			br.close();
			closeWriters();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + PATHNAME + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + PATHNAME
					+ " file.\n" + e.getStackTrace().toString());
		}
		System.out.println("Done");
	}

	private static void createOutputFiles() {
		File containerguidTextOutputFile = new File(PATH + FILENAME + "_containerguidText.txt");
		if (containerguidTextOutputFile.exists()) {
			containerguidTextOutputFile.delete();
		}
		File fragmentguidTextOutputFile = new File(PATH + FILENAME + "_fragmentguidText.txt");
		if (fragmentguidTextOutputFile.exists()) {
			fragmentguidTextOutputFile.delete();
		}
		File rangestartpathTextOutputFile = new File(PATH + FILENAME + "_rangestartpathText.txt");
		if (rangestartpathTextOutputFile.exists()) {
			rangestartpathTextOutputFile.delete();
		}
		File rangeendpathTextOutputFile = new File(PATH + FILENAME + "_rangeendpathText.txt");
		if (rangeendpathTextOutputFile.exists()) {
			rangeendpathTextOutputFile.delete();
		}
		File rangestartindexTextOutputFile = new File(PATH + FILENAME + "_rangestartindexText.txt");
		if (rangestartindexTextOutputFile.exists()) {
			rangestartindexTextOutputFile.delete();
		}
		File rangeendindexTextOutputFile = new File(PATH + FILENAME + "_rangeendindexText.txt");
		if (rangeendindexTextOutputFile.exists()) {
			rangeendindexTextOutputFile.delete();
		}
		File selectiontextTextOutputFile = new File(PATH + FILENAME + "_selectiontextText.txt");
		if (selectiontextTextOutputFile.exists()) {
			selectiontextTextOutputFile.delete();
		}
		File rangestartpageTextOutputFile = new File(PATH + FILENAME + "_rangestartpageText.txt");
		if (rangestartpageTextOutputFile.exists()) {
			rangestartpageTextOutputFile.delete();
		}
		File rangeendpageTextOutputFile = new File(PATH + FILENAME + "_rangeendpageText.txt");
		if (rangeendpageTextOutputFile.exists()) {
			rangeendpageTextOutputFile.delete();
		}

		File containerguidImageOutputFile = new File(PATH + FILENAME + "_containerguidImage.txt");
		if (containerguidImageOutputFile.exists()) {
			containerguidImageOutputFile.delete();
		}
		File fragmentguidImageOutputFile = new File(PATH + FILENAME + "_fragmentguidImage.txt");
		if (fragmentguidImageOutputFile.exists()) {
			fragmentguidImageOutputFile.delete();
		}
		File rangestartpageImageOutputFile = new File(PATH + FILENAME + "_rangestartpageImage.txt");
		if (rangestartpageImageOutputFile.exists()) {
			rangestartpageImageOutputFile.delete();
		}
		File rangeendpageImageOutputFile = new File(PATH + FILENAME + "_rangeendpageImage.txt");
		if (rangeendpageImageOutputFile.exists()) {
			rangeendpageImageOutputFile.delete();
		}

		try {
			containerguidTextWriter = new FileWriter(containerguidTextOutputFile);
			fragmentguidTextWriter = new FileWriter(fragmentguidTextOutputFile);
			rangestartpathTextWriter = new FileWriter(rangestartpathTextOutputFile);
			rangeendpathTextWriter = new FileWriter(rangeendpathTextOutputFile);
			rangestartindexTextWriter = new FileWriter(rangestartindexTextOutputFile);
			rangeendindexTextWriter = new FileWriter(rangeendindexTextOutputFile);
			selectiontextTextWriter = new FileWriter(selectiontextTextOutputFile);
			rangestartpageTextWriter = new FileWriter(rangestartpageTextOutputFile);
			rangeendpageTextWriter = new FileWriter(rangeendpageTextOutputFile);

			containerguidImageWriter = new FileWriter(containerguidImageOutputFile);
			fragmentguidImageWriter = new FileWriter(fragmentguidImageOutputFile);
			rangestartpageImageWriter = new FileWriter(rangestartpageImageOutputFile);
			rangeendpageImageWriter = new FileWriter(rangeendpageImageOutputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void closeWriters() {
		try {
			containerguidTextWriter.close();
			fragmentguidTextWriter.close();
			rangestartpathTextWriter.close();
			rangeendpathTextWriter.close();
			rangestartindexTextWriter.close();
			rangeendindexTextWriter.close();
			selectiontextTextWriter.close();
			rangestartpageTextWriter.close();
			rangeendpageTextWriter.close();

			containerguidImageWriter.close();
			fragmentguidImageWriter.close();
			rangestartpageImageWriter.close();
			rangeendpageImageWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
