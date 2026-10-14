package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Xinxi;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface XinxiMapper extends BaseMapper<Xinxi> {

//    @Select("SELECT * FROM user")
//    List<Xinxi> findAll();
//
//    @Insert("insert into user(name,geade,num,doctor,main,year,country,typr) VALUES (#{name},#{geade},#{num},#{doctor},#{main},#{year},#{country},#{typr})")
//    int insert(Xinxi xinxi);
//
//    int updata(Xinxi xinxi);
//
//    @Update("delete from user where id = #{id}}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from user where name like #{name} limit #{pageNum},#{pageSize}")
//    List<Xinxi> selectPage(Integer pageNum, Integer pageSize, String name);
//
//    @Select("select count(*) from user where name like #{name} ")
//    Integer selectTotal(String name);
}
