package com.elearn.tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

public class SendingPostRequestWithXmlAttached {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		String currentURL = "https://cloudplayer.qa.bravais.com/cre/init?&guid=&courseType=SCORM13&manifestFile=https://core-qa.bravais.com/api/dynamic/documentVersions/4955/files/122719/Scorm2004Content/manifest.xml&version=&vm=normal&pid=&ft=&as=&debug=&rg=&ProxySCO=&cpa=https://cloudplayer.qa.bravais.com/preview&bcpVer=V_3.10&mode=normal&token=undefined";
		String headerTitle = "Bravais-QA-Context";
		String headerValue = "jnMMrBdzoPmz/KseVofbhuolZGquduTaATJi9y8lCGUPw5mqXNohXuLFMkJCXpUi1vJpW+IvDLwi2FWVbsNRVYEC6XdT9jHbOMRUeadERIoZ++7ffigNDgPhp8j7uU5tXYSuSMFYsLXfRSLictKNOmomdR8fErXjowys3Ofp+IKRXJ7RkRtV/hnd8p2Tsq/+CBKl0nozVII=";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(currentURL);
		post.addHeader(headerTitle, headerValue);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
       	post.addHeader("X-Requested-With", "XMLHttpRequest");

		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie cookie = new BasicClientCookie(headerTitle, "\""
				+ headerValue + "\"");
		cookie.setDomain(".bravais.com");
		cookie.setPath("/");
		cookieStore.addCookie(cookie);
		((AbstractHttpClient) client).setCookieStore(cookieStore);
		
		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><manifest xmlns=\"http://www.imsglobal.org/xsd/imscp_v1p1\" xmlns:exslt=\"http://exslt.org/common\" xmlns:hp=\"http://xyleme.com/htmlpagelink\" xmlns:imsss=\"http://www.imsglobal.org/xsd/imsss\" xmlns:adlnav=\"http://www.adlnet.org/xsd/adlnav_v1p3\" xmlns:adlseq=\"http://www.adlnet.org/xsd/adlseq_v1p3\" xmlns:adlcp=\"http://www.adlnet.org/xsd/adlcp_v1p3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.imsglobal.org/xsd/imscp_v1p1              imscp_v1p1.xsd    http://www.adlnet.org/xsd/adlcp_v1p3 adlcp_v1p3.xsd              http://www.adlnet.org/xsd/adlseq_v1p3    adlseq_v1p3.xsd              http://www.adlnet.org/xsd/adlnav_v1p3 adlnav_v1p3.xsd    http://www.imsglobal.org/xsd/imsss imsss_v1p0.xsd\" identifier=\"SS39e9a1cc-b46b-48c0-a50f-eb368e1fb516\">" + 
				"  <metadata>" + 
				"    <schema>ADL SCORM</schema>" + 
				"    <schemaversion>2004 3rd Edition</schemaversion>" + 
				"    <lms>Default</lms>" + 
				"    <adlcp:location>metadata_course.xml</adlcp:location>" + 
				"    <allowskipquestion>false</allowskipquestion>" + 
				"  </metadata>" + 
				"  <organizations default=\"defaultOrganization\">" + 
				"    <organization identifier=\"defaultOrganization\">" + 
				"      <title>IP Telephony</title>" + 
				"      <item identifier=\"Item1\" isvisible=\"true\">" + 
				"        <title>Convergence</title>" + 
				"        <item identifier=\"Item1.1\" identifierref=\"R_7765c3d7-f00a-43aa-acdd-e26af8dac6f8\" isvisible=\"true\">" + 
				"          <title>Introduction</title>" + 
				"        </item>" + 
				"        <item identifier=\"Item1.3\" isvisible=\"true\">" + 
				"          <title>Packet Telephony</title>" + 
				"          <item identifier=\"Item1.3.1\" identifierref=\"R_e6c0c28e-ba08-4ed5-9a9c-4583a4b3b4ce\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.5\" identifierref=\"R_85add2fc-2ea1-41e1-aa4e-e36e2f6311f8\" isvisible=\"true\">" + 
				"            <title>Packet Telephony Flash Animation</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.6\" identifierref=\"R_05824a99-3a7d-49c1-a218-5c176e0a23be\" isvisible=\"true\">" + 
				"            <title>Networks</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.7\" identifierref=\"R_fe2f9cd4-71e5-4c27-a529-62a12ae88f92\" isvisible=\"true\">" + 
				"            <title>The Present: Separate Networks</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.8\" identifierref=\"R_2aeabf74-9ac2-4150-bb8d-9f08c44c7936\" isvisible=\"true\">" + 
				"            <title>How Packet Telephony Works</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.9\" identifierref=\"R_b9e040bc-0902-4c7a-ad47-160e859641df\" isvisible=\"true\">" + 
				"            <title>Packet Telephony Standards</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem1.3.10\" isvisible=\"true\">" + 
				"            <title>Internet Telephony Categories</title>" + 
				"            <item identifier=\"Item1.3.10\" identifierref=\"R_d1a3382c-991d-4660-b967-8a44a7313f0c\" isvisible=\"false\">" + 
				"              <title>Internet Telephony Categories</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.10.nested.3\" identifierref=\"R_c3bbc631-d5e6-4ec0-9119-b809cb4fde4d\" isvisible=\"true\">" + 
				"              <title>Computer-to-Computer</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.10.nested.4\" identifierref=\"R_f97bab73-ea0c-482c-bfac-da29a918d317\" isvisible=\"true\">" + 
				"              <title>Computer-to-Telephone</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.10.nested.5\" identifierref=\"R_179a038a-9a82-46a6-9111-8d85f3e69c66\" isvisible=\"true\">" + 
				"              <title>Telephone-to-Telephone</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.10.nested.6\" identifierref=\"R_8d452f7e-268b-41b6-a940-a36692e23ff2\" isvisible=\"true\">" + 
				"              <title>Web-to-Call Center Systems</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem1.3.11\" isvisible=\"true\">" + 
				"            <title>Telephony Gateway</title>" + 
				"            <item identifier=\"Item1.3.11\" identifierref=\"R_e6f97bec-40e6-4d5f-a720-f7494701e463\" isvisible=\"false\">" + 
				"              <title>Telephony Gateway</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.11.nested.3\" identifierref=\"R_d03067bf-28d9-4390-9b15-90a1269f11e0\" isvisible=\"true\">" + 
				"              <title>Dedicated Servers with Voice Cards</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.11.nested.4\" identifierref=\"R_cbca2530-3bd6-4450-8060-f32232a9ca46\" isvisible=\"true\">" + 
				"              <title>Router or Access Concentrator with Voice Cards</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.11.nested.5\" identifierref=\"R_4027cd4d-5633-4f9b-80f1-3f647200ee98\" isvisible=\"true\">" + 
				"              <title>Wiring an RJ45 Cables</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.12\" identifierref=\"R_a143ca71-4c90-4e2a-95b4-2feccad1e1cf\" isvisible=\"true\">" + 
				"            <title>Packet Telephony Products</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.3.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item1.3.4.1.0\" identifierref=\"R_68f94426-df43-4580-a027-f114853fca33\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.3.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item1.3.4.1.1.2.1\" identifierref=\"R_2e853d98-f044-4a7b-b0dc-adf1a7716057\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"2e853d98-f044-4a7b-b0dc-adf1a7716057\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-2e853d98-f044-4a7b-b0dc-adf1a7716057\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.3.4.1.1.3.1\" identifierref=\"R_e1f85d60-2f94-4e58-a543-430287137295\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"e1f85d60-2f94-4e58-a543-430287137295\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-e1f85d60-2f94-4e58-a543-430287137295\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.3.4.1.1.4.1\" identifierref=\"R_58e5a9d9-b19c-43fb-88bb-8aaa8c6f4dd7\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"58e5a9d9-b19c-43fb-88bb-8aaa8c6f4dd7\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-58e5a9d9-b19c-43fb-88bb-8aaa8c6f4dd7\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.3.4.1.1.5.1\" identifierref=\"R_34853279-9347-47d8-bb1e-73374b7ddd78\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"34853279-9347-47d8-bb1e-73374b7ddd78\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-34853279-9347-47d8-bb1e-73374b7ddd78\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.3.4.1.1.6.1\" identifierref=\"R_9b66ca30-07aa-4bee-beb8-bfce5112e64a\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"9b66ca30-07aa-4bee-beb8-bfce5112e64a\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-9b66ca30-07aa-4bee-beb8-bfce5112e64a\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.3.4.1.1.7.1\" identifierref=\"R_95111c31-a919-4536-ab8c-b3da2f00c616\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"95111c31-a919-4536-ab8c-b3da2f00c616\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-95111c31-a919-4536-ab8c-b3da2f00c616\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem1.3\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <imsss:randomizationControls selectionTiming=\"never\" reorderChildren=\"true\" randomizationTiming=\"onEachNewAttempt\"/>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem1.3\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem1.3\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item1.4\" isvisible=\"true\">" + 
				"          <title>General QoS Concepts</title>" + 
				"          <item identifier=\"Item1.4.1\" identifierref=\"R_b0889873-4064-4815-936f-c4608558a05b\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.4.5\" identifierref=\"R_8e4ecdf2-a194-4532-bd0d-e64e4a8af58e\" isvisible=\"true\">" + 
				"            <title>General QoS Concepts</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.4.6\" identifierref=\"R_bec6c91c-351c-4051-8bde-906588af7541\" isvisible=\"true\">" + 
				"            <title>Network Requirements for Convergence</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem1.4.7\" isvisible=\"true\">" + 
				"            <title>Converged Network Architecture</title>" + 
				"            <item identifier=\"Item1.4.7\" identifierref=\"R_8a15acfd-35d5-4e5e-bd20-f684a46d10fb\" isvisible=\"false\">" + 
				"              <title>Converged Network Architecture</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.4.7.nested.3\" identifierref=\"R_c0da393a-3d06-4e01-be98-243c9e962e78\" isvisible=\"true\">" + 
				"              <title>A Hybrid Approach to Convergence</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.4.7.nested.4\" identifierref=\"R_d241bfbe-0370-4b61-80e2-c3fb71a14c5d\" isvisible=\"true\">" + 
				"              <title>Parallel WAN Paths for QoS</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.4.8\" identifierref=\"R_602da7b9-0b8e-4549-891d-baebdeba3418\" isvisible=\"true\">" + 
				"            <title>Voice Quality</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.4.9\" identifierref=\"R_34b573b8-72b1-493b-bd78-79b59ec2b46a\" isvisible=\"true\">" + 
				"            <title>The QoS Parameters</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.4.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item1.4.4.1.0\" identifierref=\"R_cbd921a4-f554-4d5b-b44c-9bbb622ad8f7\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.4.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item1.4.4.1.1.1.1\" identifierref=\"R_d20d3b7d-297c-4345-b5a2-adcb41893e23\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"d20d3b7d-297c-4345-b5a2-adcb41893e23\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-d20d3b7d-297c-4345-b5a2-adcb41893e23\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.4.4.1.1.2.1\" identifierref=\"R_dd9e8ab2-f56d-4c20-afc7-3622d930748f\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"dd9e8ab2-f56d-4c20-afc7-3622d930748f\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-dd9e8ab2-f56d-4c20-afc7-3622d930748f\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.4.4.1.1.3.1\" identifierref=\"R_0074ad98-d553-47ef-a877-1639550aa757\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"0074ad98-d553-47ef-a877-1639550aa757\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-0074ad98-d553-47ef-a877-1639550aa757\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.4.4.1.1.4.1\" identifierref=\"R_c0959643-0a78-4b4b-9309-9f4bba4dfb1f\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"c0959643-0a78-4b4b-9309-9f4bba4dfb1f\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-c0959643-0a78-4b4b-9309-9f4bba4dfb1f\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem1.4\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem1.4\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem1.4\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item1.5\" isvisible=\"true\">" + 
				"          <title>H.323 Standard for Packet Multimedia</title>" + 
				"          <item identifier=\"Item1.5.1\" identifierref=\"R_6f37a4de-edc4-4ff9-baf3-c0f80d4b2b34\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.5.5\" identifierref=\"R_508db213-a533-4db6-85c1-b40af6913dbc\" isvisible=\"true\">" + 
				"            <title>H.323 Standard Flash Lesson</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.5.6\" identifierref=\"R_217182fc-faa7-4cb4-afca-03f891e6d385\" isvisible=\"true\">" + 
				"            <title>Other Standards of the H.323 Family</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem1.5.7\" isvisible=\"true\">" + 
				"            <title>Fundamental H.323 Network Components</title>" + 
				"            <item identifier=\"Item1.5.7\" identifierref=\"R_9400f01c-1f06-4014-86bc-7813288b7292\" isvisible=\"false\">" + 
				"              <title>Fundamental H.323 Network Components</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.7.nested.3\" identifierref=\"R_7ac5b7d6-beb6-440d-9e04-f18646704bb6\" isvisible=\"true\">" + 
				"              <title>Gatekeepers</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.7.nested.4\" identifierref=\"R_3551e310-8215-4de2-9066-73c0662a50b6\" isvisible=\"true\">" + 
				"              <title>MGCP Network Components</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem1.5.8\" isvisible=\"true\">" + 
				"            <title>The H.323 Protocols and Their Effect on Voice Stream QoS</title>" + 
				"            <item identifier=\"Item1.5.8\" identifierref=\"R_27775b37-1fbb-4a09-9032-bf06aca5b1b4\" isvisible=\"false\">" + 
				"              <title>The H.323 Protocols and Their Effect on Voice Stream QoS</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.8.nested.3\" identifierref=\"R_48e90929-fb74-4365-a214-f3f8b49dcd58\" isvisible=\"true\">" + 
				"              <title>H.323 Protocols</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"FolderOfItem1.5.8.nested.4\" isvisible=\"true\">" + 
				"              <title>Audio Codecs</title>" + 
				"              <item identifier=\"Item1.5.8.nested.4\" identifierref=\"R_ced31b64-92ac-4447-8966-2101a19e09cc\" isvisible=\"false\">" + 
				"                <title>Audio Codecs</title>" + 
				"                <imsss:sequencing/>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.5.8.nested.4.nested.3\" identifierref=\"R_3a2e795c-aa99-4836-8420-841db5b85763\" isvisible=\"true\">" + 
				"                <title>G.711 Companding Algorithm</title>" + 
				"                <imsss:sequencing/>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.5.8.nested.4.nested.4\" identifierref=\"R_680f0782-5a53-4c28-a106-554a704b8775\" isvisible=\"true\">" + 
				"                <title>MOS</title>" + 
				"                <imsss:sequencing/>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.8.nested.5\" identifierref=\"R_7fa51c29-53a7-4870-9619-390a02bb34a1\" isvisible=\"true\">" + 
				"              <title>RTP</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.8.nested.6\" identifierref=\"R_4b54270b-881a-41e8-b0b3-dbadc956ce7b\" isvisible=\"true\">" + 
				"              <title>Control Signaling: H.245</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem1.5.9\" isvisible=\"true\">" + 
				"            <title>Integration with the Digital PSTN</title>" + 
				"            <item identifier=\"Item1.5.9\" identifierref=\"R_48e1b47f-431a-4bed-93a1-ef4cfe79d4fb\" isvisible=\"false\">" + 
				"              <title>Integration with the Digital PSTN</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.9.nested.3\" identifierref=\"R_dff479fb-b4ed-49ac-9d5b-69f412783f88\" isvisible=\"true\">" + 
				"              <title>ISDN/VoIP Integration</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.9.nested.4\" identifierref=\"R_4e1a7750-c64e-4cb1-85bc-c6af9390337d\" isvisible=\"true\">" + 
				"              <title>Adding SS7 to VoIP</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item1.5.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item1.5.4.1.0\" identifierref=\"R_8c72e124-a3c4-4c97-b77d-b33288193b49\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item1.5.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item1.5.4.1.1.1.1\" identifierref=\"R_d9fb7e74-294f-4d42-ba8c-fa230349e911\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"d9fb7e74-294f-4d42-ba8c-fa230349e911\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-d9fb7e74-294f-4d42-ba8c-fa230349e911\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.5.4.1.1.2.1\" identifierref=\"R_9a50fe52-d2a2-4036-8798-65c5c1de3716\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"9a50fe52-d2a2-4036-8798-65c5c1de3716\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-9a50fe52-d2a2-4036-8798-65c5c1de3716\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item1.5.4.1.1.3.1\" identifierref=\"R_788aad62-8f9e-40c2-9491-cae759fa1bcc\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"788aad62-8f9e-40c2-9491-cae759fa1bcc\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-788aad62-8f9e-40c2-9491-cae759fa1bcc\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem1.5\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem1.5\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem1.5\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <imsss:sequencing>" + 
				"          <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"          <imsss:objectives>" + 
				"            <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\"/>" + 
				"          </imsss:objectives>" + 
				"        </imsss:sequencing>" + 
				"      </item>" + 
				"      <item identifier=\"Item2\" isvisible=\"true\">" + 
				"        <title>OSI Model</title>" + 
				"        <item identifier=\"Item2.1\" identifierref=\"R_44561233-eaae-4366-bcaa-b3e7f5ac3c9a\" isvisible=\"true\">" + 
				"          <title>Introduction</title>" + 
				"        </item>" + 
				"        <item identifier=\"Item2.3\" isvisible=\"true\">" + 
				"          <title>The Physical Layer</title>" + 
				"          <item identifier=\"Item2.3.1\" identifierref=\"R_9d113cf6-5c06-4650-8a1f-8c61cfb0d507\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.3.5\" identifierref=\"R_6d15b04a-11a2-4725-98d6-2f87ce0987f8\" isvisible=\"true\">" + 
				"            <title>Physical Layer Flash Animation</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.3.6\" identifierref=\"R_8fd210c1-c98a-4f14-9798-0983bb2e303c\" isvisible=\"true\">" + 
				"            <title>A Stream of Bits</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.3.7\" identifierref=\"R_a1c17b3d-1828-4f3f-a2e6-1d007fcaa99e\" isvisible=\"true\">" + 
				"            <title>Frames Across a Link</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.3.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item2.3.4.1.0\" identifierref=\"R_a2527072-d2a5-41c8-9b78-20103ee955b7\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.3.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item2.3.4.1.1.1.1\" identifierref=\"R_3251a38a-1ea9-4d8a-bcd3-dca2bf4a7696\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"3251a38a-1ea9-4d8a-bcd3-dca2bf4a7696\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-3251a38a-1ea9-4d8a-bcd3-dca2bf4a7696\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.3.4.1.1.2.1\" identifierref=\"R_a2b1d1d1-fc6b-4c72-98b0-2aec37669b70\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"a2b1d1d1-fc6b-4c72-98b0-2aec37669b70\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-a2b1d1d1-fc6b-4c72-98b0-2aec37669b70\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem2.3\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem2.3\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem2.3\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item2.4\" isvisible=\"true\">" + 
				"          <title>Data Link Layer</title>" + 
				"          <item identifier=\"Item2.4.1\" identifierref=\"R_032a3b87-d03c-41f1-9585-015e142df2a0\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.4.5\" identifierref=\"R_af4a5174-937f-4722-9577-2349856fb4b3\" isvisible=\"true\">" + 
				"            <title>Data Link Layer Flash Animation</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem2.4.6\" isvisible=\"true\">" + 
				"            <title>Data Link Layer Services</title>" + 
				"            <item identifier=\"Item2.4.6\" identifierref=\"R_ffd7041a-6b72-49a4-80fa-911752c93886\" isvisible=\"false\">" + 
				"              <title>Data Link Layer Services</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.4.6.nested.3\" identifierref=\"R_0b7805fd-c4c3-4998-b7ef-19155f51351a\" isvisible=\"true\">" + 
				"              <title>On the Transmitting Node</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.4.6.nested.4\" identifierref=\"R_455c769c-f335-4b4a-91d4-6eb92fd03321\" isvisible=\"true\">" + 
				"              <title>On the Receiving Node</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.4.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item2.4.4.1.0\" identifierref=\"R_9471078e-0e8f-4e91-878f-517aade66636\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.4.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item2.4.4.1.1.1.1\" identifierref=\"R_9bc27055-10d9-43f7-95c1-73f1c2310e3e\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"9bc27055-10d9-43f7-95c1-73f1c2310e3e\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-9bc27055-10d9-43f7-95c1-73f1c2310e3e\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.4.4.1.1.2.1\" identifierref=\"R_f8f8304e-396b-4143-aa54-da558de9c873\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"f8f8304e-396b-4143-aa54-da558de9c873\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-f8f8304e-396b-4143-aa54-da558de9c873\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem2.4\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem2.4\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem2.4\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item2.5\" isvisible=\"true\">" + 
				"          <title>The Network Layer</title>" + 
				"          <item identifier=\"Item2.5.1\" identifierref=\"R_aea83ec5-4fe0-441f-893a-a1399adf7200\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.5\" identifierref=\"R_7efdbeb9-f5b6-4ed0-b82f-4fda5a38c2e4\" isvisible=\"true\">" + 
				"            <title>The Network Layer Flash Animation</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.6\" identifierref=\"R_6b955fcc-3fcb-4ae7-8ace-2dd639449943\" isvisible=\"true\">" + 
				"            <title>Packets across a Network</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.7\" identifierref=\"R_fdb0229a-76d3-4389-aeb3-3441a016585d\" isvisible=\"true\">" + 
				"            <title>Network Layer vs. Data Link Layer Addresses</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.8\" identifierref=\"R_24131eab-084a-40bf-98a6-b287959445db\" isvisible=\"true\">" + 
				"            <title>Packet Routing</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.9\" identifierref=\"R_794c0cb9-a359-4556-afcf-001310d483a4\" isvisible=\"true\">" + 
				"            <title>Network Layer Services</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.10\" identifierref=\"R_f02ab100-1e64-4e8b-b794-020bf7df8d81\" isvisible=\"true\">" + 
				"            <title>Network Layer Protocols</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.5.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item2.5.4.1.0\" identifierref=\"R_7835e73e-d12f-457d-a581-b5d093a26c19\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.5.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item2.5.4.1.1.1.1\" identifierref=\"R_35cb9c36-1dc7-4921-8b21-e2ecd7627b9b\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"35cb9c36-1dc7-4921-8b21-e2ecd7627b9b\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-35cb9c36-1dc7-4921-8b21-e2ecd7627b9b\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.5.4.1.1.2.1\" identifierref=\"R_9b532894-b822-4033-8a56-aa9cf4cdf033\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"9b532894-b822-4033-8a56-aa9cf4cdf033\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-9b532894-b822-4033-8a56-aa9cf4cdf033\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.5.4.1.1.3.1\" identifierref=\"R_4b654b31-a1e7-44b6-8c55-c41697f8a131\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"4b654b31-a1e7-44b6-8c55-c41697f8a131\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-4b654b31-a1e7-44b6-8c55-c41697f8a131\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem2.5\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem2.5\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem2.5\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item2.6\" isvisible=\"true\">" + 
				"          <title>The Transport Layer</title>" + 
				"          <item identifier=\"Item2.6.1\" identifierref=\"R_9945d87d-ac29-4c17-993d-0c833a3b35da\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.6.5\" identifierref=\"R_bada5092-5e4b-464b-9b52-bd7a7f727a30\" isvisible=\"true\">" + 
				"            <title>The Transport Layer Flash Animation</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.6.6\" identifierref=\"R_9d93cd08-516f-414c-97db-9c8a089df85a\" isvisible=\"true\">" + 
				"            <title>Transport Layer Services</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.6.7\" identifierref=\"R_bedb8edb-4c56-4f6c-b066-c4ad73a8de18\" isvisible=\"true\">" + 
				"            <title>Transport Layer Protocols</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.6.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item2.6.4.1.0\" identifierref=\"R_e9b2d400-bc78-47df-9708-c1e63ee27456\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.6.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item2.6.4.1.1.1.1\" identifierref=\"R_daa569e0-dc5b-48e3-a0c5-724d98afe891\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"daa569e0-dc5b-48e3-a0c5-724d98afe891\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-daa569e0-dc5b-48e3-a0c5-724d98afe891\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.6.4.1.1.2.1\" identifierref=\"R_5ffcc908-22fa-4115-b7aa-9c534d61c3d3\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"5ffcc908-22fa-4115-b7aa-9c534d61c3d3\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-5ffcc908-22fa-4115-b7aa-9c534d61c3d3\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.6.4.1.1.3.1\" identifierref=\"R_cb5ebfc0-4305-4bad-b78f-06d681ace7e8\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"cb5ebfc0-4305-4bad-b78f-06d681ace7e8\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-cb5ebfc0-4305-4bad-b78f-06d681ace7e8\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem2.6\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem2.6\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem2.6\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item2.7\" isvisible=\"true\">" + 
				"          <title>The Presentation Layer</title>" + 
				"          <item identifier=\"Item2.7.1\" identifierref=\"R_2802a5a6-1382-418f-8a65-e119d1a680f3\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem2.7.5\" isvisible=\"true\">" + 
				"            <title>Computer Number Systems</title>" + 
				"            <item identifier=\"Item2.7.5\" identifierref=\"R_7ddbb54d-2dfd-4003-b0fa-02d2b29c16a6\" isvisible=\"false\">" + 
				"              <title>Computer Number Systems</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.7.5.nested.3\" identifierref=\"R_4f6824fa-51c5-4dcc-a2ed-599bfcc773c2\" isvisible=\"true\">" + 
				"              <title>The Binary Numbering System</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.7.5.nested.4\" identifierref=\"R_cdec9796-f250-49b8-9ef7-b037792d8754\" isvisible=\"true\">" + 
				"              <title>The Hexadecimal Numbering System</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.7.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item2.7.4.1.0\" identifierref=\"R_9aaf4474-8b92-488e-8d1c-d1d3582a2237\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.7.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item2.7.4.1.1.1.1\" identifierref=\"R_eedad711-ffb0-4dd5-b8d5-466902763f36\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"eedad711-ffb0-4dd5-b8d5-466902763f36\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-eedad711-ffb0-4dd5-b8d5-466902763f36\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.7.4.1.1.2.1\" identifierref=\"R_87b553d9-51b0-483a-9728-889e53a8143b\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"87b553d9-51b0-483a-9728-889e53a8143b\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-87b553d9-51b0-483a-9728-889e53a8143b\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.7.4.1.1.3.1\" identifierref=\"R_1f59d124-f033-4236-aae1-bbb23bcd290e\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"1f59d124-f033-4236-aae1-bbb23bcd290e\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-1f59d124-f033-4236-aae1-bbb23bcd290e\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem2.7\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem2.7\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem2.7\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <item identifier=\"Item2.8\" isvisible=\"true\">" + 
				"          <title>The Application Layer</title>" + 
				"          <item identifier=\"Item2.8.1\" identifierref=\"R_080c35f0-c24c-4b4a-970e-9a5f81735ea9\" isvisible=\"false\">" + 
				"            <title>Introduction</title>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.8.5\" identifierref=\"R_7524389d-db53-41b9-a9b3-cb82b23d2267\" isvisible=\"true\">" + 
				"            <title>The Application Layer Flash Animation</title>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"FolderOfItem2.8.6\" isvisible=\"true\">" + 
				"            <title>Common Application Layer Programs</title>" + 
				"            <item identifier=\"Item2.8.6\" identifierref=\"R_9970897c-93a8-49cf-a2af-812745571585\" isvisible=\"false\">" + 
				"              <title>Common Application Layer Programs</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.8.6.nested.3\" identifierref=\"R_aa5f696b-3702-45ae-8706-d6d635e55e34\" isvisible=\"true\">" + 
				"              <title>E-Mail</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.8.6.nested.4\" identifierref=\"R_a129da69-c319-4abd-9546-55d3c15513b6\" isvisible=\"true\">" + 
				"              <title>USENET Newsgroups</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.8.6.nested.5\" identifierref=\"R_326ad9ef-bfd8-4d21-ba12-8e4d8bf29145\" isvisible=\"true\">" + 
				"              <title>File Transfer and Access</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.8.6.nested.6\" identifierref=\"R_cf3ba086-fbad-4ed4-86eb-b671459d09d3\" isvisible=\"true\">" + 
				"              <title>Virtual Terminals</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.8.6.nested.7\" identifierref=\"R_5387550b-aa32-4a0b-8d3d-0d1b4975b8c1\" isvisible=\"true\">" + 
				"              <title>Web Browsers and Servers</title>" + 
				"              <imsss:sequencing/>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <item identifier=\"Item2.8.4.1\" isvisible=\"true\">" + 
				"            <title>CheckPoint Questions</title>" + 
				"            <item identifier=\"Item2.8.4.1.0\" identifierref=\"R_be66c67d-9f1b-4d0d-805b-5d0f697352cc\" isvisible=\"false\">" + 
				"              <title>CheckPoint Intro</title>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"0\"/>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective satisfiedByMeasure=\"false\" objectiveID=\"QuestionsIntroBlock\"/>" + 
				"                </imsss:objectives>" + 
				"                <imsss:deliveryControls tracked=\"false\" completionSetByContent=\"false\" objectiveSetByContent=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <item identifier=\"Item2.8.4.1.1\" isvisible=\"false\">" + 
				"              <title>CheckPoint Questions</title>" + 
				"              <item identifier=\"Item2.8.4.1.1.1.1\" identifierref=\"R_697fac68-a9f9-4916-9827-fc426336a648\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"697fac68-a9f9-4916-9827-fc426336a648\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-697fac68-a9f9-4916-9827-fc426336a648\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.8.4.1.1.2.1\" identifierref=\"R_4e83d1e6-ff77-4ba9-977c-2b2a4833649f\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"4e83d1e6-ff77-4ba9-977c-2b2a4833649f\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-4e83d1e6-ff77-4ba9-977c-2b2a4833649f\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"              </item>" + 
				"              <item identifier=\"Item2.8.4.1.1.3.1\" identifierref=\"R_2fed03fd-0885-465a-b125-7971c70e6a09\" isvisible=\"false\">" + 
				"                <title>CheckPoint Question</title>" + 
				"                <imsss:sequencing>" + 
				"                  <imsss:rollupRules objectiveMeasureWeight=\"1.0\"/>" + 
				"                  <imsss:objectives>" + 
				"                    <imsss:primaryObjective objectiveID=\"primaryObjective\" satisfiedByMeasure=\"true\">" + 
				"                      <imsss:minNormalizedMeasure>1.0</imsss:minNormalizedMeasure>" + 
				"                      <imsss:mapInfo targetObjectiveID=\"2fed03fd-0885-465a-b125-7971c70e6a09\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                    </imsss:primaryObjective>" + 
				"                    <imsss:objective objectiveID=\"q-2fed03fd-0885-465a-b125-7971c70e6a09\"/>" + 
				"                  </imsss:objectives>" + 
				"                </imsss:sequencing>" + 
				"                <adlnav:presentation>" + 
				"                  <adlnav:navigationInterface>" + 
				"                    <adlnav:hideLMSUI>continue</adlnav:hideLMSUI>" + 
				"                  </adlnav:navigationInterface>" + 
				"                </adlnav:presentation>" + 
				"              </item>" + 
				"              <imsss:sequencing>" + 
				"                <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"false\"/>" + 
				"                <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"                <imsss:objectives>" + 
				"                  <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"true\">" + 
				"                    <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                    <imsss:mapInfo targetObjectiveID=\"objItem2.8\" readSatisfiedStatus=\"false\" readNormalizedMeasure=\"false\" writeSatisfiedStatus=\"true\" writeNormalizedMeasure=\"true\"/>" + 
				"                  </imsss:primaryObjective>" + 
				"                </imsss:objectives>" + 
				"                <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"              </imsss:sequencing>" + 
				"            </item>" + 
				"            <imsss:sequencing>" + 
				"              <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"false\" forwardOnly=\"true\"/>" + 
				"              <imsss:rollupRules objectiveMeasureWeight=\"1\"/>" + 
				"              <imsss:objectives>" + 
				"                <imsss:primaryObjective objectiveID=\"QuizObjective\" satisfiedByMeasure=\"false\">" + 
				"                  <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"                  <imsss:mapInfo targetObjectiveID=\"objItem2.8\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"                </imsss:primaryObjective>" + 
				"              </imsss:objectives>" + 
				"              <adlseq:rollupConsiderations measureSatisfactionIfActive=\"false\"/>" + 
				"            </imsss:sequencing>" + 
				"          </item>" + 
				"          <imsss:sequencing>" + 
				"            <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"            <imsss:objectives>" + 
				"              <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\">" + 
				"                <imsss:mapInfo targetObjectiveID=\"objItem2.8\" readSatisfiedStatus=\"true\" readNormalizedMeasure=\"true\" writeSatisfiedStatus=\"false\" writeNormalizedMeasure=\"false\"/>" + 
				"              </imsss:primaryObjective>" + 
				"            </imsss:objectives>" + 
				"          </imsss:sequencing>" + 
				"        </item>" + 
				"        <imsss:sequencing>" + 
				"          <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"          <imsss:objectives>" + 
				"            <imsss:primaryObjective objectiveID=\"LessonObjective\" satisfiedByMeasure=\"false\"/>" + 
				"          </imsss:objectives>" + 
				"        </imsss:sequencing>" + 
				"      </item>" + 
				"      <imsss:sequencing>" + 
				"        <imsss:controlMode choiceExit=\"true\" useCurrentAttemptObjectiveInfo=\"false\" useCurrentAttemptProgressInfo=\"false\" flow=\"true\" choice=\"true\" forwardOnly=\"false\"/>" + 
				"        <imsss:auxiliaryResources>" + 
				"          <imsss:auxiliaryResource auxiliaryResourceID=\"39e9a1cc-b46b-48c0-a50f-eb368e1fb516.html\" purpose=\"Glossary\"/>" + 
				"        </imsss:auxiliaryResources>" + 
				"        <imsss:objectives>" + 
				"          <imsss:primaryObjective objectiveID=\"SSPObjective\" satisfiedByMeasure=\"true\">" + 
				"            <imsss:minNormalizedMeasure>1</imsss:minNormalizedMeasure>" + 
				"          </imsss:primaryObjective>" + 
				"        </imsss:objectives>" + 
				"      </imsss:sequencing>" + 
				"    </organization>" + 
				"  </organizations>" + 
				"  <resources>" + 
				"    <resource identifier=\"R_7765c3d7-f00a-43aa-acdd-e26af8dac6f8\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7765c3d7-f00a-43aa-acdd-e26af8dac6f8/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7765c3d7-f00a-43aa-acdd-e26af8dac6f8/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e6c0c28e-ba08-4ed5-9a9c-4583a4b3b4ce\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e6c0c28e-ba08-4ed5-9a9c-4583a4b3b4ce/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e6c0c28e-ba08-4ed5-9a9c-4583a4b3b4ce/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_b0889873-4064-4815-936f-c4608558a05b\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/b0889873-4064-4815-936f-c4608558a05b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/b0889873-4064-4815-936f-c4608558a05b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6f37a4de-edc4-4ff9-baf3-c0f80d4b2b34\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6f37a4de-edc4-4ff9-baf3-c0f80d4b2b34/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6f37a4de-edc4-4ff9-baf3-c0f80d4b2b34/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_44561233-eaae-4366-bcaa-b3e7f5ac3c9a\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/44561233-eaae-4366-bcaa-b3e7f5ac3c9a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/44561233-eaae-4366-bcaa-b3e7f5ac3c9a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9d113cf6-5c06-4650-8a1f-8c61cfb0d507\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9d113cf6-5c06-4650-8a1f-8c61cfb0d507/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9d113cf6-5c06-4650-8a1f-8c61cfb0d507/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_032a3b87-d03c-41f1-9585-015e142df2a0\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/032a3b87-d03c-41f1-9585-015e142df2a0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/032a3b87-d03c-41f1-9585-015e142df2a0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_aea83ec5-4fe0-441f-893a-a1399adf7200\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/aea83ec5-4fe0-441f-893a-a1399adf7200/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/aea83ec5-4fe0-441f-893a-a1399adf7200/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9945d87d-ac29-4c17-993d-0c833a3b35da\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9945d87d-ac29-4c17-993d-0c833a3b35da/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9945d87d-ac29-4c17-993d-0c833a3b35da/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2802a5a6-1382-418f-8a65-e119d1a680f3\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2802a5a6-1382-418f-8a65-e119d1a680f3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2802a5a6-1382-418f-8a65-e119d1a680f3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_080c35f0-c24c-4b4a-970e-9a5f81735ea9\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/080c35f0-c24c-4b4a-970e-9a5f81735ea9/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/080c35f0-c24c-4b4a-970e-9a5f81735ea9/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_879c1f35-b383-43d5-ad8d-fd3d5dc83344\" adlcp:scormType=\"sco\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/879c1f35-b383-43d5-ad8d-fd3d5dc83344/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/879c1f35-b383-43d5-ad8d-fd3d5dc83344/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2e853d98-f044-4a7b-b0dc-adf1a7716057\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"5035022f-a425-4011-b2ac-c3105c205a58\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2e853d98-f044-4a7b-b0dc-adf1a7716057/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2e853d98-f044-4a7b-b0dc-adf1a7716057/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e1f85d60-2f94-4e58-a543-430287137295\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"5035022f-a425-4011-b2ac-c3105c205a58\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e1f85d60-2f94-4e58-a543-430287137295/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e1f85d60-2f94-4e58-a543-430287137295/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_58e5a9d9-b19c-43fb-88bb-8aaa8c6f4dd7\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"5035022f-a425-4011-b2ac-c3105c205a58\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/58e5a9d9-b19c-43fb-88bb-8aaa8c6f4dd7/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/58e5a9d9-b19c-43fb-88bb-8aaa8c6f4dd7/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_34853279-9347-47d8-bb1e-73374b7ddd78\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"5035022f-a425-4011-b2ac-c3105c205a58\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/34853279-9347-47d8-bb1e-73374b7ddd78/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/34853279-9347-47d8-bb1e-73374b7ddd78/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9b66ca30-07aa-4bee-beb8-bfce5112e64a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"5035022f-a425-4011-b2ac-c3105c205a58\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9b66ca30-07aa-4bee-beb8-bfce5112e64a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9b66ca30-07aa-4bee-beb8-bfce5112e64a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_95111c31-a919-4536-ab8c-b3da2f00c616\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"5035022f-a425-4011-b2ac-c3105c205a58\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/95111c31-a919-4536-ab8c-b3da2f00c616/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/95111c31-a919-4536-ab8c-b3da2f00c616/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_da4c4e75-c36c-4e07-8d75-22d5809e1420\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/da4c4e75-c36c-4e07-8d75-22d5809e1420/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/da4c4e75-c36c-4e07-8d75-22d5809e1420/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_00b7d9e5-d91a-40b7-80b0-58778a0fbb05\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/00b7d9e5-d91a-40b7-80b0-58778a0fbb05/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/00b7d9e5-d91a-40b7-80b0-58778a0fbb05/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_016caad2-0736-455c-8217-14a98fb3b7ff\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/016caad2-0736-455c-8217-14a98fb3b7ff/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/016caad2-0736-455c-8217-14a98fb3b7ff/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_94e3b028-76d0-4b85-895b-98e29fd424bd\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/94e3b028-76d0-4b85-895b-98e29fd424bd/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/94e3b028-76d0-4b85-895b-98e29fd424bd/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_eef818ba-8fb8-4321-8045-763fe050a206\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/eef818ba-8fb8-4321-8045-763fe050a206/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/eef818ba-8fb8-4321-8045-763fe050a206/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_00f7db79-d107-490e-857b-93c308289869\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/00f7db79-d107-490e-857b-93c308289869/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/00f7db79-d107-490e-857b-93c308289869/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_72b1bfa2-79a7-47a4-b809-87d2d8e2dffc\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/72b1bfa2-79a7-47a4-b809-87d2d8e2dffc/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/72b1bfa2-79a7-47a4-b809-87d2d8e2dffc/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_08b96578-264a-49c9-b533-d4c1586299c2\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/08b96578-264a-49c9-b533-d4c1586299c2/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/08b96578-264a-49c9-b533-d4c1586299c2/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_ec4d1d83-0117-4ca2-8e6f-44619d80bbbe\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ec4d1d83-0117-4ca2-8e6f-44619d80bbbe/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ec4d1d83-0117-4ca2-8e6f-44619d80bbbe/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_05a2c400-d6fa-4a09-b756-d0f6267ffb25\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/05a2c400-d6fa-4a09-b756-d0f6267ffb25/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/05a2c400-d6fa-4a09-b756-d0f6267ffb25/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_14dad425-87cf-41b1-ac58-8705d889ce00\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/14dad425-87cf-41b1-ac58-8705d889ce00/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/14dad425-87cf-41b1-ac58-8705d889ce00/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c12006fb-ece5-4e29-bc70-7c9d6db59f77\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c12006fb-ece5-4e29-bc70-7c9d6db59f77/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c12006fb-ece5-4e29-bc70-7c9d6db59f77/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4c26f12b-8cb8-44de-8fc1-c61b644489d7\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4c26f12b-8cb8-44de-8fc1-c61b644489d7/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4c26f12b-8cb8-44de-8fc1-c61b644489d7/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e1a788b3-b8bc-4d36-a154-e7c01e35785b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e1a788b3-b8bc-4d36-a154-e7c01e35785b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e1a788b3-b8bc-4d36-a154-e7c01e35785b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_3f3aeedf-02c2-4216-90fd-5a4c3cbc56e9\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3f3aeedf-02c2-4216-90fd-5a4c3cbc56e9/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3f3aeedf-02c2-4216-90fd-5a4c3cbc56e9/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8a24ad31-8111-4445-9589-975a71fcd2f7\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8a24ad31-8111-4445-9589-975a71fcd2f7/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8a24ad31-8111-4445-9589-975a71fcd2f7/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f65a1ee4-17fc-452e-a95d-102025e78676\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f65a1ee4-17fc-452e-a95d-102025e78676/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f65a1ee4-17fc-452e-a95d-102025e78676/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9c1c8319-caf6-4a5b-bf45-71f8ca6672ba\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9c1c8319-caf6-4a5b-bf45-71f8ca6672ba/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9c1c8319-caf6-4a5b-bf45-71f8ca6672ba/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_75e78b2d-1590-48e0-a880-71c6a0cb1161\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/75e78b2d-1590-48e0-a880-71c6a0cb1161/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/75e78b2d-1590-48e0-a880-71c6a0cb1161/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_85add2fc-2ea1-41e1-aa4e-e36e2f6311f8\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/85add2fc-2ea1-41e1-aa4e-e36e2f6311f8/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/85add2fc-2ea1-41e1-aa4e-e36e2f6311f8/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_ce646d3a-cf03-42c3-aa8a-2d4901f818c2\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ce646d3a-cf03-42c3-aa8a-2d4901f818c2/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ce646d3a-cf03-42c3-aa8a-2d4901f818c2/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_05824a99-3a7d-49c1-a218-5c176e0a23be\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/05824a99-3a7d-49c1-a218-5c176e0a23be/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/05824a99-3a7d-49c1-a218-5c176e0a23be/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_5fad414e-75dc-42b9-b401-780fe087d4d1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/5fad414e-75dc-42b9-b401-780fe087d4d1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/5fad414e-75dc-42b9-b401-780fe087d4d1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_fe2f9cd4-71e5-4c27-a529-62a12ae88f92\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fe2f9cd4-71e5-4c27-a529-62a12ae88f92/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fe2f9cd4-71e5-4c27-a529-62a12ae88f92/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2aeabf74-9ac2-4150-bb8d-9f08c44c7936\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2aeabf74-9ac2-4150-bb8d-9f08c44c7936/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2aeabf74-9ac2-4150-bb8d-9f08c44c7936/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_b9e040bc-0902-4c7a-ad47-160e859641df\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/b9e040bc-0902-4c7a-ad47-160e859641df/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/b9e040bc-0902-4c7a-ad47-160e859641df/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d1a3382c-991d-4660-b967-8a44a7313f0c\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d1a3382c-991d-4660-b967-8a44a7313f0c/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d1a3382c-991d-4660-b967-8a44a7313f0c/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c3bbc631-d5e6-4ec0-9119-b809cb4fde4d\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c3bbc631-d5e6-4ec0-9119-b809cb4fde4d/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c3bbc631-d5e6-4ec0-9119-b809cb4fde4d/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f97bab73-ea0c-482c-bfac-da29a918d317\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f97bab73-ea0c-482c-bfac-da29a918d317/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f97bab73-ea0c-482c-bfac-da29a918d317/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_179a038a-9a82-46a6-9111-8d85f3e69c66\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/179a038a-9a82-46a6-9111-8d85f3e69c66/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/179a038a-9a82-46a6-9111-8d85f3e69c66/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8d452f7e-268b-41b6-a940-a36692e23ff2\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8d452f7e-268b-41b6-a940-a36692e23ff2/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8d452f7e-268b-41b6-a940-a36692e23ff2/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e6f97bec-40e6-4d5f-a720-f7494701e463\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e6f97bec-40e6-4d5f-a720-f7494701e463/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e6f97bec-40e6-4d5f-a720-f7494701e463/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d03067bf-28d9-4390-9b15-90a1269f11e0\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d03067bf-28d9-4390-9b15-90a1269f11e0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d03067bf-28d9-4390-9b15-90a1269f11e0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cbca2530-3bd6-4450-8060-f32232a9ca46\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cbca2530-3bd6-4450-8060-f32232a9ca46/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cbca2530-3bd6-4450-8060-f32232a9ca46/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4027cd4d-5633-4f9b-80f1-3f647200ee98\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4027cd4d-5633-4f9b-80f1-3f647200ee98/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4027cd4d-5633-4f9b-80f1-3f647200ee98/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_a143ca71-4c90-4e2a-95b4-2feccad1e1cf\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a143ca71-4c90-4e2a-95b4-2feccad1e1cf/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a143ca71-4c90-4e2a-95b4-2feccad1e1cf/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d20d3b7d-297c-4345-b5a2-adcb41893e23\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d20d3b7d-297c-4345-b5a2-adcb41893e23/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d20d3b7d-297c-4345-b5a2-adcb41893e23/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_dd9e8ab2-f56d-4c20-afc7-3622d930748f\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/dd9e8ab2-f56d-4c20-afc7-3622d930748f/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/dd9e8ab2-f56d-4c20-afc7-3622d930748f/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0074ad98-d553-47ef-a877-1639550aa757\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0074ad98-d553-47ef-a877-1639550aa757/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0074ad98-d553-47ef-a877-1639550aa757/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c0959643-0a78-4b4b-9309-9f4bba4dfb1f\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c0959643-0a78-4b4b-9309-9f4bba4dfb1f/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c0959643-0a78-4b4b-9309-9f4bba4dfb1f/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_76e07abb-a5df-49f0-9a72-ee02c4e5fad3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/76e07abb-a5df-49f0-9a72-ee02c4e5fad3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/76e07abb-a5df-49f0-9a72-ee02c4e5fad3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0e9abdd6-b8be-4c96-b7b9-35006df54226\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0e9abdd6-b8be-4c96-b7b9-35006df54226/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0e9abdd6-b8be-4c96-b7b9-35006df54226/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_54e4f907-d30f-448f-8167-251a5cea3cf0\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/54e4f907-d30f-448f-8167-251a5cea3cf0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/54e4f907-d30f-448f-8167-251a5cea3cf0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8f24a0f1-b199-4bc7-9738-037b72bf011d\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8f24a0f1-b199-4bc7-9738-037b72bf011d/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8f24a0f1-b199-4bc7-9738-037b72bf011d/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c7d63705-159c-4408-90d4-4691d9f34d77\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c7d63705-159c-4408-90d4-4691d9f34d77/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c7d63705-159c-4408-90d4-4691d9f34d77/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c936b482-b6ff-4172-b66d-2bfdc1d3fce8\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c936b482-b6ff-4172-b66d-2bfdc1d3fce8/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c936b482-b6ff-4172-b66d-2bfdc1d3fce8/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0e5eb8ee-ba4d-452d-9684-9b792e6576f1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0e5eb8ee-ba4d-452d-9684-9b792e6576f1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0e5eb8ee-ba4d-452d-9684-9b792e6576f1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2d72efa1-04b9-4020-9f5b-bfb710c702e5\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2d72efa1-04b9-4020-9f5b-bfb710c702e5/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2d72efa1-04b9-4020-9f5b-bfb710c702e5/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_332adaa5-bb93-4a5c-aad6-303bf450d152\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/332adaa5-bb93-4a5c-aad6-303bf450d152/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/332adaa5-bb93-4a5c-aad6-303bf450d152/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_09b796c6-3b9c-4942-b5b4-9cd904a0e181\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/09b796c6-3b9c-4942-b5b4-9cd904a0e181/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/09b796c6-3b9c-4942-b5b4-9cd904a0e181/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8edfe92f-8e0f-49a0-9ffa-124f969167f0\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8edfe92f-8e0f-49a0-9ffa-124f969167f0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8edfe92f-8e0f-49a0-9ffa-124f969167f0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8e4ecdf2-a194-4532-bd0d-e64e4a8af58e\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8e4ecdf2-a194-4532-bd0d-e64e4a8af58e/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8e4ecdf2-a194-4532-bd0d-e64e4a8af58e/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_31c3ebae-0fad-48b3-8711-a380e22c555f\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/31c3ebae-0fad-48b3-8711-a380e22c555f/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/31c3ebae-0fad-48b3-8711-a380e22c555f/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_bec6c91c-351c-4051-8bde-906588af7541\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bec6c91c-351c-4051-8bde-906588af7541/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bec6c91c-351c-4051-8bde-906588af7541/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8a15acfd-35d5-4e5e-bd20-f684a46d10fb\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8a15acfd-35d5-4e5e-bd20-f684a46d10fb/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8a15acfd-35d5-4e5e-bd20-f684a46d10fb/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c0da393a-3d06-4e01-be98-243c9e962e78\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c0da393a-3d06-4e01-be98-243c9e962e78/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c0da393a-3d06-4e01-be98-243c9e962e78/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d241bfbe-0370-4b61-80e2-c3fb71a14c5d\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d241bfbe-0370-4b61-80e2-c3fb71a14c5d/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d241bfbe-0370-4b61-80e2-c3fb71a14c5d/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_602da7b9-0b8e-4549-891d-baebdeba3418\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/602da7b9-0b8e-4549-891d-baebdeba3418/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/602da7b9-0b8e-4549-891d-baebdeba3418/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_34b573b8-72b1-493b-bd78-79b59ec2b46a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/34b573b8-72b1-493b-bd78-79b59ec2b46a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/34b573b8-72b1-493b-bd78-79b59ec2b46a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d9fb7e74-294f-4d42-ba8c-fa230349e911\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d9fb7e74-294f-4d42-ba8c-fa230349e911/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d9fb7e74-294f-4d42-ba8c-fa230349e911/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9a50fe52-d2a2-4036-8798-65c5c1de3716\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9a50fe52-d2a2-4036-8798-65c5c1de3716/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9a50fe52-d2a2-4036-8798-65c5c1de3716/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_788aad62-8f9e-40c2-9491-cae759fa1bcc\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/788aad62-8f9e-40c2-9491-cae759fa1bcc/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/788aad62-8f9e-40c2-9491-cae759fa1bcc/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_bcc71f98-957b-48b6-acc1-055d63771374\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bcc71f98-957b-48b6-acc1-055d63771374/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bcc71f98-957b-48b6-acc1-055d63771374/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9ee7e61f-887b-48c4-bf26-104c40e40e9c\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9ee7e61f-887b-48c4-bf26-104c40e40e9c/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9ee7e61f-887b-48c4-bf26-104c40e40e9c/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_05d05bb1-5b2d-466e-a0ba-dd44d6a03086\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/05d05bb1-5b2d-466e-a0ba-dd44d6a03086/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/05d05bb1-5b2d-466e-a0ba-dd44d6a03086/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_786902f4-f059-4a57-8cec-26631b12728d\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/786902f4-f059-4a57-8cec-26631b12728d/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/786902f4-f059-4a57-8cec-26631b12728d/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cdcca0f2-e00b-4261-86c2-6f83e433a4f5\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cdcca0f2-e00b-4261-86c2-6f83e433a4f5/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cdcca0f2-e00b-4261-86c2-6f83e433a4f5/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_dfe760b1-60f5-438f-94ab-63cd56b5cae6\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/dfe760b1-60f5-438f-94ab-63cd56b5cae6/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/dfe760b1-60f5-438f-94ab-63cd56b5cae6/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4efd4b44-767f-4d62-8dc2-f59ec71c9c94\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4efd4b44-767f-4d62-8dc2-f59ec71c9c94/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4efd4b44-767f-4d62-8dc2-f59ec71c9c94/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d2e25ef2-a6e4-4dda-a64d-994bec8bee84\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d2e25ef2-a6e4-4dda-a64d-994bec8bee84/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d2e25ef2-a6e4-4dda-a64d-994bec8bee84/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_da478a27-6b86-4751-8bbf-9d47062bb5c3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/da478a27-6b86-4751-8bbf-9d47062bb5c3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/da478a27-6b86-4751-8bbf-9d47062bb5c3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_26dd3a5b-363a-47c9-bf1c-17964989913b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/26dd3a5b-363a-47c9-bf1c-17964989913b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/26dd3a5b-363a-47c9-bf1c-17964989913b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f863656a-dcb4-45a6-85fd-7c84f35edddd\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f863656a-dcb4-45a6-85fd-7c84f35edddd/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f863656a-dcb4-45a6-85fd-7c84f35edddd/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e6ff9fb5-2106-42c0-af58-a54a940646b5\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e6ff9fb5-2106-42c0-af58-a54a940646b5/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e6ff9fb5-2106-42c0-af58-a54a940646b5/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_3abc6131-35d9-466b-bfa6-99a1015f647a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3abc6131-35d9-466b-bfa6-99a1015f647a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3abc6131-35d9-466b-bfa6-99a1015f647a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d83a3770-cdee-4a78-8fb1-fab0570dcfd2\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d83a3770-cdee-4a78-8fb1-fab0570dcfd2/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d83a3770-cdee-4a78-8fb1-fab0570dcfd2/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2909b9a4-4794-4cca-9b01-f47d5acc7057\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2909b9a4-4794-4cca-9b01-f47d5acc7057/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2909b9a4-4794-4cca-9b01-f47d5acc7057/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9a3bd168-3144-4727-bd1b-5181515f9b3f\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9a3bd168-3144-4727-bd1b-5181515f9b3f/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9a3bd168-3144-4727-bd1b-5181515f9b3f/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_508db213-a533-4db6-85c1-b40af6913dbc\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/508db213-a533-4db6-85c1-b40af6913dbc/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/508db213-a533-4db6-85c1-b40af6913dbc/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f9983f6e-e7c0-4b97-a820-6a19a7a6c800\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f9983f6e-e7c0-4b97-a820-6a19a7a6c800/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f9983f6e-e7c0-4b97-a820-6a19a7a6c800/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_217182fc-faa7-4cb4-afca-03f891e6d385\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/217182fc-faa7-4cb4-afca-03f891e6d385/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/217182fc-faa7-4cb4-afca-03f891e6d385/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9400f01c-1f06-4014-86bc-7813288b7292\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9400f01c-1f06-4014-86bc-7813288b7292/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9400f01c-1f06-4014-86bc-7813288b7292/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7ac5b7d6-beb6-440d-9e04-f18646704bb6\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7ac5b7d6-beb6-440d-9e04-f18646704bb6/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7ac5b7d6-beb6-440d-9e04-f18646704bb6/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_3551e310-8215-4de2-9066-73c0662a50b6\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3551e310-8215-4de2-9066-73c0662a50b6/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3551e310-8215-4de2-9066-73c0662a50b6/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_27775b37-1fbb-4a09-9032-bf06aca5b1b4\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/27775b37-1fbb-4a09-9032-bf06aca5b1b4/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/27775b37-1fbb-4a09-9032-bf06aca5b1b4/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_48e90929-fb74-4365-a214-f3f8b49dcd58\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/48e90929-fb74-4365-a214-f3f8b49dcd58/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/48e90929-fb74-4365-a214-f3f8b49dcd58/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_ced31b64-92ac-4447-8966-2101a19e09cc\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ced31b64-92ac-4447-8966-2101a19e09cc/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ced31b64-92ac-4447-8966-2101a19e09cc/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_3a2e795c-aa99-4836-8420-841db5b85763\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3a2e795c-aa99-4836-8420-841db5b85763/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3a2e795c-aa99-4836-8420-841db5b85763/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_680f0782-5a53-4c28-a106-554a704b8775\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/680f0782-5a53-4c28-a106-554a704b8775/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/680f0782-5a53-4c28-a106-554a704b8775/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7fa51c29-53a7-4870-9619-390a02bb34a1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7fa51c29-53a7-4870-9619-390a02bb34a1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7fa51c29-53a7-4870-9619-390a02bb34a1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4b54270b-881a-41e8-b0b3-dbadc956ce7b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4b54270b-881a-41e8-b0b3-dbadc956ce7b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4b54270b-881a-41e8-b0b3-dbadc956ce7b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_48e1b47f-431a-4bed-93a1-ef4cfe79d4fb\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/48e1b47f-431a-4bed-93a1-ef4cfe79d4fb/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/48e1b47f-431a-4bed-93a1-ef4cfe79d4fb/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_dff479fb-b4ed-49ac-9d5b-69f412783f88\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/dff479fb-b4ed-49ac-9d5b-69f412783f88/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/dff479fb-b4ed-49ac-9d5b-69f412783f88/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4e1a7750-c64e-4cb1-85bc-c6af9390337d\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4e1a7750-c64e-4cb1-85bc-c6af9390337d/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4e1a7750-c64e-4cb1-85bc-c6af9390337d/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_3251a38a-1ea9-4d8a-bcd3-dca2bf4a7696\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3251a38a-1ea9-4d8a-bcd3-dca2bf4a7696/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/3251a38a-1ea9-4d8a-bcd3-dca2bf4a7696/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_a2b1d1d1-fc6b-4c72-98b0-2aec37669b70\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a2b1d1d1-fc6b-4c72-98b0-2aec37669b70/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a2b1d1d1-fc6b-4c72-98b0-2aec37669b70/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_fb5ede25-85e4-4b0e-af15-321f01cb24a1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fb5ede25-85e4-4b0e-af15-321f01cb24a1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fb5ede25-85e4-4b0e-af15-321f01cb24a1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2769293d-1a9f-4759-a0a5-458272a01959\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2769293d-1a9f-4759-a0a5-458272a01959/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2769293d-1a9f-4759-a0a5-458272a01959/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7f646e98-eba8-4e89-842a-d20e3280f1e1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7f646e98-eba8-4e89-842a-d20e3280f1e1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7f646e98-eba8-4e89-842a-d20e3280f1e1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6b320762-3a38-4e4c-a4eb-3c7cdf2efe20\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6b320762-3a38-4e4c-a4eb-3c7cdf2efe20/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6b320762-3a38-4e4c-a4eb-3c7cdf2efe20/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2e772219-c92f-4c02-a741-443e780c2ff9\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2e772219-c92f-4c02-a741-443e780c2ff9/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2e772219-c92f-4c02-a741-443e780c2ff9/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4b45099d-26cb-4dba-beb2-3c5c862a383b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4b45099d-26cb-4dba-beb2-3c5c862a383b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4b45099d-26cb-4dba-beb2-3c5c862a383b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6d15b04a-11a2-4725-98d6-2f87ce0987f8\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6d15b04a-11a2-4725-98d6-2f87ce0987f8/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6d15b04a-11a2-4725-98d6-2f87ce0987f8/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_b2f2785e-64e0-4668-ae43-91f08c4c1a4b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/b2f2785e-64e0-4668-ae43-91f08c4c1a4b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/b2f2785e-64e0-4668-ae43-91f08c4c1a4b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8fd210c1-c98a-4f14-9798-0983bb2e303c\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8fd210c1-c98a-4f14-9798-0983bb2e303c/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8fd210c1-c98a-4f14-9798-0983bb2e303c/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_a1c17b3d-1828-4f3f-a2e6-1d007fcaa99e\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a1c17b3d-1828-4f3f-a2e6-1d007fcaa99e/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a1c17b3d-1828-4f3f-a2e6-1d007fcaa99e/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9bc27055-10d9-43f7-95c1-73f1c2310e3e\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9bc27055-10d9-43f7-95c1-73f1c2310e3e/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9bc27055-10d9-43f7-95c1-73f1c2310e3e/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f8f8304e-396b-4143-aa54-da558de9c873\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f8f8304e-396b-4143-aa54-da558de9c873/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f8f8304e-396b-4143-aa54-da558de9c873/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_95388ac0-533b-41d2-a3cb-b4ae7ad3202a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/95388ac0-533b-41d2-a3cb-b4ae7ad3202a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/95388ac0-533b-41d2-a3cb-b4ae7ad3202a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_aaa27b89-34d0-4ea4-96a7-451586c05713\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/aaa27b89-34d0-4ea4-96a7-451586c05713/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/aaa27b89-34d0-4ea4-96a7-451586c05713/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_bedf3e9d-6a90-4abd-8c26-36cdcfdc29c1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bedf3e9d-6a90-4abd-8c26-36cdcfdc29c1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bedf3e9d-6a90-4abd-8c26-36cdcfdc29c1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2aa2af79-0f59-4f5f-a808-3486bd166a52\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2aa2af79-0f59-4f5f-a808-3486bd166a52/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2aa2af79-0f59-4f5f-a808-3486bd166a52/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_59ebeb48-b491-4ce2-b581-8f2b1ac97d90\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/59ebeb48-b491-4ce2-b581-8f2b1ac97d90/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/59ebeb48-b491-4ce2-b581-8f2b1ac97d90/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_942e4ad4-f3ce-4eca-938b-2dda3553a695\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/942e4ad4-f3ce-4eca-938b-2dda3553a695/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/942e4ad4-f3ce-4eca-938b-2dda3553a695/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_af4a5174-937f-4722-9577-2349856fb4b3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/af4a5174-937f-4722-9577-2349856fb4b3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/af4a5174-937f-4722-9577-2349856fb4b3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_75b331e6-8c1b-4a2f-ba6e-805cfd876024\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/75b331e6-8c1b-4a2f-ba6e-805cfd876024/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/75b331e6-8c1b-4a2f-ba6e-805cfd876024/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_ffd7041a-6b72-49a4-80fa-911752c93886\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ffd7041a-6b72-49a4-80fa-911752c93886/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ffd7041a-6b72-49a4-80fa-911752c93886/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0b7805fd-c4c3-4998-b7ef-19155f51351a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0b7805fd-c4c3-4998-b7ef-19155f51351a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0b7805fd-c4c3-4998-b7ef-19155f51351a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_455c769c-f335-4b4a-91d4-6eb92fd03321\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/455c769c-f335-4b4a-91d4-6eb92fd03321/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/455c769c-f335-4b4a-91d4-6eb92fd03321/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_35cb9c36-1dc7-4921-8b21-e2ecd7627b9b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/35cb9c36-1dc7-4921-8b21-e2ecd7627b9b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/35cb9c36-1dc7-4921-8b21-e2ecd7627b9b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9b532894-b822-4033-8a56-aa9cf4cdf033\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9b532894-b822-4033-8a56-aa9cf4cdf033/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9b532894-b822-4033-8a56-aa9cf4cdf033/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4b654b31-a1e7-44b6-8c55-c41697f8a131\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4b654b31-a1e7-44b6-8c55-c41697f8a131/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4b654b31-a1e7-44b6-8c55-c41697f8a131/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4ce8b5a4-8d73-4fcb-b040-803537a4c136\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4ce8b5a4-8d73-4fcb-b040-803537a4c136/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4ce8b5a4-8d73-4fcb-b040-803537a4c136/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_402df43f-082b-4783-a6b8-2b403f651a9b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/402df43f-082b-4783-a6b8-2b403f651a9b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/402df43f-082b-4783-a6b8-2b403f651a9b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_03e544a5-0675-4a40-a542-3330bb7ea1f4\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/03e544a5-0675-4a40-a542-3330bb7ea1f4/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/03e544a5-0675-4a40-a542-3330bb7ea1f4/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0b7e26f0-d6e8-492b-bce9-bea2613a49e0\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0b7e26f0-d6e8-492b-bce9-bea2613a49e0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0b7e26f0-d6e8-492b-bce9-bea2613a49e0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9b055d5c-03bc-4ea6-a4af-f7e920d29815\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9b055d5c-03bc-4ea6-a4af-f7e920d29815/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9b055d5c-03bc-4ea6-a4af-f7e920d29815/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0dc0f7ca-da6f-448c-8181-1d4c11b28810\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0dc0f7ca-da6f-448c-8181-1d4c11b28810/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0dc0f7ca-da6f-448c-8181-1d4c11b28810/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_a144c45f-ca25-49a1-8009-5f113237c7c0\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a144c45f-ca25-49a1-8009-5f113237c7c0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a144c45f-ca25-49a1-8009-5f113237c7c0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e2791c8d-0fd8-45c3-a39a-db4e276b0ef3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e2791c8d-0fd8-45c3-a39a-db4e276b0ef3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e2791c8d-0fd8-45c3-a39a-db4e276b0ef3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_90bf199b-1f11-4e4a-905b-0d60c7aa24e1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/90bf199b-1f11-4e4a-905b-0d60c7aa24e1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/90bf199b-1f11-4e4a-905b-0d60c7aa24e1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_06eb5617-44d0-4341-bd95-71f74b0685c6\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/06eb5617-44d0-4341-bd95-71f74b0685c6/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/06eb5617-44d0-4341-bd95-71f74b0685c6/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7efdbeb9-f5b6-4ed0-b82f-4fda5a38c2e4\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7efdbeb9-f5b6-4ed0-b82f-4fda5a38c2e4/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7efdbeb9-f5b6-4ed0-b82f-4fda5a38c2e4/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_23b7a7e0-9cfb-4717-86c1-e7a2b8fbf9b3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/23b7a7e0-9cfb-4717-86c1-e7a2b8fbf9b3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/23b7a7e0-9cfb-4717-86c1-e7a2b8fbf9b3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6b955fcc-3fcb-4ae7-8ace-2dd639449943\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6b955fcc-3fcb-4ae7-8ace-2dd639449943/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6b955fcc-3fcb-4ae7-8ace-2dd639449943/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_fdb0229a-76d3-4389-aeb3-3441a016585d\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fdb0229a-76d3-4389-aeb3-3441a016585d/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fdb0229a-76d3-4389-aeb3-3441a016585d/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_24131eab-084a-40bf-98a6-b287959445db\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/24131eab-084a-40bf-98a6-b287959445db/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/24131eab-084a-40bf-98a6-b287959445db/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_794c0cb9-a359-4556-afcf-001310d483a4\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/794c0cb9-a359-4556-afcf-001310d483a4/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/794c0cb9-a359-4556-afcf-001310d483a4/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f02ab100-1e64-4e8b-b794-020bf7df8d81\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f02ab100-1e64-4e8b-b794-020bf7df8d81/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f02ab100-1e64-4e8b-b794-020bf7df8d81/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_daa569e0-dc5b-48e3-a0c5-724d98afe891\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/daa569e0-dc5b-48e3-a0c5-724d98afe891/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/daa569e0-dc5b-48e3-a0c5-724d98afe891/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_5ffcc908-22fa-4115-b7aa-9c534d61c3d3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/5ffcc908-22fa-4115-b7aa-9c534d61c3d3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/5ffcc908-22fa-4115-b7aa-9c534d61c3d3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cb5ebfc0-4305-4bad-b78f-06d681ace7e8\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cb5ebfc0-4305-4bad-b78f-06d681ace7e8/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cb5ebfc0-4305-4bad-b78f-06d681ace7e8/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_54f51f5a-4390-410d-bc96-dec0c7856d05\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/54f51f5a-4390-410d-bc96-dec0c7856d05/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/54f51f5a-4390-410d-bc96-dec0c7856d05/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cc9125de-ca3a-40dd-ad06-fc10baeeb210\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cc9125de-ca3a-40dd-ad06-fc10baeeb210/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cc9125de-ca3a-40dd-ad06-fc10baeeb210/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7d705ea9-b269-43d9-98e1-a6bb36da239b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7d705ea9-b269-43d9-98e1-a6bb36da239b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7d705ea9-b269-43d9-98e1-a6bb36da239b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_ad436a99-1954-4b9d-812a-98a0f50266fc\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ad436a99-1954-4b9d-812a-98a0f50266fc/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/ad436a99-1954-4b9d-812a-98a0f50266fc/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_541c64c3-d861-438d-a7b9-e2c1e507f4a0\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/541c64c3-d861-438d-a7b9-e2c1e507f4a0/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/541c64c3-d861-438d-a7b9-e2c1e507f4a0/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_60c8ca35-d6bb-464b-b5cf-8f2b121f78b5\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/60c8ca35-d6bb-464b-b5cf-8f2b121f78b5/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/60c8ca35-d6bb-464b-b5cf-8f2b121f78b5/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_65403fad-eb04-493c-a54d-8975c054b8dd\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/65403fad-eb04-493c-a54d-8975c054b8dd/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/65403fad-eb04-493c-a54d-8975c054b8dd/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d3156626-24d1-4513-9fe6-1dd1edee0989\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d3156626-24d1-4513-9fe6-1dd1edee0989/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d3156626-24d1-4513-9fe6-1dd1edee0989/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_84fed266-b6d3-4975-891b-cd774224c684\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/84fed266-b6d3-4975-891b-cd774224c684/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/84fed266-b6d3-4975-891b-cd774224c684/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_918bb867-89b7-4774-8722-b1133684e31b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/918bb867-89b7-4774-8722-b1133684e31b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/918bb867-89b7-4774-8722-b1133684e31b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_bada5092-5e4b-464b-9b52-bd7a7f727a30\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bada5092-5e4b-464b-9b52-bd7a7f727a30/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bada5092-5e4b-464b-9b52-bd7a7f727a30/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7553521b-b89a-4451-922f-d1af4e284403\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7553521b-b89a-4451-922f-d1af4e284403/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7553521b-b89a-4451-922f-d1af4e284403/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9d93cd08-516f-414c-97db-9c8a089df85a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9d93cd08-516f-414c-97db-9c8a089df85a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9d93cd08-516f-414c-97db-9c8a089df85a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_bedb8edb-4c56-4f6c-b066-c4ad73a8de18\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bedb8edb-4c56-4f6c-b066-c4ad73a8de18/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/bedb8edb-4c56-4f6c-b066-c4ad73a8de18/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_eedad711-ffb0-4dd5-b8d5-466902763f36\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/eedad711-ffb0-4dd5-b8d5-466902763f36/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/eedad711-ffb0-4dd5-b8d5-466902763f36/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_87b553d9-51b0-483a-9728-889e53a8143b\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/87b553d9-51b0-483a-9728-889e53a8143b/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/87b553d9-51b0-483a-9728-889e53a8143b/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_1f59d124-f033-4236-aae1-bbb23bcd290e\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/1f59d124-f033-4236-aae1-bbb23bcd290e/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/1f59d124-f033-4236-aae1-bbb23bcd290e/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6f0177fe-47d0-4b2e-98ce-5c04852b6088\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6f0177fe-47d0-4b2e-98ce-5c04852b6088/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6f0177fe-47d0-4b2e-98ce-5c04852b6088/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_20a8201d-097b-42de-99c4-ad6b4ef44df4\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/20a8201d-097b-42de-99c4-ad6b4ef44df4/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/20a8201d-097b-42de-99c4-ad6b4ef44df4/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_27a6175c-419d-4c4d-abf9-e60471c6de25\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/27a6175c-419d-4c4d-abf9-e60471c6de25/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/27a6175c-419d-4c4d-abf9-e60471c6de25/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_1d9ae87f-51e7-4302-aba5-90dc0a911b81\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/1d9ae87f-51e7-4302-aba5-90dc0a911b81/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/1d9ae87f-51e7-4302-aba5-90dc0a911b81/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e260d071-9187-4fa2-8cf2-1abc6dc4320c\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e260d071-9187-4fa2-8cf2-1abc6dc4320c/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e260d071-9187-4fa2-8cf2-1abc6dc4320c/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_461374ae-0c5b-4446-b35e-f4e9cc690bdb\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/461374ae-0c5b-4446-b35e-f4e9cc690bdb/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/461374ae-0c5b-4446-b35e-f4e9cc690bdb/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_15431ff4-8c7a-4a4f-b40c-4e24ba62e9df\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/15431ff4-8c7a-4a4f-b40c-4e24ba62e9df/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/15431ff4-8c7a-4a4f-b40c-4e24ba62e9df/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0cd3a212-c3c9-45b9-b092-642a2ac9e05f\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0cd3a212-c3c9-45b9-b092-642a2ac9e05f/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0cd3a212-c3c9-45b9-b092-642a2ac9e05f/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_78fb8728-d41e-4884-937b-ed8190fd24c8\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/78fb8728-d41e-4884-937b-ed8190fd24c8/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/78fb8728-d41e-4884-937b-ed8190fd24c8/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7f3b06ef-2bd0-4dd0-8fa0-a291ac1f7707\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7f3b06ef-2bd0-4dd0-8fa0-a291ac1f7707/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7f3b06ef-2bd0-4dd0-8fa0-a291ac1f7707/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7ddbb54d-2dfd-4003-b0fa-02d2b29c16a6\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7ddbb54d-2dfd-4003-b0fa-02d2b29c16a6/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7ddbb54d-2dfd-4003-b0fa-02d2b29c16a6/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4f6824fa-51c5-4dcc-a2ed-599bfcc773c2\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4f6824fa-51c5-4dcc-a2ed-599bfcc773c2/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4f6824fa-51c5-4dcc-a2ed-599bfcc773c2/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cdec9796-f250-49b8-9ef7-b037792d8754\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cdec9796-f250-49b8-9ef7-b037792d8754/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cdec9796-f250-49b8-9ef7-b037792d8754/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_697fac68-a9f9-4916-9827-fc426336a648\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/697fac68-a9f9-4916-9827-fc426336a648/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/697fac68-a9f9-4916-9827-fc426336a648/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4e83d1e6-ff77-4ba9-977c-2b2a4833649f\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4e83d1e6-ff77-4ba9-977c-2b2a4833649f/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4e83d1e6-ff77-4ba9-977c-2b2a4833649f/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_2fed03fd-0885-465a-b125-7971c70e6a09\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2fed03fd-0885-465a-b125-7971c70e6a09/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/2fed03fd-0885-465a-b125-7971c70e6a09/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7dc3dfd6-90a8-4e47-bbde-11785366ebce\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7dc3dfd6-90a8-4e47-bbde-11785366ebce/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7dc3dfd6-90a8-4e47-bbde-11785366ebce/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_f8c3c8aa-5896-43b8-a98c-554835fc95d3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f8c3c8aa-5896-43b8-a98c-554835fc95d3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/f8c3c8aa-5896-43b8-a98c-554835fc95d3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6a873d58-e473-4de6-93d1-4a79da30e81c\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6a873d58-e473-4de6-93d1-4a79da30e81c/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6a873d58-e473-4de6-93d1-4a79da30e81c/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c9cf4159-6724-4c15-aa4c-31f3ee006b74\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c9cf4159-6724-4c15-aa4c-31f3ee006b74/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c9cf4159-6724-4c15-aa4c-31f3ee006b74/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_78ca7e2e-8e3f-47ba-bb7b-e7807fe05e20\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/78ca7e2e-8e3f-47ba-bb7b-e7807fe05e20/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/78ca7e2e-8e3f-47ba-bb7b-e7807fe05e20/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_6f892c30-7470-4180-a6e6-e57d8ef176f9\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6f892c30-7470-4180-a6e6-e57d8ef176f9/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/6f892c30-7470-4180-a6e6-e57d8ef176f9/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_1fff41ed-c8ab-4fb8-88c4-46bb86251644\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/1fff41ed-c8ab-4fb8-88c4-46bb86251644/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/1fff41ed-c8ab-4fb8-88c4-46bb86251644/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e7a16638-71dc-42e7-bbb9-257b7a6b1120\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e7a16638-71dc-42e7-bbb9-257b7a6b1120/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e7a16638-71dc-42e7-bbb9-257b7a6b1120/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_4143abab-fc72-4e47-a28d-8f9ee0ba7758\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4143abab-fc72-4e47-a28d-8f9ee0ba7758/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/4143abab-fc72-4e47-a28d-8f9ee0ba7758/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_0978f029-1169-41b2-8342-8463e44c7a67\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0978f029-1169-41b2-8342-8463e44c7a67/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/0978f029-1169-41b2-8342-8463e44c7a67/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7524389d-db53-41b9-a9b3-cb82b23d2267\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7524389d-db53-41b9-a9b3-cb82b23d2267/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7524389d-db53-41b9-a9b3-cb82b23d2267/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_905284e6-5395-4655-9982-92a60ed97b7a\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/905284e6-5395-4655-9982-92a60ed97b7a/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/905284e6-5395-4655-9982-92a60ed97b7a/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9970897c-93a8-49cf-a2af-812745571585\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9970897c-93a8-49cf-a2af-812745571585/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9970897c-93a8-49cf-a2af-812745571585/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_aa5f696b-3702-45ae-8706-d6d635e55e34\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/aa5f696b-3702-45ae-8706-d6d635e55e34/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/aa5f696b-3702-45ae-8706-d6d635e55e34/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_a129da69-c319-4abd-9546-55d3c15513b6\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a129da69-c319-4abd-9546-55d3c15513b6/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a129da69-c319-4abd-9546-55d3c15513b6/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_326ad9ef-bfd8-4d21-ba12-8e4d8bf29145\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/326ad9ef-bfd8-4d21-ba12-8e4d8bf29145/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/326ad9ef-bfd8-4d21-ba12-8e4d8bf29145/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cf3ba086-fbad-4ed4-86eb-b671459d09d3\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cf3ba086-fbad-4ed4-86eb-b671459d09d3/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cf3ba086-fbad-4ed4-86eb-b671459d09d3/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_5387550b-aa32-4a0b-8d3d-0d1b4975b8c1\" adlcp:scormType=\"sco\" type=\"webcontent\" webBehaviour=\"\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/5387550b-aa32-4a0b-8d3d-0d1b4975b8c1/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/5387550b-aa32-4a0b-8d3d-0d1b4975b8c1/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_68f94426-df43-4580-a027-f114853fca33\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/68f94426-df43-4580-a027-f114853fca33/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/68f94426-df43-4580-a027-f114853fca33/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_cbd921a4-f554-4d5b-b44c-9bbb622ad8f7\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cbd921a4-f554-4d5b-b44c-9bbb622ad8f7/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/cbd921a4-f554-4d5b-b44c-9bbb622ad8f7/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_8c72e124-a3c4-4c97-b77d-b33288193b49\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8c72e124-a3c4-4c97-b77d-b33288193b49/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/8c72e124-a3c4-4c97-b77d-b33288193b49/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_a2527072-d2a5-41c8-9b78-20103ee955b7\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a2527072-d2a5-41c8-9b78-20103ee955b7/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/a2527072-d2a5-41c8-9b78-20103ee955b7/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9471078e-0e8f-4e91-878f-517aade66636\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9471078e-0e8f-4e91-878f-517aade66636/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9471078e-0e8f-4e91-878f-517aade66636/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_7835e73e-d12f-457d-a581-b5d093a26c19\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7835e73e-d12f-457d-a581-b5d093a26c19/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/7835e73e-d12f-457d-a581-b5d093a26c19/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e9b2d400-bc78-47df-9708-c1e63ee27456\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e9b2d400-bc78-47df-9708-c1e63ee27456/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e9b2d400-bc78-47df-9708-c1e63ee27456/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_9aaf4474-8b92-488e-8d1c-d1d3582a2237\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9aaf4474-8b92-488e-8d1c-d1d3582a2237/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/9aaf4474-8b92-488e-8d1c-d1d3582a2237/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_be66c67d-9f1b-4d0d-805b-5d0f697352cc\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/be66c67d-9f1b-4d0d-805b-5d0f697352cc/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/be66c67d-9f1b-4d0d-805b-5d0f697352cc/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_219e918c-6bda-4884-9855-be0b7f0c9549\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/219e918c-6bda-4884-9855-be0b7f0c9549/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/219e918c-6bda-4884-9855-be0b7f0c9549/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_c50c3397-cce4-4055-9fdf-1300574d236c\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c50c3397-cce4-4055-9fdf-1300574d236c/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/c50c3397-cce4-4055-9fdf-1300574d236c/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_e48ce843-4bff-4ccc-b316-d51b1fcb3afe\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e48ce843-4bff-4ccc-b316-d51b1fcb3afe/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/e48ce843-4bff-4ccc-b316-d51b1fcb3afe/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_283b1f1f-5793-4dd2-8f50-f9f2569010f9\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/283b1f1f-5793-4dd2-8f50-f9f2569010f9/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/283b1f1f-5793-4dd2-8f50-f9f2569010f9/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_359cf026-7d29-4ee0-9fc6-04a1b915df65\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/359cf026-7d29-4ee0-9fc6-04a1b915df65/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/359cf026-7d29-4ee0-9fc6-04a1b915df65/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_fdadfec6-07ae-4c72-a2d4-8f65829ffcc7\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fdadfec6-07ae-4c72-a2d4-8f65829ffcc7/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/fdadfec6-07ae-4c72-a2d4-8f65829ffcc7/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_939d3560-066e-42b5-b3c5-890f12c02f84\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/939d3560-066e-42b5-b3c5-890f12c02f84/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/939d3560-066e-42b5-b3c5-890f12c02f84/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_d320d4d3-d5bb-4eca-915a-9aeb3b5eda03\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d320d4d3-d5bb-4eca-915a-9aeb3b5eda03/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/d320d4d3-d5bb-4eca-915a-9aeb3b5eda03/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"R_50c9fc7d-9fa0-4b23-806d-4f576b91f63e\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/50c9fc7d-9fa0-4b23-806d-4f576b91f63e/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/50c9fc7d-9fa0-4b23-806d-4f576b91f63e/\"/>" + 
				"    </resource>" + 
				"    <resource identifier=\"DesignData0_Root\" adlcp:scormType=\"asset\" type=\"webcontent\" href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/39e9a1cc-b46b-48c0-a50f-eb368e1fb516/\">" + 
				"      <file href=\"https://core-qa.bravais.com/api/dynamic/documentVersions/4955/contentObjects/lcmsXmlGuid/39e9a1cc-b46b-48c0-a50f-eb368e1fb516/\"/>" + 
				"    </resource>" + 
				"  </resources>" + 
				"</manifest>";
		
		InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
		InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream);
		post.setEntity(inputStreamEntity);

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		InputStream input = entity.getContent();
		FileOutputStream fos = new FileOutputStream(new File("D:/Xyleme/performance/products/bravais/output/response.txt"));
		int read = 0;
		byte[] buffer = new byte[32768];
		while((read = input.read(buffer)) > 0) {
			fos.write(buffer, 0, read);
		}
		fos.close();
		input.close();
       	System.out.println("REQUEST: " + currentURL + "; " + "RESPONSE: " + response.getStatusLine());
       	EntityUtils.consume(entity);
	}

}
