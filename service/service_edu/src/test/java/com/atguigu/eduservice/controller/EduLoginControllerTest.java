package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class EduLoginControllerTest {

    @Test
    public void helloReturnsNiHaoMessage() throws Exception {
        EduLoginController controller = new EduLoginController();

        R response = controller.hello();

        Field messageField = R.class.getDeclaredField("message");
        messageField.setAccessible(true);
        assertEquals("你好", messageField.get(response));
    }
}
