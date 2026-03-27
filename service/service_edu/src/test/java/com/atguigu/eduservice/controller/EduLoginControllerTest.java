package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EduLoginControllerTest {

    @Test
    public void testHelloReturnsGreetingMessage() {
        EduLoginController controller = new EduLoginController();

        R response = controller.hello();

        assertTrue(response.getSuccess());
        assertEquals("你好", response.getMessage());
    }
}
