package com.wzz.demo.service.impl;

import com.wzz.demo.mapper.UserMapper;
import com.wzz.demo.pojo.User;
import com.wzz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserByName(String username) {
        Example userExample = new Example(User.class);
        Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username" , username);
        return userMapper.selectOneByExample(userExample);
    }

    @Override
    public User queryUserById(String userId) {
        Example userExample = new Example(User.class);
        Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("userId" , userId);
        return userMapper.selectOneByExample(userExample);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addUser(User user) {
        String userId = UUID.randomUUID().toString().replace("-","");
        user.setUserId(userId);
        userMapper.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUser(String username) {
        userMapper.delete(queryUserByName(username));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
