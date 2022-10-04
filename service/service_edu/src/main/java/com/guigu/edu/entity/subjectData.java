package com.guigu.edu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class subjectData {

    @ExcelProperty(index = 0)
    public String oneSubjectName;

    @ExcelProperty(index = 1)
    public String twoSubjectName;
}
