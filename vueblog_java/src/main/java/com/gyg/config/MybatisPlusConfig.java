package com.gyg.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatisPlus配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.gyg.mapper")   //指定变成实现类的接口所在的包
public class MybatisPlusConfig {

    /**
     * 实现一个分页插件PaginationInterceptor
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
