package com.wzz.demo.service.impl;

import com.wzz.demo.mapper.UserMapper;
import com.wzz.demo.pojo.User;
import com.wzz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }
}
