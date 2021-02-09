package com.excel.NSE.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.excel.NSE.constants.Constants;
import com.excel.NSE.pojos.DetailData;
import com.excel.NSE.pojos.NSEObject;
import com.excel.NSE.scheduler.Reminder;
import com.fasterxml.jackson.databind.ObjectMapper;;

@RestController
@RequestMapping(value = "/NSE")
public class NSEController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/getNSE")
	public String getNSE() throws Exception {
		URL obj = new URL(Constants.GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", Constants.USER_AGENT);

		con.setRequestProperty("Accept-Charset", Constants.charset);
		con.setRequestProperty("accept-language", "en-GB,en-US;q=0.9,en;q=0.8");
		con.setRequestProperty("sec-fetch-mode", "navigate");
		con.setRequestProperty("sec-fetch-dest", "document");
		con.setRequestProperty("server-timing", "edge; dur=1");

		con.setUseCaches(true);
		int responseCode = con.getResponseCode();
		StringBuffer response = new StringBuffer();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			Object content = con.getHeaderFieldKey(1);
			System.out.println(content.toString());
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String current = new java.io.File(".").getCanonicalPath();
			// print result
			System.out.println(response.toString());

		}
		return response.toString();
	}

	@GetMapping("/getNSE_RT")
	public void getNSE_RT() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constants.GET_URL)
				.queryParam("User-Agent", Constants.USER_AGENT)
				.queryParam("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
				.queryParam("Accept-Charset", Constants.charset).queryParam("sec-fetch-mode", "navigate")
				.queryParam("sec-fetch-dest", "document").queryParam("server-timing", "edge; dur=1");

		System.out.println(builder.toUriString());

		HttpEntity<?> request = new HttpEntity<>(null);
		ResponseEntity<String> respEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request,
				String.class);

		System.out.println(respEntity.toString());
	}

	@GetMapping("/createExcelNSE")
	public void createExcelNSE() throws Exception {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormatter.parse("2021-02-08 13:05:45");

		Timer timer = new Timer();
		timer.schedule(new Reminder(), date);
		int period = 1000;
		timer.schedule(new Reminder(), date, period);

	}

	@GetMapping("/createExcelNSE_thread")
	public void createExcelNSE_thread() throws Exception {
		while (true) {
			Reminder rem = new Reminder();
			rem.run();
			Thread.sleep(40000);
		}
	}

}