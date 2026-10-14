package com.example.demo.service.impl;

import com.example.demo.entity.Shensu;
import com.example.demo.mapper.ShensuMapper;
import com.example.demo.service.IShensuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-04
 */
@Service
public class ShensuServiceImpl extends ServiceImpl<ShensuMapper, Shensu> implements IShensuService {

    @Override
    public boolean saveXinxi(Shensu shensu) {
        return saveOrUpdate(shensu);
    }
}
