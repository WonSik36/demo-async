package com.example.demoasync;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SomeController {
    private final SomeService service;


    @GetMapping("/test1")
    public String syncEvent() {

        service.syncEvent();

        return "test1";
    }

    @GetMapping("/test2")
    public String asyncEvent() {
        service.asyncEvent();

        return "test2";
    }
}
