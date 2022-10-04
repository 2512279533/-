package eee;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

    @ExcelProperty("学生编号")
    int number;

    @ExcelProperty("学生姓名")
    String name;
}
