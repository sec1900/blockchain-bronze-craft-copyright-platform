package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Xinxi;
import com.example.demo.mapper.XinxiMapper;
import org.springframework.stereotype.Service;

@Service
public class XinxiService extends ServiceImpl<XinxiMapper,Xinxi> {

    public boolean saveXinxi(Xinxi xinxi) {
        return saveOrUpdate(xinxi);
    }
}
