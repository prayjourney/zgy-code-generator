package com.zgy.learn.zgycodegenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zgy
 * @date 2022/6/29
 */
@Controller
public class IndexController {
    @GetMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }

}
