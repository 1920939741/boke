package com.gyg;

import com.gyg.entity.User;
import com.gyg.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
