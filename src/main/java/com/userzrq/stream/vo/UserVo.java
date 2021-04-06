package com.userzrq.stream.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserVo {

    @Getter
    private Integer userId;
    @Getter
    private String email;



}
