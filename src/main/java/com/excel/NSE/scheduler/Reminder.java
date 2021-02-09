package com.excel.NSE.scheduler;

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

import com.excel.NSE.constants.Constants;
import com.excel.NSE.pojos.DetailData;
import com.excel.NSE.pojos.NSEObject;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Reminder extends TimerTask {

	public void run() {
		System.out.println("RUN called");
		URL obj;
		HttpURLConnection con;
		try {
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

			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				Object content = con.getHeaderFieldKey(1);
				System.out.println(content.toString());
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				String current = new java.io.File(".").getCanonicalPath();
				// print result

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
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}