package com.gyg;

import com.gyg.entity.User;
import com.gyg.service.UserService;
import com.gyg.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpringbootVueBlogApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("测试");
        System.out.println(dataSource.getConnection());
        User user = userService.getById(1L);
        System.out.println(user);
    }




    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest() {
        redisTemplate.opsForValue().set("name", "张三");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
