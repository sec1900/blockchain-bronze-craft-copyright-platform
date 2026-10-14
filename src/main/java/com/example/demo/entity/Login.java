package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-08-03
 */
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String username;

    private String password;

    private String quanxian;

    private String name;

    private String sex;

    private String phone;

    private String place;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getQuanxian() {
        return quanxian;
    }

      public void setQuanxian(String quanxian) {
          this.quanxian = quanxian;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getSex() {
        return sex;
    }

      public void setSex(String sex) {
          this.sex = sex;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }
    
    public String getPlace() {
        return place;
    }

      public void setPlace(String place) {
          this.place = place;
      }

    @Override
    public String toString() {
        return "Login{" +
              "id=" + id +
                  ", username=" + username +
                  ", password=" + password +
                  ", quanxian=" + quanxian +
                  ", name=" + name +
                  ", sex=" + sex +
                  ", phone=" + phone +
                  ", place=" + place +
              "}";
    }
}
