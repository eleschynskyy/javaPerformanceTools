package com.elearn.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

public class MsisFileTypeAnalyzer {

	private static final String OUTPUT_FILE = "D:/Xyleme/performance/products/msis/output.txt";
	private static String FOLDER_PATH = "D:/Xyleme/performance/products/msis/output";

	public static void main(String[] args) throws IOException {
		File folder = new File(FOLDER_PATH);
		File[] listOfFiles = folder.listFiles();
		File outputFile = new File(OUTPUT_FILE);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		String fileType;
		FileWriter writer = new FileWriter(outputFile);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				try {
					System.out.println(FilenameUtils.getExtension(listOfFiles[i].getName()) + "|" + 
							getAttributesDate(listOfFiles[i].getAbsolutePath()).getTime() + "|" + 
							getAttributesString(listOfFiles[i].getAbsolutePath())
							);
					fileType = FilenameUtils.getExtension(listOfFiles[i].getName());
					if (fileType.equals("pdf")) {
						fileType = "1";
					}
					writer.write(getAttributesDate(listOfFiles[i].getAbsolutePath()).getTime() + "|" + 
								 getAttributesString(listOfFiles[i].getAbsolutePath()) + "|" +
								 fileType + "\n"
								 );
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		writer.close();
		System.out.println("Done");
	}
	
	public static Date getAttributesDate(String pathStr) throws IOException {
	    Path p = Paths.get(pathStr);
	    BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
	    Date date = new Date(view.lastModifiedTime().toMillis());
	    return date;
	  }
	
	public static String getAttributesString(String pathStr) throws IOException {
	    Path p = Paths.get(pathStr);
	    BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();
	    Date date = new Date(view.lastModifiedTime().toMillis());
	    Pattern pattern = Pattern.compile("(Mon|Tue|Wed|Thu|Fri|Sat|Sun)\\s([A-z]{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}).*");
	    Matcher matcher = pattern.matcher(date.toString());
	    if (matcher.matches()) {
	    	return matcher.group(2);
	    }
	    return null;
	  }

}
