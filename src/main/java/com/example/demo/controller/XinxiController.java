package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Xinxi;

import com.example.demo.service.XinxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/xinxi")
public class    XinxiController {


    @Autowired
    private XinxiService xinxiService;

    @GetMapping//查询
    public List<Xinxi> findAll() {
        return xinxiService.list();
    }

    @PostMapping//更新
    public boolean save(@RequestBody Xinxi xinxi) {
        return xinxiService.saveXinxi(xinxi);
    }

    @DeleteMapping("/{id}")//删除
    public boolean delete(@PathVariable Integer id) {
       return xinxiService.removeById(id);
    }

    @GetMapping("/page") //分页查询
    public IPage<Xinxi> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String name) {
        IPage<Xinxi> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Xinxi> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return xinxiService.page(page, queryWrapper);
    }
}
