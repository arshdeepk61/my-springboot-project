package org.example.controller;

import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DemoController {

    @GetMapping("/async-demo")
    public String asyncDemo() {
        // Set MDC for current request
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("username", "student");

        doAsyncTask();

        return "Request processed, check logs with MDC";
    }

    @Async("asyncExecutor")
    public void doAsyncTask() {
        // MDC is propagated automatically!
        System.out.println("Async task running, requestId=" + MDC.get("requestId"));
    }
}
