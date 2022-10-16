package com.gyg.mapper;

import com.gyg.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * InnoDB free: 11264 kB Mapper 接口
 * </p>
 *
 * @author 关注公众号：码猿编程日记
 * @since 2021-09-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from m_user where username = #{username}")
    User findByUserName(String username);

    @Select("select * from m_user where email = #{email}")
    User findByEmail(String email);

}
