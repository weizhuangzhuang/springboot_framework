package com.wzz.demo.controller;

import com.wzz.demo.service.StudentService;
import com.wzz.demo.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/all")
    public JSONResult findAll(){
        return JSONResult.ok(studentService.findAll());
    }

}
