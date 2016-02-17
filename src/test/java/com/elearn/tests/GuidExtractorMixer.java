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

public class GuidExtractorMixer {

	private static String FILENAME = "1000-page-course";
	private static String FILE_EXT = "xml";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br;
		FileWriter writerPages;
		FileWriter writerPages1;
		int pageIterator = 0;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File loadOutputFilePages = new File(PATH + FILENAME + "_PARABLOCK1.txt");
			if (loadOutputFilePages.exists()) {
				loadOutputFilePages.delete();
			}
			File loadOutputFilePages1 = new File(PATH + FILENAME + "_PARABLOCK2.txt");
			if (loadOutputFilePages1.exists()) {
				loadOutputFilePages1.delete();
			}
			writerPages = new FileWriter(loadOutputFilePages);
			writerPages1 = new FileWriter(loadOutputFilePages1);
			Pattern pageGuidPattern = Pattern.compile(".*<ParaBlock xy:guid=\"(.*?)\">.*");
			Matcher pageGuidMatcher;
			String line;
			boolean order = false;
			while ((line = br.readLine()) != null) {
				pageGuidMatcher = pageGuidPattern.matcher(line);
				if (pageGuidMatcher.matches()) {
					if (!order) {
						writerPages.write(pageGuidMatcher.group(1) + "\n");
					} else {
						writerPages1.write(pageGuidMatcher.group(1) + "\n");
					}
					order = !order;
					pageIterator++;
				}
			}
			br.close();
//			writerPages.write("PAGE_COUNT\t" + pageIterator);
			writerPages.close();
			writerPages1.close();
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
