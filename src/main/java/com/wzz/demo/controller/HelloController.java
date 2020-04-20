package com.wzz.demo.controller;

import com.wzz.demo.pojo.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "你好";
    }

    @GetMapping("/info1")
    public void getInfo(@RequestParam String name , Integer age){
        System.out.println(name + ":" + age);
    }

    @PostMapping("/info2")
    public void postInfo(User user , String username){
        System.out.println(username);
    }
}
