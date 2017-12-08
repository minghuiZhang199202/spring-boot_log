package com.source.web;

import org.springframework.web.bind.annotation.*;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 15:49 2017/12/8</p>
 * <p>modified By: </p>
 */
@RestController
public class HelloWorldController {
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(@RequestParam String name){
        return "Hello " + name;

    }
}
