package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.FileForm;

import lombok.Data;

@Controller
@RequestMapping("/home")
public class ReadExcel {
	@RequestMapping("")
	public String showUploadScreen(Model model) {
		FileForm fileForm = new FileForm();
		model.addAttribute("fileForm", fileForm);
		return "index";
	}
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploadFile(@Valid @ModelAttribute("fileForm")FileForm fileForm,BindingResult bindingResult, Model model) throws IOException{
		if(bindingResult.hasErrors()) {
			return "index";
		}
		Workbook workbook = new XSSFWorkbook(fileForm.getExcelFile().getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		Map<Integer, List<String>> excelData = new HashMap<>();
		ArrayList<String> headStringList = new ArrayList<String>();
		Row headRow = sheet.getRow(0);
		for(Cell headCell: headRow) {
			String getResultString = getCellValue(headCell);
			if(getResultString != null) {
				headStringList.add(getResultString);
			}
		}
		ArrayList<ArrayList<String>> bodyData = new ArrayList<ArrayList<String>>();
		for(int i = 1; i<= 9; i++) {
			Row bodyRow = sheet.getRow(i);
			ArrayList<String> bodyRowStrings = new ArrayList<String>();
			for(Cell bodyCell: bodyRow) {
				String getResultString = getCellValue(bodyCell);
				if(getResultString != null) {
					bodyRowStrings.add(getResultString);
				}
			}
			bodyData.add(bodyRowStrings);
		}
		model.addAttribute("headStringList", headStringList);
		model.addAttribute("bodyData", bodyData);
		return "index";
	}
	
	private String getCellValue(Cell cell) {
		String resultString = new String();
		switch (cell.getCellType()) {
		case STRING: {
			resultString = cell.getRichStringCellValue().getString();
			break;
			
		}
		case NUMERIC: {
			if (DateUtil.isCellDateFormatted(cell)) {
			     resultString = String.valueOf(cell.getDateCellValue() + "");
			} else {
			     resultString = String.valueOf(cell.getNumericCellValue() + "");
			}
			break;
		}
		case BOOLEAN: {
			resultString = String.valueOf(cell.getBooleanCellValue()+ "");
			break;
		}
		case FORMULA: {
			resultString = String.valueOf(cell.getCellFormula() + "");
			break;
		}
		default:
			resultString = null;
		}
		return resultString;
	}
	
}
