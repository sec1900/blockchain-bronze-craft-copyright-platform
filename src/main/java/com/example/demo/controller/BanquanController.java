package com.example.demo.controller;


import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Banquan;
import com.example.demo.entity.Miwen;
import com.example.demo.entity.User1;
import com.example.demo.entity.Xinxi;
import com.example.demo.service.IBanquanService;
import com.example.demo.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-07-31
 */
@Controller
@ResponseBody
@RequestMapping("/banquan")
public class BanquanController {

    @Autowired
    private IBanquanService banquanService;

    @GetMapping//查询
    public List<Banquan> findAll() {
        return banquanService.list();
    }

    @GetMapping("/page") //分页查询
    public IPage<Banquan> findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String name) {
        IPage<Banquan> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Banquan> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",name);
        return banquanService.page(page, queryWrapper);
    }

    @DeleteMapping("/{id}")//删除
    public boolean delete(@PathVariable Integer id) {
        return banquanService.removeById(id);
    }

    @PostMapping//更新
    public boolean save(@RequestBody Banquan banquan) {
        if(Objects.equals(banquan.getUrl(), "2")){
            String md5;
            md5 = DigestUtils.md5DigestAsHex(banquan.getName().getBytes(StandardCharsets.UTF_8));
            banquan.setUrl(md5);
        }
        return banquanService.saveXinxi(banquan);
    }

    @GetMapping("/name/{name}")
    public Banquan findOne(@PathVariable String name) {
        QueryWrapper<Banquan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return banquanService.getOne(queryWrapper);
    }

    @GetMapping("/url/{url}")
    public Banquan findOnebyUrl(@PathVariable String url) {
        QueryWrapper<Banquan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url);
        return banquanService.getOne(queryWrapper);
    }

//    @GetMapping("/aes/{data}")
//    public String ase(@PathVariable String data) {
//        String key = "2bc73dw20ebf4d46";
//        String temp = AESUtils.encryptIntoHexString(data,key);
//        System.out.println(temp);
//        return temp;
//    }

    @GetMapping("/aes") //aes加密
    public boolean jiami(@RequestParam String data,
                         @RequestParam String data1) {
        String key = "2bc73dw20ebf4d46";
        String temp = AESUtils.encryptIntoHexString(data,key);
        System.out.print(data);
        System.out.print(temp);
//        System.out.print(data1);
        return Objects.equals(temp, data1);
    }

    @GetMapping("/aes/{data}") //aes解密
    public Miwen  jiemi (@PathVariable String data) {
        String key = "2bc73dw20ebf4d46";
        Miwen miwen = new Miwen();
        miwen.setMingwen(AESUtils.decryptByHexString(data,key));;
        System.out.print(data);
        return miwen;
    }
}

