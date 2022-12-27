package com.gyg;

import com.gyg.entity.User;
import com.gyg.service.ClientUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpringbootVueBlogApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    ClientUserService userService;

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

    /**
     * 邮箱格式校验
     */
    @Test
    public void isEmailCorrect(){
        String email="334363281@qq.com";
        System.out.println(isQQEmailCorrect(email));
    }


    public boolean isQQEmailCorrect(String email) {
//        String regex = "[1-9]\\d{7,9}@(qq|QQ).(COM|com)";
        String regex="([a-z][\\w]{5,17}[@][0-9a-z]{2,10}[\\.](com|cn|net|org|))||(^[1-9]{1}[0-9]{4,14}@(qq|QQ).(COM|com)$)";
        return email.matches(regex);
    }
}
