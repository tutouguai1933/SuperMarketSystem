package com.djy.controller.provider;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.djy.common.Result;
import com.djy.mapper.BillMapper;
import com.djy.pojo.Bill;
import com.djy.pojo.Provider;
import com.djy.service.ProviderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    BillMapper billMapper;

    @Autowired
    ProviderService providerService;

    @ApiOperation("根据Id删除provider，需要proId")
    @PostMapping("/delProviderById/{proId}")
    private Result<?> delProviderById(@PathVariable int proId) {
        if (proId > 0) {
            int billCount = -1;

            QueryWrapper<Bill> billQueryWrapper = new QueryWrapper<>();

            billQueryWrapper.eq("providerId",proId);

            List<Bill> bills = billMapper.selectList(billQueryWrapper);

            billCount = bills.size();
            if (billCount == 0) {
                int flag = providerService.deleteProviderById(proId);
                if (flag >= 0) {
                    return Result.success();
                } else {
                    return Result.error("1", "删除失败");
                }
            } else {//该供应商下有订单，不能删除，返回订单数
                return Result.error("1", "不能删除，因为该供应商有对应订单");
            }
        } else {
            return Result.error("1", "proId不合法");
        }
    }


    @ApiOperation("根据Id修改provider，需要provider")
    @PostMapping("/modifyById")
    private Result<?> modifyById(@RequestBody Provider provider) {
        boolean flag = providerService.modifyById(provider);
        if (flag) {
            return Result.success();
        } else {
            return Result.error("1", "修改失败");
        }
    }

    @ApiOperation("根据id获取provider，需要proId")
    @PostMapping("/getProviderById/{proId}")
    private Result<?> getProviderById(@PathVariable int proId) {
        if (proId > 0) {
            Provider provider = providerService.getProviderById(proId);
            return Result.success(provider);
        } else {
            return Result.error("1", "proId不合法");
        }
    }

    @ApiOperation("添加provider，需要provider")
    @PostMapping("/add")
    private Result<?> add(@RequestBody Provider provider) {
        provider.setId(null);
        boolean flag = providerService.add(provider);
        if (flag) {
            return Result.success();
        } else {
            return Result.error("1", "添加失败");
        }
    }

    @ApiOperation("特殊查询，可选proName，proCode")
    @PostMapping("/query/{pageIndex}/{pageSize}")
    private Result<?> query(@RequestBody Provider provider,@PathVariable int pageIndex,@PathVariable int pageSize) {
        String proName = provider.getProName();
        String proCode = provider.getProCode();
        if (proName == null || proName.equals("string")) {
            proName = "";
        }
        if (proCode == null || proCode.equals("string")) {
            proCode = "";
        }
        HashMap<String, Object> map = providerService.getProviderList(proName, proCode, pageIndex, pageSize);
        return Result.success(map);
    }

}
