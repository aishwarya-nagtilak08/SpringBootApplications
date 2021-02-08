package com.excel.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.excel.model.DataTO;
import com.excel.pojos.Datum;
import com.excel.pojos.MainClass;
import com.excel.repository.DataRepository;

@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	DataRepository dataRepository;

	private static final Logger log = LoggerFactory.getLogger(ExcelController.class);

	@GetMapping(value = "/getData")
	public ResponseEntity<MainClass> getData() {
		ResponseEntity<MainClass> response = restTemplate.getForEntity("https://reqres.in/api/users?page=2",
				MainClass.class);
		MainClass mainList = response.getBody();
		List<Datum> datumList = mainList.getData();
		for (Datum dt : datumList) {
			DataTO dto = new DataTO();
			dto.setEmail(dt.getEmail());
			dto.setFirstName(dt.getFirstName());
			dto.setLastName(dt.getLastName());
			dataRepository.save(dto);
		}
		return response;
	}

	@GetMapping("/createExcel")
	public String createExcel() throws IOException {

		ResponseEntity<MainClass> response = restTemplate.getForEntity("https://reqres.in/api/users?page=2",
				MainClass.class);

		MainClass mainList = response.getBody();
		List<Datum> datumList = mainList.getData();

		String filename = "C:\\Users\\Harshal\\Desktop\\Java Projects\\Product.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("FirstSheet");

		HSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue("Id");
		rowhead.createCell(1).setCellValue("Email");
		rowhead.createCell(2).setCellValue("First Name");
		rowhead.createCell(3).setCellValue("Last Name");

		HSSFRow row = sheet.createRow((short) 1);

		FileOutputStream fileOut = new FileOutputStream(filename);
		int cellnum = 0;
		for (Datum fk : datumList) {

			for (Datum d : datumList) {
				Cell cell = row.createCell(cellnum++);
				sheet.autoSizeColumn((short) cellnum);
				if (d.getId() instanceof Integer) {
					cell.setCellValue(fk.getId());
				}
				if (d.getEmail() instanceof String) {
					cell.setCellValue(fk.getEmail());
				}
				if (d.getFirstName() instanceof String) {
					cell.setCellValue(fk.getFirstName());
				}
				if (d.getLastName() instanceof String) {
					cell.setCellValue(fk.getLastName());
				}
			}

			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			System.out.println("Your excel file has been generated!");
		}

		return "string";
	}

	
}
