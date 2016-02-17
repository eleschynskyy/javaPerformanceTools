package com.elearn.tests;

import java.io.File;

import com.xyleme.media.client.MediaClient;
import com.xyleme.media.client.MediaFile;

public class APIMediaClientTest {

	public static void main(String[] args) {
		addMedia();
		System.out.println("Done");
	}

	private static void listMedia() {
		String username = "mongo_4";
		String password = "mongodb";
		String url = "https://lcms-isolated.xyleme.com:10380/media-service/xml";
		MediaClient client = MediaClient.login(url, username, password);
//		client.list("/PT", false, EnumSet.noneOf(Flags.class));
	}

	private static void addMedia() {
		String username = "xpe_perf1";
		String password = "5oMeTh1n65ecrEt";
		String url = "https://lcms-isolated.xyleme.com:10280/media-service/xml";
		MediaClient client = MediaClient.login(url, username, password);
		String mediaDrivePath = "/PT/" + System.currentTimeMillis() + "error_statistic.png";
		String mediaFilePath = "D:/Xyleme/performance/products/lcms/testing/15-Jul-2015/TEST_1/image/error_statistic.png";
		MediaFile mediaFile = new MediaFile(mediaDrivePath, false);
		mediaFile.setInputStream(new File(mediaFilePath));
		client.put(mediaFile);
		client.logout();
	}

}
