package com.djy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName("provider")
@KeySequence(value = "SEQ_ORA_INTEGER_KEY_PROVIDER")
public class Provider {

    @TableId(value = "id")
    private Integer id;   //id
    private String proCode; //供应商编码
    private String proName; //供应商名称
    private String proDesc; //供应商描述
    private String proContact; //供应商联系人
    private String proPhone; //供应商电话
    private String proAddress; //供应商地址
    private String proFax; //供应商传真
    private Integer createdBy; //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creationDate; //创建时间
    private Integer modifyBy; //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyDate;//更新时间

}
