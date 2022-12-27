package com.gyg.backstage.controller;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyg.backstage.dto.UserDTO;
import com.gyg.backstage.entity.User;
import com.gyg.backstage.service.IUserService;
import com.gyg.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 凭栏倚,吞云烟! 半入清风半入喉,清风也染人间愁!
 * @since 2022-12-27
 */
@Slf4j
@RestController
@RequestMapping("backstage/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        if (Objects.isNull(userDTO) || StrUtil.isBlank(userDTO.getUsername()) || StrUtil.isBlank(userDTO.getPassword())) {
            return Result.error(500,"参数不能为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        User user = userService.getOne(queryWrapper);
        if (ObjectUtil.isEmpty(user)){
            return Result.error(500,String.format("用户名：[%s],不存在！"));
        }
        if (!userDTO.equals(user.getPassword())){
            return Result.error(500,String.format("密码错误！"));
        }
        return Result.success(200,String.format("尊敬的[%s]用户，欢迎登陆！",user.getName()),user);
    }
}
