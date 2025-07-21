package com.toy.myroomnow.globalexception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointer(Exception e) {
        // 로깅도 가능
        System.out.println("❗ NullPointerException: " + e.getMessage());
        return "redirect:/login";
    }
}
