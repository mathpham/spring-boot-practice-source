package com.example.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Email;
import com.example.demo.service.EmailSenderService;

@SpringBootTest
public class EmailSenderServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    public void sendHtmlMessageTest() throws MessagingException {
        Email email = new Email();
        email.setTo("test@gmail.com");
        email.setFrom("test@gmail.com");
        email.setSubject("Welcome Email from CodingNConcepts");
        email.setTemplate("welcome-email.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "PhamVanGiang");
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("Python", "Go", "C#"));
        email.setProperties(properties);

        Assertions.assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
    }
    
    @Test
    public void sendProjectIntroEmailTest() throws MessagingException {
        Email email = new Email();
        email.setTo("test@gmail.com");
        email.setFrom("test@gmail.com");
        email.setSubject("Out Projects");
        email.setTemplate("project-intro.html");
        Map<String, Object> properties = new HashMap<>();
        properties.put("tel", "0123456789");
        properties.put("brand", "MyGiang");
        properties.put("title", "Dự án của chúng tôi");
        properties.put("address", "Hoàng Mai, Hà Nội, Việt Nam");
        properties.put("about", " Chúng tôi đến từ Việt  Nam");
        properties.put("linkList", Arrays.asList("Trang Chu", "Tin Tuc","Gioi Thieu", "Lien He" ));
        email.setProperties(properties);

        Assertions.assertDoesNotThrow(() -> emailSenderService.sendHtmlMessage(email));
    }
}