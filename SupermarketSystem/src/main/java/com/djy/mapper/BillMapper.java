package com.djy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djy.pojo.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;


@Repository
public interface BillMapper extends BaseMapper<Bill> {

}
