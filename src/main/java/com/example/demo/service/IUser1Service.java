package com.example.demo.service;

import com.example.demo.controller.dto.LoginDTO;
import com.example.demo.entity.User1;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-07-31
 */
public interface IUser1Service extends IService<User1> {

    LoginDTO login(LoginDTO loginDTO);


    User1 register(LoginDTO loginDTO);

    boolean saveXinxi(User1 user1);
}
