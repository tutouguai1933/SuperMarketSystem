package com.djy.pojo;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
@TableName("message")
@KeySequence(value = "SEQ_ORA_INTEGER_KEY_MESSAGE")
public class Message extends Model<Message> {

    @TableId(value = "id")
    private Long id;
    private String content;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    private Long parentId;
    private Long foreignId;

    @TableField(exist = false)
    private Message parentMessage;

    @TableField(exist = false)
    private String avatar;


}
