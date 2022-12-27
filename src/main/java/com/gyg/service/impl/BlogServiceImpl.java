package com.gyg.service.impl;

import com.gyg.entity.Blog;
import com.gyg.mapper.BlogMapper;
import com.gyg.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author chengyiyong
 * @date 2022/12/20 11:01
 * @version 1.0
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
