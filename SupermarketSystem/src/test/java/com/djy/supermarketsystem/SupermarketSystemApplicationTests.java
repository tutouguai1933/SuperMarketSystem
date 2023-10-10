package com.djy.supermarketsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djy.mapper.UserMapper;
import com.djy.pojo.User;
import com.mysql.jdbc.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SupermarketSystemApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void Test() throws Exception {
        String username = "";
        int userRole =0;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            queryWrapper.like("userName", username);
        }
        if (userRole > 0) {
            queryWrapper.eq("userRole", userRole);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
