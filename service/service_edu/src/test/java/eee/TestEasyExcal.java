package eee;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcal {
    public static void main(String[] args) {
        String filename="D:\\write.xlsx";


        //调用easyexcel的方法实现写操作
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(TestEasyExcal.getData());


        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setNumber(i);
            data.setName("name"+i);

            list.add(data);
        }

        return list;
    }
}
