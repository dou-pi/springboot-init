package com.doupi.springbootinit.controller;


import com.doupi.springbootinit.domain.Result;
import com.doupi.springbootinit.domain.User;
import com.doupi.springbootinit.domain.dto.UserDto;
import com.doupi.springbootinit.service.UserService;

import com.doupi.springbootinit.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("User")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public Result userRegister(@RequestBody UserDto userDto) {
        String userAccount = userDto.getUserAccount();
        String userPassword = userDto.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        long resut = userService.userRegister(userAccount, userPassword);
        return Result.success(resut);
    }

    /**
     * 用户登录
     *
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody UserDto userDto) {
        String userAccount = userDto.getUserAccount();
        String userPassword = userDto.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User resut = userService.userLogin(userAccount, userPassword);
        //登录成功,生成令牌,下发令牌
        if (resut != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", resut.getId());
            claims.put("name", resut.getUserAccount());
            claims.put("username", resut.getUserPassword());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }

        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
    }


}
