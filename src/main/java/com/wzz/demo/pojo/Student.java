package com.wzz.demo.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Student {

    @Excel(name = "id", width=15)
    @NotBlank(message = "该字段不能为空")
    private Integer id;

    @Excel(name = "姓名", orderNum = "0", width=30)
    private String name;

    @Excel(name = "性别", replace = { "男_1", "女_2" }, orderNum = "1", width=30)
    private String sex;

    @Excel(name = "生日", orderNum = "2", width=30)
    private String birthday;


}
