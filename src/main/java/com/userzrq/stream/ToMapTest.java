package com.userzrq.stream;

import com.userzrq.stream.vo.UserVo;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ToMapTest {

    public static void main(String[] args) {
        UserVo userVo1 = new UserVo(1, "userzrq@126.com");
        UserVo userVo2 = new UserVo(2, "zrquser@126.com");
        UserVo userVo3 = new UserVo(2, "zhangsan@126.com");

        ArrayList<UserVo> users = new ArrayList<>();
        users.add(userVo1);
        users.add(userVo2);
        users.add(userVo3);

        Map<Integer, String> userMap = users.stream().collect(Collectors.toMap(UserVo::getUserId, UserVo::getEmail));

        System.out.println(userMap.toString());
    }
}
