package com.elearn.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xyleme.sdoc.client.FolderInfo;
import com.xyleme.sdoc.client.SDocInfo;
import com.xyleme.sdoc.client.SDocService;
import com.xyleme.sdoc.client.SSConstants;
import com.xyleme.sdoc.client.UserInfo;
import com.xyleme.sdoc.client.properties.FragmentProperties;
import com.xyleme.sdoc.client.search.ContentTerm;
import com.xyleme.sdoc.client.search.PropertyRangeTerm;
import com.xyleme.sdoc.client.search.PropertyTerm;
import com.xyleme.sdoc.client.search.SDocQuery;

public class APILcmsTest {

	public static void main(String[] args) {
//		String username = "xpe_perf1";
//		String password = "5oMeTh1n65ecrEt";
//		String guid = "1c6be087-e0dc-4c02-97ba-5b31e4174bcf";
//		String url = "https://lcms-isolated.xyleme.com:10280/sdoc-service/xml";
//		SDocService client = SDocService.login(url, username, password);
		/*
		FolderInfo[] fList = client.folderlist(new FolderInfo());
		for(FolderInfo f: fList){
			System.out.println("Folder: " + f.getName());
		}
		FolderInfo fld = client.folderget("TESTFOLDER1");
		System.out.println("\nFound: " + fld.getName());
//		FolderInfo[] fInfoList = client.folderlist(new FolderInfo());
		FolderInfo fld = client.folderget("TESTFOLDER1");*/
		/*
		SDocQuery query = new SDocQuery(new PropertyTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true), null, null);
		SDocInfo info = client.search(new FolderInfo(), query, null, SSConstants.GET_PROPS, SSConstants.NO_RESOLVE, false);
//		SDocInfo info = client.search(new FolderInfo(), query, null, SSConstants.GET_GUIDS, SSConstants.FULL_RESOLVE, false);
		List<String> guids = info.getGuids();
		for(String g: guids){
			System.out.println("GUID: " + g);
		}
		/*
		FolderInfo fInfo = client.foldercreate(new FolderInfo(), "hjgyhjghjghj");
        String guid = client.importXML(xmlContent1, true, fInfo);
        SDocQuery query = new SDocQuery(new PropertyTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true), null, null)
        SDocInfo info = client.search(new FolderInfo(), query, null, SSConstants.GET_PROPS, SSConstants.NO_RESOLVE, false)
        SDocQuery sQuery = new SDocQuery(new PropertyTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true), null, null);
        SDocInfo sInfo = client.search(fInfo, sQuery, null, SSConstants.GET_PROPS | SSConstants.GET_SECURITY |  SSConstants.GET_FOLDERS , SSConstants.NO_RESOLVE, false);
        */
//		checkTest();
//		multipleImport();
//		importToExistingFolder();
//		deleteFolder();
//		listDocumentsInFolder();
		getUsers();
		System.out.println("Done");
	}

