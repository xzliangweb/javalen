package com.example.itheima_reggie_take;

import org.junit.jupiter.api.Test;

public class UploadFileTest {
    @Test
    public void test(){
        String url = "slur.jpg";
        String substring = url.substring(url.lastIndexOf("."));
        System.out.println(substring);
    }
}
