package com.simon;

import com.simon.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试
 *
 * @author simon
 * @date 2019-01-30
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTest {
    @Autowired
    private CityService cityService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void cityTest() {
        System.out.println("城市总数" + cityService.getList(null, 1, 10, ""));
    }
}
