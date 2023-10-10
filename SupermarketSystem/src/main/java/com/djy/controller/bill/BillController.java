package com.djy.controller.bill;

import com.djy.common.Result;
import com.djy.mapper.ProviderMapper;
import com.djy.pojo.Bill;
import com.djy.pojo.Provider;
import com.djy.service.BillService;
import com.djy.service.ProviderService;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private BillService billService;

    @Autowired
    ProviderMapper providerMapper;

    @ApiOperation("获取全部的供应商列表，不需要传值")
    @PostMapping("/getProviderList")
    private Result<?> getProviderlist() {
        List<Provider> providerList = new ArrayList<Provider>();
        providerList = providerMapper.selectList(null);
        return Result.success(providerList);
    }

    @ApiOperation("根据Id获取bill，需要billId")
    @PostMapping("/getBillById/{billId}")
    private Result<?> getBillById(@PathVariable String billId) {
        if (!StringUtils.isNullOrEmpty(billId)) {
            Bill bill = billService.getBillById(billId);
            return Result.success(bill);
        } else {
            return Result.error("1", "id为空");
        }
    }

    @ApiOperation("根据Id修改bill，需要bill")
    @PostMapping("/modifyById")
    private Result<?> modifyById(@RequestBody Bill bill) {
        boolean flag = billService.modifyById(bill);
        return Result.success(flag);
    }

    @ApiOperation("根据Id删除bill，需要billId")
    @PostMapping("/delBill/{billId}")
    private Result<?> delBill(@PathVariable String billId) {
        if (!StringUtils.isNullOrEmpty(billId)) {
            boolean flag = billService.deleteBillById(billId);
            if (flag) {//删除成功
                return Result.success();
            } else {//删除失败
                return Result.error("1","删除失败");
            }
        } else {
            return Result.error("1","Id为空");
        }
    }

    @ApiOperation("插入bill，需要bill的一部分属性，不需要id")
    @PostMapping("/add")
    private Result<?> add(@RequestBody Bill bill) {
        bill.setId(null);
        boolean flag = billService.add(bill);
        if (flag) {
            return Result.success();
        }else{
            return Result.error("1","添加失败");
        }
    }

    @ApiOperation("特殊查询bill，可选isPayment，ProductName，providerId,需要pageIndex,pageSize")
    @PostMapping("/query/{pageIndex}/{pageSize}")
    private Result<?> query(@RequestBody Bill bill ,@PathVariable int pageIndex,@PathVariable int pageSize) {
        if (bill.getProductName()==null||bill.getProductName().equals("string")){
            bill.setProductName("");
        }
        if(bill.getIsPayment()==null) bill.setIsPayment(0);
        if(bill.getProviderId()==null) bill.setProviderId(0);
        HashMap<String, Object> map = billService.getBillList(bill, pageIndex, pageSize);
        return Result.success(map);
    }
}
