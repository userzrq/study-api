package com.userzrq.reflection.annotaionDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor  // 用于反射getInstance是使用的无参构造器
public class User {

    private Long id;

    private String username;

    private String password;

    private List<String> permissions;

}
