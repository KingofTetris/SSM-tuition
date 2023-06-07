package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */
@Data
@AllArgsConstructor //全参构造
@NoArgsConstructor //无参构造
public class User {

    private Integer id;

    private String username;

    private String password;

    private String gender;

    private Integer age;

    //部分参数构造
    public User(String username, String password, String gender, Integer age) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }
}
