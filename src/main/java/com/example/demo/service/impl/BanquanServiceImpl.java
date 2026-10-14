package com.example.demo.service.impl;

import com.example.demo.entity.Banquan;
import com.example.demo.mapper.BanquanMapper;
import com.example.demo.service.IBanquanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-09-27
 */
@Service
public class BanquanServiceImpl extends ServiceImpl<BanquanMapper, Banquan> implements IBanquanService {

    @Override
    public boolean saveXinxi(Banquan banquan) {
        return saveOrUpdate(banquan);
    }
}
