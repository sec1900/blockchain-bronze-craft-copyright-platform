package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.LoginDTO;
import com.example.demo.entity.User1;
import com.example.demo.service.IUser1Service;
import com.example.demo.utils.suijishu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-07-31
 */
@Controller
@ResponseBody
@RequestMapping("/user1")
public class User1Controller {

    @Autowired
    private IUser1Service user1Service;

    private int random;
    private String phone;

    @GetMapping("/page") //分页查询
    public IPage<User1> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String name) {
        IPage<User1> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User1> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return user1Service.page(page, queryWrapper);
    }
    //    用户登录
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        LoginDTO dto = user1Service.login(loginDTO);
        return Result.success(dto);
    }

    //    用户注册
    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(user1Service.register(loginDTO));
    }

    @PostMapping//更新
    public boolean save(@RequestBody User1 user1) {
        return user1Service.saveXinxi(user1);
    }

    @GetMapping("/username/{username}")
    public User1 findOne(@PathVariable String username) {
        QueryWrapper<User1> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return user1Service.getOne(queryWrapper);
    }

    @GetMapping("/getphone")
    public boolean getphone(@RequestParam String phone) {
        suijishu r = new suijishu();
        this.random = r.getRandom();
        this.phone = phone;
        return true;
    }

    @GetMapping("/phone")
    public boolean test(@RequestParam String phone,
                        @RequestParam int random) {
        if(Objects.equals(this.phone, phone) && this.random == random){
            return true;
        }
        return false;
    }
}

