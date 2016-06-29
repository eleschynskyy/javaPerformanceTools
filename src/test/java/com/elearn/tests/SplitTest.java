package com.elearn.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class SplitTest {

	public static void main(String[] args) {
//		generate64ByteString();
		List<Byte> pull = new ArrayList<Byte>();
		pull.add((byte)0);
		pull.add((byte)1);
		Collections.shuffle(pull);
		for(int i = 0; i < pull.size(); i++){
			System.out.println(pull.get(i));
		}
		byte[] action_values = new byte[pull.size()];
		for(int i = 0; i < pull.size(); i++){
			action_values[i] = pull.get(i);
			System.out.println(action_values[i]);
		}

		System.out.println("----------------");
	}

	private static void defineOptions() {
		ArrayList<String> availableSet = new ArrayList<String>();
		ArrayList<String> options = new ArrayList<String>();
		availableSet.add("1");
		availableSet.add("2");
		availableSet.add("3");
		availableSet.add("4");
		for(int i = 1; i <= 2; i++){
			int r = new Random().nextInt(availableSet.size());
			options.add(availableSet.get(r));
			availableSet.remove(r);
		}
		Iterator<String> i =  options.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}

	private static void makeJson() {
		HashSet<String> reviewersNames = new HashSet<String>();
		String principals = "[{\"email\":\"\",\"firstName\":\"\",\"gravatar\":null,\"isGroup\":false,\"lastName\":\"\",\"name\":\"lcmssetup\"},{\"email\":\"\",\"firstName\":\"\",\"gravatar\":null,\"isGroup\":false,\"lastName\":\"\",\"name\":\"xyadmin\"}]";
		JsonArray allReviewers = JsonArray.readFrom(principals);
		JsonArray reviewers = new JsonArray();
		Random rnd = new Random();
		JsonObject reviewer;
		reviewer = allReviewers.get(rnd.nextInt(allReviewers.size())).asObject();
		String reviewerName = reviewer.get("name").asString();
		boolean isGroup = reviewer.get("isGroup").asBoolean();
		JsonObject reviewerJson = new JsonObject();
		reviewerJson.add("name", reviewerName);
		reviewerJson.add("isGroup", isGroup);
		reviewers.add(reviewerJson);
		System.out.println(reviewers);
	}

	private static void extractJson() {
		HashSet<String> oldReviewersNames = new HashSet<String>();
		String jsonString = "{\"assignedBy\":{\"email\":null,\"firstName\":null,\"gravatar\":null,\"isGroup\":false,\"lastName\":null,\"name\":\"yaroslav.basovskyy\"},\"createdBy\":{\"email\":\"yaroslav.basovskyy@xyleme.com\",\"firstName\":\"Yaroslav\",\"gravatar\":\"b85d2d1e8c4969a532fdda61ff80574d\",\"isGroup\":false,\"lastName\":\"Basovskyy\",\"name\":\"yaroslav.basovskyy\"},\"documentGuid\":\"6e5da827-e093-4ca7-b512-71662421db71\",\"endDate\":\"2015-09-10T11:17:32Z\",\"guid\":\"c9365fdc-a139-4a13-b29a-05304185ddaa\",\"instructions\":null,\"mine\":false,\"name\":\"Our Solar System new - Review_ODCTG\",\"outputProfile\":\"Instructor Guide MSIS (Word) (Review Mode)\",\"permissions\":{\"edit\":true,\"view\":true},\"remindDates\":[],\"reviewers\":[{\"email\":\"bogdan.solianyk@xyleme.com\",\"firstName\":\"Bogdan\",\"gravatar\":\"c757eb8fbfaeb17d9c8f46e73a65314d\",\"isGroup\":false,\"lastName\":\"Solianyk\",\"name\":\"bogdan.solianyk\"},{\"email\":\"yaroslav.basovskyy@xyleme.com\",\"firstName\":\"Yaroslav\",\"gravatar\":\"b85d2d1e8c4969a532fdda61ff80574d\",\"isGroup\":false,\"lastName\":\"Basovskyy\",\"name\":\"yaroslav.basovskyy\"}],\"sendReminders\":true,\"startDate\":\"2015-06-27T17:46:35Z\",\"status\":\"open\"}";
		JsonObject review = JsonObject.readFrom(jsonString);
		JsonArray reviewers = review.asObject().get("reviewers").asArray();
		for(int i = 0; i <= reviewers.size() - 1; i++){
			String reviewerName = reviewers.get(i).asObject().get("name").asString();
			System.out.println(reviewerName);
			oldReviewersNames.add(reviewerName);
		}
		String principals = "[{\"email\":\"\",\"firstName\":\"\",\"gravatar\":null,\"isGroup\":false,\"lastName\":\"\",\"name\":\"lcmssetup\"},{\"email\":\"\",\"firstName\":\"\",\"gravatar\":null,\"isGroup\":false,\"lastName\":\"\",\"name\":\"xyadmin\"}]";
		JsonArray newReviewers = JsonArray.readFrom(principals);
		Random rnd = new Random();
		JsonObject reviewer = newReviewers.get(rnd.nextInt(newReviewers.size())).asObject();
		System.out.println(reviewer.get("name").asString());

		if(!oldReviewersNames.contains(reviewer.get("name").asString())){
			oldReviewersNames.add(reviewer.get("name").asString());
		}

		reviewers.add(reviewer);
		System.out.println("+++++++++++++++++++++");
		for(int i = 0; i <= reviewers.size() - 1; i++){
			String reviewerName = reviewers.get(i).asObject().get("name").asString();
			System.out.println(reviewerName);
		}
		System.out.println(reviewers);
	}

	private static void formatDate() {
		String dateStr = "2015-06-28T17:45:00Z";
//		2015-07-02T00:00:00+0300
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			Date date = formatter.parse(dateStr);
			System.out.println(date);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			System.out.println(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		System.out.println(formatter.format(c.getTime()));
	}

	private static void generate64ByteString(){
		byte[] r = new byte[64];
		new Random().nextBytes(r);
		String s = Base64.getEncoder().encodeToString(r);
//		String s = Base64.encodeBase64String(r);
		System.out.println(s);
	}

}
