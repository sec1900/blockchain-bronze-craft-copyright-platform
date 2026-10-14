package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.LoginDTO;
import com.example.demo.entity.Login;
import com.example.demo.entity.User1;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.User1Mapper;
import com.example.demo.service.IUser1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-07-31
 */
@Service
public class User1ServiceImpl extends ServiceImpl<User1Mapper, User1> implements IUser1Service {

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        User1 one = getLoginInfo(loginDTO);
        if(one != null) {
            BeanUtil.copyProperties(one, loginDTO, true);
            String token = TokenUtils.genToker(one.getId().toString(), one.getPassword());
            loginDTO.setToken(token);
            return loginDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User1 register(LoginDTO loginDTO) {
        User1 one = getLoginInfo(loginDTO);
        if(one == null){
            one = new User1();
            BeanUtil.copyProperties(loginDTO, one , true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return one;
    }

    @Override
    public boolean saveXinxi(User1 user1) {
        return saveOrUpdate(user1);
    }


    private User1 getLoginInfo(LoginDTO loginDTO) {
        QueryWrapper<User1> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginDTO.getUsername());
        queryWrapper.eq("password",loginDTO.getPassword());
        User1 one;
        try {
            one = getOne(queryWrapper); //数据库查询的数据
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
