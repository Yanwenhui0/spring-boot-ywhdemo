package com.springboot.swagger.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/24
 */
@Data
@AllArgsConstructor
public class User {

    private String name;
    private String password;

}
