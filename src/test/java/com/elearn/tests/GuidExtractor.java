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

public class GuidExtractor {

	private static String FILENAME = "1000-page-course";
//	private static String FILENAME = "test";
	private static String FILE_EXT = "xml";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br;
		FileWriter writerPages;
		FileWriter writerGuids;
		int pageIterator = 0;
		int guidIterator = 0;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File loadOutputFilePages = new File(PATH + FILENAME + "_PAGE.txt");
			if (loadOutputFilePages.exists()) {
				loadOutputFilePages.delete();
			}
			File loadOutputFileGuids = new File(PATH + FILENAME + "_GUID.txt");
			if (loadOutputFileGuids.exists()) {
				loadOutputFileGuids.delete();
			}
			writerPages = new FileWriter(loadOutputFilePages);
			writerGuids = new FileWriter(loadOutputFileGuids);
			Pattern pageGuidPattern = Pattern.compile(".*<Page xy:guid=\"(.*?)\">.*");
			Matcher pageGuidMatcher;
			Pattern guidPattern = Pattern.compile(".*xy:guid=\"(.*?)\">.*");
			Matcher guidMatcher;
			String line;
			while ((line = br.readLine()) != null) {
				pageGuidMatcher = pageGuidPattern.matcher(line);
				guidMatcher = guidPattern.matcher(line);
				if (pageGuidMatcher.matches()) {
//					writerPages.write("PAGE_"+ (pageIterator + 1) + "\t" + pageGuidMatcher.group(1) + "\n");
//					writerPages.write("vars.put(\"PAGE_"+ (pageIterator + 1) + "\", \"" + pageGuidMatcher.group(1) + ".html\");\n");
					writerPages.write("vars.put(\"MEDIA_"+ (pageIterator + 1) + "\", \"\");\n");
					pageIterator++;
				} else {
					if (guidMatcher.matches()) {
						writerGuids.write("FRAGMENTGUID_"+ (guidIterator + 1) + "\t" + guidMatcher.group(1) + "\n");
						guidIterator++;
					}
				}
			}
			br.close();
			writerPages.write("PAGE_COUNT\t" + pageIterator);
			writerGuids.write("GUID_COUNT\t" + guidIterator);
			writerPages.close();
			writerGuids.close();
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
