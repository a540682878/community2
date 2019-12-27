package com.xcy.community2.model;

import lombok.Data;

@Data
public class Question {
    //id
    private Integer id;
    //标题
    private String title;
    //问题描述
    private String description;
    //创建时间
    private Long gmtCreat;
    //修改时间
    private Long gmtModified;
    //创建者
    private Integer creator;
    //回复数
    private Integer commentCount;
    //阅读数
    private Integer viewCount;
    //关注数
    private Integer likeCount;
    //标签
    private String tag;

}