	private static void getUsers() {
		String username = "yevhen.leshchynskyy@xyleme.com";
		String password = "Qwerty1234";
		String url = "https://studio-perf4.qa.xyleme.com/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
		String spsFolder = "SpsPerformanceTestsFolder";
		FolderInfo fInfo = client.folderget(new FolderInfo(), spsFolder);
		PropertyRangeTerm pt = new PropertyRangeTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true, true);
		SDocQuery sQuery = new SDocQuery(pt, null, null);
		SDocInfo sInfo = client.search(fInfo, sQuery, null, SSConstants.GET_PROPS, SSConstants.NO_RESOLVE, false);
		int i = 0;
		for(String guid: sInfo.getGuids()){
			client.delete(guid, true);
			i++;
			System.out.println("deleted " + guid);
		}
		client.logout();
	}

	public static void checkTest() {
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10280/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
//		FolderInfo fInfo = client.foldercreate(new FolderInfo(), "FFFF");

		//
		String folderPath = "D:/Xyleme/performance/products/xpe/jmx/import";
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		int numberOfFiles = listOfFiles.length;
		Random r = new Random();
		int index = r.nextInt(numberOfFiles);
		String filepath = listOfFiles[index].getAbsolutePath();

		//getting content of the xml
		StringBuilder content = new StringBuilder();
		File file = new File(filepath);
		InputStreamReader fstream = null;
		try {
			fstream = new InputStreamReader(new FileInputStream(file), "UTF-8");
			int data;
			while ((data = fstream.read()) != -1) {
				content.append((char) data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fstream != null) fstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//

		String guid = client.importXML(content.toString(), true);
//		SDocQuery query = new SDocQuery(new PropertyTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true), null, null);
		SDocQuery query = new SDocQuery(new ContentTerm(guid), null, null);
		SDocInfo info = client.search(query, null, SSConstants.GET_ALL, SSConstants.NO_RESOLVE, false);
		SDocQuery sQuery = new SDocQuery(new PropertyTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true), null, null);
		SDocInfo sInfo = client.search(sQuery, null, SSConstants.GET_ALL, SSConstants.FULL_RESOLVE_NO_REPEAT, false);
		System.out.println("Searching for " + guid + "...");
		if(info.getGuids().size() > 0  || sInfo.getGuids().size() > 0) {
			System.out.println("INFO" + info.getGuids().size());
			System.out.println("SINFO" + sInfo.getGuids().size());
			for (String gid: info.getGuids()) {
				System.out.println("ITEM: " + gid);
			}
		}
	    }

	public static void multipleImport() {
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10380/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
		FolderInfo fInfo = client.foldercreate(new FolderInfo(), "ImportFolder");
		//
		String folderPath = "D:/Xyleme/performance/products/xpe/jmx/import";
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		int numberOfFiles = listOfFiles.length;
		Random r = new Random();
		int index = r.nextInt(numberOfFiles);
		String filepath = listOfFiles[index].getAbsolutePath();

		//getting content of the xml
		StringBuilder content = new StringBuilder();
		File file = new File(filepath);
		InputStreamReader fstream = null;
		try {
			fstream = new InputStreamReader(new FileInputStream(file), "UTF-8");
			int data;
			while ((data = fstream.read()) != -1) {
				content.append((char) data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fstream != null) fstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//
		String guid = client.importXML(content.toString(), true, fInfo);
		System.out.println(guid);
	}

	public static void importToExistingFolder() {
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10380/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
		//
		String folderPath = "D:/Xyleme/performance/products/xpe/jmx/import";
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		int numberOfFiles = listOfFiles.length;
		Random r = new Random();
		int index = r.nextInt(numberOfFiles);
		String filepath = listOfFiles[index].getAbsolutePath();

		//getting content of the xml
		StringBuilder content = new StringBuilder();
		File file = new File(filepath);
		InputStreamReader fstream = null;
		try {
			fstream = new InputStreamReader(new FileInputStream(file), "UTF-8");
			int data;
			while ((data = fstream.read()) != -1) {
				content.append((char) data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fstream != null) fstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//
		FolderInfo fInfo = client.folderget("ImportFolder");
		String guid = client.importXML(content.toString(), true, fInfo);
//		client.checkin(guid, false);
		System.out.println(guid);
	}

	public static void deleteFolder() {
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10380/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
		client.logout();
	}

	public static void listDocumentsInFolder(){
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10280/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
		FolderInfo fInfo = client.folderget("ImportAPI");
		PropertyRangeTerm pt = new PropertyRangeTerm(FragmentProperties.FRAGMENT_ROOT_OF_DOCUMENT, true, true);
		SDocQuery sQuery = new SDocQuery(pt, null, null);
		SDocInfo sInfo = client.search(fInfo, sQuery, null, SSConstants.GET_PROPS | SSConstants.GET_SECURITY |  SSConstants.GET_FOLDERS , SSConstants.NO_RESOLVE, false);
		if(sInfo.getGuids().size() > 0) {
			System.out.println("SINFO" + sInfo.getGuids().size());
			for (String gid: sInfo.getGuids()) {
				System.out.println("ITEM: " + gid);
			}
		}
	}

	public static void simpleSearch() {
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10280/sdoc-service/xml";
		SDocService client = SDocService.login(url, username, password);
		ContentTerm cTerm = new ContentTerm("Our");
		SDocQuery sQuery = new SDocQuery(cTerm, null, cTerm);
		SDocInfo sInfo = client.search(sQuery, null, SSConstants.GET_ALL, SSConstants.NO_RESOLVE);
		if(sInfo.getGuids().size() > 0) {
			System.out.println("SINFO" + sInfo.getGuids().size());
			for (String gid: sInfo.getGuids()) {
				System.out.println("ITEM: " + gid);
			}
		}
	}

}
