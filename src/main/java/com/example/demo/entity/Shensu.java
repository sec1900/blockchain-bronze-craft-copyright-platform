package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author baomidou
 * @since 2022-10-04
 */
public class Shensu implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String username;

    private String name;

    private String type;

    private String time;

    private String url;

      /**
     * 介绍
     */
      private String introduce;

      /**
     * 高
     */
      private String height;

      /**
     * 重
     */
      private String weight;

      /**
     * 长
     */
      private String length;

      /**
     * 宽
     */
      private String width;


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

    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }

    public String getType() {
        return type;
    }

      public void setType(String type) {
          this.type = type;
      }

    public String getTime() {
        return time;
    }

      public void setTime(String time) {
          this.time = time;
      }

    public String getUrl() {
        return url;
    }

      public void setUrl(String url) {
          this.url = url;
      }

    public String getIntroduce() {
        return introduce;
    }

      public void setIntroduce(String introduce) {
          this.introduce = introduce;
      }

    public String getHeight() {
        return height;
    }

      public void setHeight(String height) {
          this.height = height;
      }

    public String getWeight() {
        return weight;
    }

      public void setWeight(String weight) {
          this.weight = weight;
      }

    public String getLength() {
        return length;
    }

      public void setLength(String length) {
          this.length = length;
      }

    public String getWidth() {
        return width;
    }

      public void setWidth(String width) {
          this.width = width;
      }

    @Override
    public String toString() {
        return "Shensu{" +
              "id=" + id +
                  ", username=" + username +
                  ", name=" + name +
                  ", type=" + type +
                  ", time=" + time +
                  ", url=" + url +
                  ", introduce=" + introduce +
                  ", height=" + height +
                  ", weight=" + weight +
                  ", length=" + length +
                  ", width=" + width +
              "}";
    }
}
