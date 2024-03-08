package com.simplecode.service.service.impl;


import com.simplecode.service.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ArticlesServiceImplTest {
    @Autowired
    private ArticlesServiceImpl articlesService;

    @Test
    public void testGetString(){
        String x = articlesService.getString("x");
        System.out.println(x);
    }

}
