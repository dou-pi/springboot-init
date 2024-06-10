package com.doupi.springbootinit.domain.dto;

import lombok.Data;

@Data
public class UserDto {

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
