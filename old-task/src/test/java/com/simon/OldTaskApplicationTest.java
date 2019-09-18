package com.simon;

import com.alibaba.fastjson.JSON;
import com.simon.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-17 15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OldTaskApplicationTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void deleteTest() {
        taskService.delete("com.simon.task.CtripScenicJob", "group1");
    }

    @Test
    public void listTest() {
        System.out.println(JSON.toJSONString(taskService.list()));
    }
}
