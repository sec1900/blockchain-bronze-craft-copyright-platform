package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.LoginDTO;
import com.example.demo.entity.Login;
import com.example.demo.entity.User1;
import com.example.demo.entity.Xinxi;
import com.example.demo.service.ILoginService;
import com.example.demo.utils.TokenUtils;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-07-15
 */
@Controller
@ResponseBody
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        LoginDTO dto = loginService.login(loginDTO);
        return Result.success(dto);
    }

    @GetMapping//查询
    public List<Login> findAll() {
        return loginService.list();
    }

    @GetMapping("/page") //分页查询
    public IPage<Login> findPage(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(defaultValue = "") String name) {
        IPage<Login> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return loginService.page(page, queryWrapper);
    }

    @PostMapping//更新
    public boolean save(@RequestBody Login login) {
        return loginService.saveXinxi(login);
    }

    @GetMapping("/username/{username}")
    public Login findOne(@PathVariable String username) {
        QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return loginService.getOne(queryWrapper);
    }
}

