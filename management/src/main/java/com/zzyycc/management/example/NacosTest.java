package com.zzyycc.management.example;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosTest {

    @Value("${zzyycc.name}")
    private String name;

    @Value("${zzyycc.age}")
    private String age;

    @GetMapping("nacos/info")
    public String getApplicationInfo() {
        return name + ":" + age;
    }
}
