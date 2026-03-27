package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EduLoginControllerTest {

    @Test
    public void helloReturnsNiHaoMessage() throws Exception {
        EduLoginController controller = new EduLoginController();

        R response = controller.hello();

        assertEquals("你好", response.getMessage());
    }
}
