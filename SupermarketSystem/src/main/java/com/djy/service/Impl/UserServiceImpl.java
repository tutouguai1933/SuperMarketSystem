package com.djy.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djy.mapper.RoleMapper;
import com.djy.mapper.UserMapper;
import com.djy.pojo.Role;
import com.djy.pojo.User;
import com.djy.service.UserService;
import com.mysql.jdbc.StringUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User login(String userCode, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userCode", userCode);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updatePwd(int id, String password) {
        User user = userMapper.selectById(id);
        user.setUserPassword(password);
        return userMapper.updateById(user) > 0 ? true : false;
    }

    @Override
    public int getUserCount(String username, int userRole) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            queryWrapper.like("userName", username);
        }
        if (userRole > 0) {
            queryWrapper.eq("userRole", userRole);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        return userList.size();
    }

    @Override
    public HashMap<String, Object> getUserList(String queryUserName, int queryUserRole, int pageIndex, int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(queryUserName)) {
            queryWrapper.like("userName", queryUserName);
        }
        if (queryUserRole > 0) {
            queryWrapper.eq("userRole", queryUserRole);
        }

        queryWrapper.orderByAsc("id");
        Page<User> userPage = new Page<>(pageIndex, pageSize);
        userMapper.selectPage(userPage, queryWrapper);

        List<User> userList = userPage.getRecords();

        long total = userPage.getTotal();

        for (User user : userList) {
            Role role = roleMapper.selectById(user.getUserRole());
            if (role == null) {
                user.setUserRoleName(null);
            } else user.setUserRoleName(role.getRoleName());
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("total", total);

        return map;
    }

    @Override
    public boolean modifyById(User user) {
        return userMapper.updateById(user) > 0 ? true : false;
    }

    @Override
    public boolean add(User user) {
        return userMapper.insert(user) > 0 ? true : false;
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userCode", userCode);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean deleteUserById(Integer delId) {
        return userMapper.deleteById(delId) > 0 ? true : false;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);

        Role role = roleMapper.selectById(user.getUserRole());

        user.setUserRoleName(role.getRoleName());

        return user;
    }
}
