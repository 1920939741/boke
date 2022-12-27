package com.gyg.mapper;

import com.gyg.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description: TODO
 * @author chengyiyong
 * @date 2022/12/20 11:45
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from m_user where username = #{username}")
    User findByUserName(String username);

    @Select("select * from m_user where email = #{email}")
    User findByEmail(String email);

}
