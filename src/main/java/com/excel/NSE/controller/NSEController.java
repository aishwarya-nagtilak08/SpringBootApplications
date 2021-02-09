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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

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

	@GetMapping("/addTORMQ")
	public String addTORMQ() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("NSE_CHANNEL", false, false, false, null);

		/***************************************************************/

		URL obj;
		HttpURLConnection con;

		obj = new URL(Constants.GET_URL);
		con = (HttpURLConnection) obj.openConnection();
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

			channel.basicPublish("", "NSE_CHANNEL", null, response.toString().getBytes());
			System.out.println(" [x] Sent '" + response.toString() + "'");

			NSEObject nseObject = new ObjectMapper().readValue(response.toString(), NSEObject.class);
			String expiryDateInput = "11-Feb-2021";
			Long putOI = 0L;
			Long callOI = 0L;
			List<DetailData> detailDataList = nseObject.getFiltered().getData();
			for (Long strikePriceIndex = 14500L; strikePriceIndex <= 15500; strikePriceIndex += 50) {
				Long finalStrikePriceIndex = strikePriceIndex;
				List<DetailData> filteredDetailData = detailDataList.stream()
						.filter((ss) -> ss.getStrikePrice().equals(finalStrikePriceIndex)
								&& ss.getExpiryDate().equals(expiryDateInput))
						.collect(Collectors.toList());
				putOI += filteredDetailData.get(0).getPE().getOpenInterest();
				callOI += filteredDetailData.get(0).getCE().getOpenInterest();
			}

			String filename = "./Balance.xlsx";
			// creating an instance of HSSFWorkbook class
			File excelFile = new File(filename);
			if (!excelFile.exists()) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				// invoking creatSheet() method and passing the name of the sheet to be created
				HSSFSheet sheet = workbook.createSheet("January");
				// creating the 0th row using the createRow() method
				HSSFRow rowhead = sheet.createRow((short) 0);
				// creating cell by using the createCell() method and setting the values to the
				// cell by using the setCellValue() method
				rowhead.createCell(0).setCellValue("Time");
				rowhead.createCell(1).setCellValue("PCR");
				rowhead.createCell(2).setCellValue("call OI");
				rowhead.createCell(3).setCellValue("PutOI");

				FileOutputStream fileOut = new FileOutputStream(filename);
				workbook.write(fileOut);
				// closing the Stream
				fileOut.close();
				// closing the workbook
			}
			Double avgCallOI = callOI.doubleValue() / 20;
			Double avgPutOI = putOI.doubleValue() / 20;
			Double pcr = avgPutOI / avgCallOI;
			System.out.println(pcr);
			Object[][] bookData = { { new Date(), pcr, callOI, putOI }, };

			// creating an instance of HSSFWorkbook class
			FileInputStream inputStream = new FileInputStream(filename);
			Workbook workbook1 = WorkbookFactory.create(inputStream);

			Sheet sheet1 = workbook1.getSheetAt(0);
			int rowCount = sheet1.getLastRowNum();

			for (Object[] aBook : bookData) {
				Row row = sheet1.createRow(++rowCount);

				int columnCount = 0;

				Cell cell = row.createCell(columnCount);
				cell.setCellValue(rowCount);

				for (Object field : aBook) {
					cell = row.createCell(columnCount++);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Long) {
						cell.setCellValue((Long) field);
					} else if (field instanceof Date) {
						DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
						Date ss = (Date) field;
						cell.setCellValue(timeFormat.format(ss));
					} else if (field instanceof Double) {
						cell.setCellValue((Double) field);
					}
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream("./Balance.xlsx");
			workbook1.write(outputStream);
			outputStream.close();
			System.out.println("Excel file has been generated successfully.");

			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String msg = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + msg + "'");
			};
			channel.basicConsume("NSE_CHANNEL", true, deliverCallback, consumerTag -> {
			});
			
		}
		return response.toString();
	}
}