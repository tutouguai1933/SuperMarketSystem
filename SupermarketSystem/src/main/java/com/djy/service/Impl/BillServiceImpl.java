package com.djy.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djy.mapper.BillMapper;
import com.djy.mapper.ProviderMapper;
import com.djy.pojo.Bill;
import com.djy.pojo.Provider;
import com.djy.service.BillService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public boolean add(Bill bill) {
        return billMapper.insert(bill) > 0 ? true : false;
    }

    @Override
    public HashMap<String,Object> getBillList(Bill bill ,int pageIndex,int pageSize) {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
            queryWrapper.like("productName",bill.getProductName());
        }
        if (bill.getProviderId() > 0) {
            queryWrapper.eq("providerId",bill.getProviderId());
        }
        if (bill.getIsPayment() > 0) {
            queryWrapper.eq("isPayment",bill.getIsPayment());
        }
        queryWrapper.orderByAsc("id");
        Page<Bill> billPage = new Page<>(pageIndex, pageSize);
        billMapper.selectPage(billPage, queryWrapper);
        List<Bill> billList = billPage.getRecords();
        long total = billPage.getTotal();

        for (Bill bill1 : billList) {
            Provider provider = providerMapper.selectById(bill1.getProviderId());
            bill1.setProviderName(provider.getProName());
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("billList",billList);
        map.put("total",total);

        return map;
    }

    @Override
    public boolean deleteBillById(String delId) {
        return billMapper.deleteById(delId) > 0 ? true : false;
    }

    @Override
    public Bill getBillById(String id) {
        Bill bill = billMapper.selectById(id);

        if(bill!=null){
            Provider provider = providerMapper.selectById(bill.getProviderId());
            if (provider!=null) bill.setProviderName(provider.getProName());
        }
        return bill;
    }

    @Override
    public boolean modifyById(Bill bill) {
        return billMapper.updateById(bill) > 0 ? true : false;
    }
}
