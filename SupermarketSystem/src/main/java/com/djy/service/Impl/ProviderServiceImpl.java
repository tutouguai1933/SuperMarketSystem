package com.djy.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djy.mapper.BillMapper;
import com.djy.mapper.ProviderMapper;
import com.djy.pojo.Bill;
import com.djy.pojo.Provider;
import com.djy.service.ProviderService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private BillMapper billMapper;

    @Override
    public boolean add(Provider provider) {
        return providerMapper.insert(provider) > 0 ? true : false;
    }

    @Override
    public HashMap<String, Object> getProviderList(String proName, String proCode, int pageIndex, int pageSize) {
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(proName)) {
            queryWrapper.like("proName", proName);
        }
        if (!StringUtils.isNullOrEmpty(proCode)) {
            queryWrapper.eq("proCode", proCode);
        }
        queryWrapper.orderByAsc("id");
        Page<Provider> providerPage = new Page<>(pageIndex, pageSize);
        providerMapper.selectPage(providerPage, queryWrapper);
        List<Provider> providerList = providerPage.getRecords();
        long total = providerPage.getTotal();

        HashMap<String, Object> map = new HashMap<>();
        map.put("providerList", providerList);
        map.put("total", total);

        return map;
    }

    @Override
    public int deleteProviderById(int delId) {
        return providerMapper.deleteById(delId);
    }

    @Override
    public Provider getProviderById(int id) {
        return providerMapper.selectById(id);
    }

    @Override
    public boolean modifyById(Provider provider) {
        return providerMapper.updateById(provider) > 0 ? true : false;
    }
}
