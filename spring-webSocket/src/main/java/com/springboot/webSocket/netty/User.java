package com.springboot.webSocket.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :  yanwenhui
 * @date : 2020/7/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId;
    private String name;
    private String sex;
    private String tel;

    public User(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
