package com.gyg.service.impl;

import com.gyg.entity.User;
import com.gyg.mapper.UserMapper;
import com.gyg.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author 关注公众号：码猿编程日记
 * @since 2021-09-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
