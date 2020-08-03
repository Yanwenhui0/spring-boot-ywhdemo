package com.springboot.mybatisplusmore.entity.primary;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/30
 */
@Data
@ToString
public class User {

    @TableId
    private Integer userId;

    private String name;

    private String sex;

    private String tel;
}