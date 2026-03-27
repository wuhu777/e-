package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.junit.Assert;
import org.junit.Test;

public class EduLoginControllerTest {

    @Test
    public void helloReturnsChineseGreetingMessage() {
        EduLoginController controller = new EduLoginController();

        R response = controller.hello();

        Assert.assertTrue(response.getSuccess());
        Assert.assertEquals("你好", response.getMessage());
    }
}
