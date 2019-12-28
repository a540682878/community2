package com.xcy.community2.model;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private Long accountId;
    private String name;
    private String token;
    private Long gmtCreat;
    private Long gmtModified;
    private String avatarUrl;

}
