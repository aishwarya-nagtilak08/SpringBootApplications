package com.excel.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

		ResponseEntity<Object> response = restTemplate.getForEntity("https://reqres.in/api/users?page=2", Object.class);

		String filename = "C:\\Users\\Harshal\\Desktop\\Java Projects\\Product.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("FirstSheet");

		HSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue("No.");
		rowhead.createCell(1).setCellValue("Name");
		rowhead.createCell(2).setCellValue("Address");
		rowhead.createCell(3).setCellValue("Email");

		HSSFRow row = sheet.createRow((short) 1);
		row.createCell(0).setCellValue("1");
		row.createCell(1).setCellValue("Sankumarsingh");
		row.createCell(2).setCellValue("India");
		row.createCell(3).setCellValue("sankumarsingh@gmail.com");

		FileOutputStream fileOut = new FileOutputStream(filename);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		System.out.println("Your excel file has been generated!");

		return "yedya";
	}
}
