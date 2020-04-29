package com.wzz.demo.controller;

import com.wzz.demo.pojo.User;
import com.wzz.demo.service.UserService;
import com.wzz.demo.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/queryall")
    public JSONResult queryAllUser() {
        return JSONResult.ok(userService.queryAllUser());
    }

    @RequestMapping("/querybyname")
    public JSONResult queryByName(String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("查询的用户名为空");
        }
        User user = userService.queryUserByName(username);
        if (user != null) {
            return JSONResult.ok(user);
        } else {
            return JSONResult.errorMsg("用户不存在");
        }
    }

    @PostMapping("/add")
    public JSONResult addUser(User user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            JSONResult.errorMsg("用户名或密码不能为空");
        }
        if (userService.queryUserByName(user.getUsername()) != null) {
            JSONResult.errorMsg("用户名已经被注册");
        }
        //插入用户
        //插入用户之前可以对密码进行加密
        userService.addUser(user);
        return JSONResult.ok("用户新增成功");
    }

    @GetMapping("delete")
    public JSONResult deleteUser(String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("删除的用户名为空");
        }
        userService.deleteUser(username);
        return JSONResult.ok("用户删除成功");
    }

    @GetMapping("update")
    public JSONResult deleteUser(User user) {
        userService.updateUser(user);
        return JSONResult.ok("用户更新成功");
    }
}
