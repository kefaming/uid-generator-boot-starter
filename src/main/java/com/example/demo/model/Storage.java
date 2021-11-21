package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "t_storage")
public class Storage extends Model<Storage> implements Serializable {

    private static final long serialVersionUID = 3384226332070584947L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String commodityCode;
    private String name;
    private Integer count;
}
