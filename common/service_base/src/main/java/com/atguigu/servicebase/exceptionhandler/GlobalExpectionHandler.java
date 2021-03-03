package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
/*统一异常处理类*/
@ControllerAdvice
@Slf4j
public class GlobalExpectionHandler {
        //指定出现什么异常处理这个方法
        @ExceptionHandler(Exception.class)
        @ResponseBody//为了返回数据
        public R error(Exception e){
            e.printStackTrace();
            return R.error().message("执行了全局处理异常");
        }

        @ExceptionHandler(ArithmeticException.class)
        @ResponseBody //为了返回数据
         public R error(ArithmeticException e){
            e.printStackTrace();
            return R.error().message("执行了ArithmeticExpection异常处理。。。");
        }

        @ExceptionHandler(GuliException.class)
        @ResponseBody //为了返回数据
        public R error(GuliException e){
            log.error(e.getMessage());
            e.printStackTrace();
            return R.error().code(e.getCode()).message(e.getMsg());
        }
}
