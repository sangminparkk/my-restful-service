package com.chandler.myrestfulservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageSource messageSource;

    @GetMapping("/internationalized")
    public String helloResponse(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
