package com.example.demo.service;

import com.example.demo.entity.Banquan;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-27
 */
public interface IBanquanService extends IService<Banquan> {

    boolean saveXinxi(Banquan banquan);
}
