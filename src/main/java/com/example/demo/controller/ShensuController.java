package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Banquan;
import com.example.demo.entity.Shensu;
import com.example.demo.service.IShensuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-08-30
 */
@Controller
@ResponseBody
@RequestMapping("/shensu")
public class ShensuController {

    @Autowired
    private IShensuService shensuService;

    @GetMapping("/page") //分页查询
    public IPage<Shensu> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String name) {
        IPage<Shensu> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Shensu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",name);
        return shensuService.page(page, queryWrapper);
    }

    @PostMapping//更新
    public boolean save(@RequestBody Shensu shensu) {
        return shensuService.saveXinxi(shensu);
    }

    @DeleteMapping("/{id}")//删除
    public boolean delete(@PathVariable Integer id) {
        return shensuService.removeById(id);
    }

}

