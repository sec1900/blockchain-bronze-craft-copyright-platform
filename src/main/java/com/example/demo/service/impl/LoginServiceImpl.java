package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.LoginDTO;
import com.example.demo.entity.Login;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-07-15
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        Login one = getLoginInfo(loginDTO);
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
    public Login register(LoginDTO loginDTO) {
        Login one = getLoginInfo(loginDTO);
        if(one == null){
            one = new Login();
            BeanUtil.copyProperties(loginDTO, one , true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return one;
    }

    @Override
    public boolean saveXinxi(Login login) {
        return saveOrUpdate(login);
    }

    private Login getLoginInfo(LoginDTO loginDTO) {
        QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginDTO.getUsername());
        queryWrapper.eq("password",loginDTO.getPassword());
        Login one;
        try {
            one = getOne(queryWrapper); //数据库查询的数据
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
