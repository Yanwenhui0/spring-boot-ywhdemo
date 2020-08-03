package com.springboot.springdatamore.entity.primary;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/29
 */
@Data
@ToString
@Entity
public class User {

    @Id
    private Integer userId;

    private String name;

    private String sex;

    private String tel;
}
