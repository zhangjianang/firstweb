package com.ang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("front")
    public String handleError(){
        return "pages/front"; // 这里返回的是文件名，这里会展示 404.html
    }

}
