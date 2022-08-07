package com.example.demo.annotation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.properties.Field;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class CheckFileValidator implements ConstraintValidator<CheckFile, MultipartFile> {

	int max_length;
	String message;
	@Override
	public void initialize(CheckFile annotation) {
	
		this.max_length = annotation.max_length();
	}
	
	@Override
	public boolean isValid(MultipartFile excelFile, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		String fileName = excelFile.getOriginalFilename();
		if(fileName.length() > max_length) {
			return false;
		}else {
			return true;
		}
	}
}
