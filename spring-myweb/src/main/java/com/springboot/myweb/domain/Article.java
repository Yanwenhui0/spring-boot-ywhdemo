package com.springboot.myweb.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import lombok.*;

/**
@description : 
@author : yanwenhui
@date : 2020/8/11 
*/
@Data
@ToString
@NoArgsConstructor
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
    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Article(int id, String title, String author, String content){
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_AUTHOR = "author";

    public static final String COL_CONTENT = "content";

    public static final String COL_IS_DELETE = "is_delete";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}