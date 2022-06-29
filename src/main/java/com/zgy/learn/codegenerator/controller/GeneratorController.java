package com.zgy.learn.codegenerator.controller;

import com.zgy.learn.codegenerator.util.Generator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zgy
 * @date 2022/6/28
 */
@RestController
public class GeneratorController {
    @Resource
    private Generator generator;

    @GetMapping("generator")
    public String generator() {
        generator.generate();
        return "okay";
    }

}
