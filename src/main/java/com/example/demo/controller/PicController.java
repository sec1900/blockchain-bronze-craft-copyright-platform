package com.example.demo.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Files;
import com.example.demo.entity.Pic;
import com.example.demo.mapper.PicMapper;
import com.example.demo.utils.Calculate;
import com.example.demo.utils.PearsonDemo;
import com.example.demo.utils.PhotoDigest;
import com.fasterxml.jackson.core.sym.Name1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@ResponseBody
@RequestMapping("/file1")
public class PicController {

    @Value("${files.upload.path1}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    @Resource
    private PicMapper picMapper;

    private String data1;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        this.data1 = fileUUID;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 从数据库查询是否存在相同的记录
        Pic dbFiles = getFileByMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://" + serverIp + ":9090/file1/" + fileUUID;
        }


        // 存储数据库
        Pic saveFile = new Pic();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024); // 单位 kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        saveFile.setName1(fileUUID);
        picMapper.insert(saveFile);
        return url;
    }

    private Pic getFileByMd5(String md5) {
        // 查询文件的md5是否存在
        QueryWrapper<Pic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Pic> filesList = picMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    @GetMapping
    private Pic findAll() throws IOException {
        List<Pic> list =  picMapper.selectList(null);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("Name1",this.data1);
        List<Pic> stu = picMapper.selectByMap(map);
        System.out.println(list.get(0).getName1());
        System.out.println(this.data1);
        final String pic1Path = "/D:/deno2/files1/"+this.data1;
        final List<Double> origin = Calculate.getPicArrayData(pic1Path);
        System.out.println(origin);
        double percent = 0;
        for(int i=0 ; i < list.size() ; i++){
            if(!Objects.equals(this.data1, list.get(i).getName1())){
                final String pic2Path = "/D:/deno2/files1/"+list.get(i).getName1();
                final List<Double> after = Calculate.getPicArrayData(pic2Path);
                System.out.println(after);
                percent = PearsonDemo.getPearsonBydim(origin, after);
                System.out.println(percent);
                if(percent >= 0.8){
                    picMapper.deleteById(stu.get(0).getId());
                    return list.get(i);
                }
            }
        }
        System.out.println("处理完毕");
        return null;
    }

    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {

        QueryWrapper<Pic> queryWrapper = new QueryWrapper<>();
        // 查询未删除的记录
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(picMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
