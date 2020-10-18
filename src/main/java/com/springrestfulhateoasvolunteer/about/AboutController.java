package com.springrestfulhateoasvolunteer.about;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String getAbout(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("about.message", null, locale);
    }
}
