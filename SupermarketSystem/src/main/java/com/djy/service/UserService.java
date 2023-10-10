package com.djy.service;

import com.djy.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode, String password);

    //根据用户ID修改密码
    public boolean updatePwd(int id,String password);

    //根据用户名或者角色查询用户总数
    public int getUserCount(String username,int userRole);

    public HashMap<String,Object> getUserList(String queryUserName, int queryUserRole, int pageIndex, int pageSize);

    public boolean modifyById(User user);

    public boolean add(User user);

    public User selectUserCodeExist(String userCode);

    public boolean deleteUserById(Integer delId);

    public User getUserById(Integer id);
}
