package com.wzz.demo.service.impl;

import com.wzz.demo.mapper.StudentMapper;
import com.wzz.demo.pojo.Student;
import com.wzz.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    @Override
    public void insertStudent(List<Student> studentList) {
        studentMapper.insertList(studentList);
    }
}
