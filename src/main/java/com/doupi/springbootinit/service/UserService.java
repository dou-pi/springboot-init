package com.doupi.springbootinit.service;

import com.doupi.springbootinit.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author doupi
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-06-09 19:13:01
*/
public interface UserService extends IService<User> {


    Long userRegister(String userAccount, String userPassword);

    User userLogin(String userAccount, String userPassword);
}
