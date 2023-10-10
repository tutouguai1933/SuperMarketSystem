package com.djy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Repository
public interface UserMapper extends BaseMapper<User> {
}
