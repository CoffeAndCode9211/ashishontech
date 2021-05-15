package com.ashishontech.demo.controller;


import com.ashishontech.demo.service.RetryInternalCallService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RetryController {

    private final RetryInternalCallService retryService;

    @GetMapping("data")
    public String getData(@RequestParam  String value){
        return retryService.getValue(value);
    }
}
