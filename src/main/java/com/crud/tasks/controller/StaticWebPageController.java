package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StaticWebPageController {
    public Map<String, String> mathOperations = new HashMap<>();
    public int i=1;

    @RequestMapping("/")
    public String index(Map<String, String> model) {
        mathOperations.put("2 * 2", "4");
        mathOperations.put("2 * 2 + 2", "6");
        mathOperations.put("2 - 2 * 2", "-2");
        model.put("variable", "My Thymeleaf variable");
        for(Map.Entry<String, String> variable:mathOperations.entrySet()){
            model.put("operation"+i, variable.getKey());
            model.put("outcome"+i, variable.getValue());
            i++;
        }
        return "index";
    }
}
