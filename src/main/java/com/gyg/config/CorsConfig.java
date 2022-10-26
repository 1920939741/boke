package com.gyg.config;

import com.gyg.core.interceptor.FirstInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor firstInterceptor = new FirstInterceptor();
        registry.addInterceptor(firstInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/toolFtl", "/login/**", "/css/**", "/js/**", "/jsp/**", "/ftl/**",
                        "/images/**", "/file/**", "/audio/**", "/html/**", "/servlet/**", "/error", "/**/**.apk");

//        HandlerInterceptor interceptor = new AuthAdapter();
//        InterceptorRegistration registration = registry.addInterceptor(interceptor);
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns("/toolFtl", "/login/**", "/css/**", "/js/**", "/jsp/**", "/ftl/**",
//                "/images/**", "/file/**", "/audio/**", "/html/**", "/servlet/**", "/error", "/**/**.apk","/pacSkuInfo/**");
    }

}
