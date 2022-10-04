package com.guigu.GlobalExaeption;

import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(ArithmeticException.class)
//    public R error(Exception e){
//        e.printStackTrace();
//        return R.error().message("执行了自定义异常");
//    }

    @ResponseBody
    @ExceptionHandler(guliException.class)
    public R error(guliException e){
        e.printStackTrace();
        log.error(e.getMsg());
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
