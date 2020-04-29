package com.wzz.demo.controller;

import com.wzz.demo.annotation.UserLoginToken;
import com.wzz.demo.pojo.User;
import com.wzz.demo.service.TokenService;
import com.wzz.demo.service.UserService;
import com.wzz.demo.utils.JSONResult;
import com.wzz.demo.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/login")
    public JSONResult login(User user , HttpServletResponse response){
        //System.out.println(user.getUsername() + "==" + user.getPassword());
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        User user1 = userService.queryUserByName(user.getUsername());
        if(user1 == null){
            return JSONResult.errorMsg("用户不存在");
        }
        if(StringUtils.equals(user.getPassword(),user1.getPassword())){
            String token = tokenService.getToken(user1);
            Cookie cookie = new Cookie("token" , token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return JSONResult.build(200,"登录成功，返回token", token);
        }else{
            return JSONResult.errorMsg("密码错误");
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public JSONResult getMessage(){
        return JSONResult.ok(TokenUtil.getTokenUserId());
    }

    @GetMapping("/getMessage1")
    public JSONResult getMessage1(){
        return JSONResult.ok("测试");
    }
}
