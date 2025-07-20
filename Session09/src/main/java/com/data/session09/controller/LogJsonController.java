package com.data.session09.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogJsonController {

    @GetMapping("/info")
    public String logInfo() {
        log.info("INFO level - User accessed the /log/info endpoint");
        return "Info log recorded";
    }

    @GetMapping("/error")
    public String logError() {
        try {
            int result = 10/0;
            return "Result: " + result;
        } catch (Exception e) {
            log.error("ERROR level - Exception occurred", e);
            return "Error logged";
        }
    }

    @GetMapping("/event")
    public String logEvent(@RequestParam String user, @RequestParam String action) {
        log.info("User action event | user={} | action={}", user, action);
        return "Event log recorded";
    }
}