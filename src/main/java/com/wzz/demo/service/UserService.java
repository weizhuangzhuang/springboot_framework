package com.wzz.demo.service;

import com.wzz.demo.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUser();

    /**
     * 通过用户名查询用户
      * @param username
     * @return
     */
    User queryUserByName(String username);

    /**
     * 通过用户id查询用户
     * @param userId
     * @return
     */
    User queryUserById(String userId);

    /**
     * 新增用户
     * @param user
     */
    void addUser(User user);

    /**
     * 删除用户
     * @param username
     */
    void deleteUser(String username);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

}
