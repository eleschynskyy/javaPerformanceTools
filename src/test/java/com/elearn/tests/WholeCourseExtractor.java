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

public class WholeCourseExtractor {

	private static String FILENAME = "wps_updated";
	private static String FILE_EXT = "jmx";
	private static String PATH = "D:/Xyleme/performance/products/xpe/output/";
	private static String PATHNAME = PATH + FILENAME + "." + FILE_EXT;

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br;
		FileWriter writerPages;
		FileWriter writerMedia;
		FileWriter writerPngs;
		int pageIterator = 1;
		int pngIterator = 1;
		try {
			br = new BufferedReader(new FileReader(PATHNAME));
			File loadOutputFileMedia = new File(PATH + FILENAME + "_MEDIA.txt");
			if (loadOutputFileMedia.exists()) {
				loadOutputFileMedia.delete();
			}
			File loadOutputFilePages = new File(PATH + FILENAME + "_PAGES.txt");
			if (loadOutputFilePages.exists()) {
				loadOutputFilePages.delete();
			}
			File loadOutputFilePngs = new File(PATH + FILENAME + "_PNGS.txt");
			if (loadOutputFilePngs.exists()) {
				loadOutputFilePngs.delete();
			}
			writerMedia = new FileWriter(loadOutputFileMedia);
			writerPages = new FileWriter(loadOutputFilePages);
			writerPngs = new FileWriter(loadOutputFilePngs);
			Pattern pageGuidPattern = Pattern.compile(".*testname=\"\\$\\{BASE_PREVIEW_URL}\\/(.+?\\.html).*?\" enabled=\"true\".*");
			Pattern pngPattern = Pattern.compile(".*testname=\"(\\$\\{BASE_PREVIEW_URL}\\/resources\\/equation\\/.+?\\/.+?\\.png).*?\" enabled=\"true\".*");
			Pattern mediaPattern = Pattern.compile(".*testname=\"(https:\\/\\/studio-perf2\\.qa\\.xyleme\\.com\\/media-service\\/.+?)\" enabled=\"true\".*");
			Matcher pageGuidMatcher;
			Matcher pngMatcher;
			Matcher mediaMatcher;
			String line;
			String prefix = "";
			String pageId = "";
			String pngId = "";
			while ((line = br.readLine()) != null) {
				pageGuidMatcher = pageGuidPattern.matcher(line);
				pngMatcher = pngPattern.matcher(line);
				mediaMatcher = mediaPattern.matcher(line);
				if (pageGuidMatcher.matches()) {
					pageId = pageGuidMatcher.group(1);
					writerPages.write("vars.put(\"PAGE_" + (pageIterator + 1) + "\", \"" + pageId + "\");\n");
					writerMedia.write(prefix + "vars.put(\"MEDIA_" + (pageIterator + 1) + "\", \"");
					writerPngs.write(prefix + "vars.put(\"PNG_" + (pageIterator + 1) + "\", \"");
					prefix = "\");\n";
//					writerPages.write("PAGE_" + (pageIterator + 1) + "\t" + pageId + "\n");
//					writerMedia.write(prefix + "MEDIA_" + (pageIterator + 1) + "\t");
//					prefix = "\n";
					pageIterator++;
				}
				if (mediaMatcher.matches()) {
					String mediaUrl = mediaMatcher.group(1);
					writerMedia.write(mediaUrl + "<TAB>");
				}
				if (pngMatcher.matches()) {
					String pngUrl = pngMatcher.group(1);
					writerPngs.write(pngUrl + "<TAB>");
				}
			}
			br.close();
//			writerPages.write("PAGE_COUNT\t" + (pageIterator - 1));
			writerMedia.write("\");");
			writerPngs.write("\");");
			writerPages.write("vars.put(\"PAGE_COUNT\", \"" + (pageIterator - 1) + "\");");
			writerPages.close();
			writerMedia.close();
			writerPngs.close();
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
