package com.djy.service.Impl;

import com.djy.mapper.RoleMapper;
import com.djy.pojo.Role;
import com.djy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> getRoleList() {
        return roleMapper.selectList(null);
    }


}
