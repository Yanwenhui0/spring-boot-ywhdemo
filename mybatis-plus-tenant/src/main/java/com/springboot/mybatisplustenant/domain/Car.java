package com.springboot.mybatisplustenant.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/1
 */
@Data
@Accessors(chain = true)
@TableName(value = "car")
public class Car implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "car_name")
    private String carName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CAR_NAME = "car_name";
}
