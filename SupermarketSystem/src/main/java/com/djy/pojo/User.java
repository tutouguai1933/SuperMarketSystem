package com.djy.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("\"USER\"")
@KeySequence(value = "SEQ_ORA_INTEGER_KEY_USER")
public class User {
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id; //id
    private String userCode; //用户编码
    private String userName; //用户名称
    private String userPassword; //用户密码
    private Integer gender;  //性别
    private Date birthday;  //出生日期
    private String phone;   //电话
    private String address; //地址
    private Integer userRole;    //用户角色
    private Integer createdBy;   //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creationDate; //创建时间
    private Integer modifyBy;     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyDate;   //更新时间

    @TableField(exist = false)
    private Integer age;//年龄
    @TableField(exist = false)
    private String userRoleName;    //用户角色名称

    public Integer getAge() {
        if(birthday!=null){
            Date date = new Date();
            Integer age = date.getYear() - birthday.getYear();
            return age;
        }else {
            return null;
        }

    }
}
