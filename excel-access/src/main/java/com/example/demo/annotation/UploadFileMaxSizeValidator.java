package com.example.demo.annotation;

import javax.validation.*;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileMaxSizeValidator implements
ConstraintValidator<UploadFileMaxSize, MultipartFile> {

private UploadFileMaxSize constraint;

@Override
public void initialize(UploadFileMaxSize constraint) {
    this.constraint = constraint;
}

@Override
public boolean isValid(MultipartFile multipartFile,
    ConstraintValidatorContext context) {
    if (constraint.value() < 0 || multipartFile == null) {
        return true;
    }
    return multipartFile.getSize() <= constraint.value();
}

}