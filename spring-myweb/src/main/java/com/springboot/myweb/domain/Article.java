package com.springboot.myweb.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import lombok.Data;

/**
@description : 
@author : yanwenhui
@date : 2020/8/11 
*/
@Data
@TableName(value = "article")
public class Article {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String author;

    private String content;

    /**
     * 逻辑删除
     */
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_AUTHOR = "author";

    public static final String COL_CONTENT = "content";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}