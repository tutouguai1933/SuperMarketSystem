package com.djy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("bill")
@KeySequence(value = "SEQ_ORA_INTEGER_KEY_BILL")
public class Bill {
    @TableId(value = "id")
    private Integer id;   //id
    private String billCode; //账单编码
    private String productName; //商品名称
    private String productDesc; //商品描述
    private String productUnit; //商品单位
    private BigDecimal productCount; //商品数量
    private BigDecimal totalPrice; //总金额
    private Integer isPayment; //是否支付
    private Integer providerId; //供应商ID
    private Integer createdBy; //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creationDate; //创建时间
    private Integer modifyBy; //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyDate;//更新时间

    @TableField(exist = false)
    private String providerName;//供应商名称
}
