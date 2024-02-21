package com.kjq.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String userAccount;
    private String avatarUrl;
    private Integer gender;
    private String userPassword;
    private Integer userStatus;
    private String createTime;
    private Integer userRole;
}
