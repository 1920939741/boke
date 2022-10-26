package com.gyg.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyg.common.lang.Result;
import com.gyg.entity.Blog;
import com.gyg.service.BlogService;
import com.gyg.shiro.AccountProfile;
import com.gyg.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author 关注公众号：码猿编程日记
 * @since 2021-09-21
 */
@RestController
//@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    /**
     * 分页博客页
     *
     * @param currentPage
     * @return
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);

        AccountProfile accountProfile =  (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        System.out.println(accountProfile);

        IPage<Blog> pageDate = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.success(pageDate);
    }

    /**
     * 查找指定的博客
     *
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") long id) {

        Blog blog = blogService.getById(id);
//        用断言来来判断文章是否找不到
        Assert.notNull(blog, "该博客已经被删除！");

//        返回该博客数据
        return Result.success(blog);
    }

    /**
     * @param blog
     * @return
     */
//    只有登录之后才能编辑
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        System.out.println("编辑测试11111111111111111");
        System.out.println(blog.toString());
        System.out.println("当前用户ID：" + ShiroUtil.getProfile().getId());
        System.out.println(blog.toString());
//        System.out.println("当前用户id：" + ShiroUtil.getSubjectID());

        Blog temp = null;
        //      如果博客id不为空，就是编辑
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
//            每一个用户只能编辑自己的文章
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()), "你没有权限编辑");

        } else {
            //      如果id为空，就是添加
            temp = new Blog();
//            将这篇文章添加给当前用户的id
            temp.setUserId(ShiroUtil.getProfile().getId());
//            博客创建时间
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        //  将两个对象进行复制，指定那些字段不复制
        //BeanUtil.copyProperties("转换前的类","转换后的类");
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");

        //保存或者更新这一篇文章
        blogService.saveOrUpdate(temp);

        return Result.success("操作成功");
    }

    /**
     * 根据博客ID删除博客
     * @param id
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/blog/delete/{id}")
    public Result deleteBlog(@PathVariable("id") long id){
        System.out.println(id);
        System.out.println("------------");
//        int bid = Integer.parseInt(id);
        boolean isRemove = blogService.removeById(id);
        if (!isRemove){
            return Result.fail("删除失败！");
        }
        return Result.success("删除成功！");
    }

}
