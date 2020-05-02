package com.wzz.demo.service;

import com.wzz.demo.pojo.Student;

import java.util.List;

public interface StudentService {

    /**
     * 查询所有学生信息
     * @return
     */
    List<Student> findAll();

    /**
     * 插入学生列表
     * @param studentList
     */
    void insertStudent(List<Student> studentList);

}
