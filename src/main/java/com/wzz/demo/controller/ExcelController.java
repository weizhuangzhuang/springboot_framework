package com.wzz.demo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.wzz.demo.pojo.Student;
import com.wzz.demo.service.StudentService;
import com.wzz.demo.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/exportExcel")
    public void export(HttpServletResponse response){
        List<Student> students = studentService.findAll();
        ExcelUtils.exportExcel(students , "easypoi导出功能", "导出sheet1",Student.class , "测试student.xls", response);
        //return JSONResult.ok();
    }

    @PostMapping("/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 数据处理
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        // 需要验证
        importParams.setNeedVerfiy(false);

        try {
            ExcelImportResult<Student> result = ExcelImportUtil.importExcelMore(file.getInputStream(), Student.class,
                    importParams);
            List<Student> studentList = result.getList();
            for (Student student : studentList) {
                // System.out.println(User);
                log.info("从Excel导入数据到数据库的详细为 ：{}", JSONObject.toJSONString(student));
                //TODO 将导入的数据做保存数据库操作
            }
            log.info("从Excel导入数据一共 {} 行 ", studentList.size());
            studentService.insertStudent(studentList);
        } catch (IOException e) {
            log.error("导入失败：{}", e.getMessage());
        } catch (Exception e1) {
            log.error("导入失败：{}", e1.getMessage());
        }
        return "导入成功";
    }

}
