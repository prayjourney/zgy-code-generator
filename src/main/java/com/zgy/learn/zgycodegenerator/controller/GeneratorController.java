package com.zgy.learn.zgycodegenerator.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.zgy.learn.zgycodegenerator.util.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zgy
 * @date 2022/6/28
 */
@Slf4j
@RestController
public class GeneratorController {
    @Resource
    private Generator generator;

    @GetMapping("generator")
    public JSONObject generator() {
        JSONObject result = new JSONObject();
        result.putOpt("code", 1);
        result.putOpt("message", "error");
        try {
            log.info("开始生成项目, 时间是:{}", DateUtil.now());
            generator.generate();
            log.info("项目生成完毕, 时间是:{}", DateUtil.now());
            result.putOpt("code", 0);
            result.putOpt("message", "success");
            return result;
        } catch (Exception e) {
            return result;
        }
    }

}
