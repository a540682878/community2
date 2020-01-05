package com.xcy.community2.model;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreat;
    private Long gmtModified;
    private Long likeCount;
}
