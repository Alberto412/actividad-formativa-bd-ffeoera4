package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
