package com.alessiottavo.reactor.controller;


import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MarkdownController {

    @GetMapping("/")
    @ResponseBody
    public String getIndex() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/templates/readme.html")));
        } catch (Exception e) {
            return e.toString();
        }
    }
}
