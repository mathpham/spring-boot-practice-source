package com.example.demo.entity;


import org.springframework.web.multipart.MultipartFile;

import com.example.demo.annotation.CheckFile;
import com.example.demo.annotation.UploadFileMaxSize;
import com.example.demo.annotation.UploadFileNotEmpty;
import com.example.demo.annotation.UploadFileRequired;



public class FileForm {
	
	@UploadFileRequired
	@UploadFileNotEmpty
	@UploadFileMaxSize(value = 1024*1024)
	@CheckFile(max_length = 20)
	private MultipartFile excelFile;

	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}
	
	
}
