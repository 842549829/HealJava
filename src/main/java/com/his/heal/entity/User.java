package com.his.heal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("abpusers")
@Data
public class User {
    @TableId
    private String id;
    @TableField("UserName")
    private String userName;
}
