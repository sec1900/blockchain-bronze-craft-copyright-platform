package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.controller.dto.LoginDTO;
import com.example.demo.entity.Login;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-07-15
 */
public interface ILoginService extends IService<Login> {

    LoginDTO login(LoginDTO loginDTO);

    Login register(LoginDTO loginDTO);

    boolean saveXinxi(Login login);
}
