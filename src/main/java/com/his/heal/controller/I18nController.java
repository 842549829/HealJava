package com.his.heal.controller;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/i18n")
public class I18nController {

    private final MessageSource messageSource;

    public I18nController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 多语言测试
     *  中文 Accept-Language zh-CN
     *  英文 Accept-Language en-US
     * @param locale
     * @return
     */
    @GetMapping
    public String getI18n(Locale locale) {
        return messageSource.getMessage("welcome.message", null, locale);
    }
}
