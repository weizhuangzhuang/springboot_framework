package com.wzz.demo.controller;

import com.wzz.demo.service.UserService;
import com.wzz.demo.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryall")
    public JSONResult queryAllUser() {
        return JSONResult.ok(userService.queryAllUser());
    }
}
