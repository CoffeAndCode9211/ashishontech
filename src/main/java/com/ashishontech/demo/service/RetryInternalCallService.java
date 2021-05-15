package com.ashishontech.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class RetryInternalCallService {

    @Resource(name = "retryInternalCallService")
    private RetryInternalCallService self;

    public String getValue(String appender) {
        return self.getData(appender);
    }

    @Retryable(value = NumberFormatException.class, maxAttempts = 4, backoff = @Backoff(500))
    public String getData(String appender) {
        log.info("Calling getData");
        Integer value = Integer.parseInt(appender);
        value++;
        return value.toString();
    }

    @Recover
    public String recoverData(String appender) {
        log.info("Calling recoverData");
        return "DEFAULT_1_1";
    }

}
