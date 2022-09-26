package com.arman.findmyfriend.service;

import com.arman.findmyfriend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Arman
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param planetCode    星球编号
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签所有用户
     * @param tags
     * @return
     */
    List<User> searchUsersByTagsSQL(List<String> tags);

    /**
     * 根据标签所有用户
     * @param tags
     * @return
     */
    List<User> searchUsersByTagsMemory(List<String> tags);

    /**
     * 跟新用户信息
     *
     * @param loginUser
     * @param user
     */
    int updateUser(User loginUser, User user);

    /**
     * 获取当前登录用户信息
     * @param request
     */
    User getLoginUser(HttpServletRequest request);


    /**
     * 是否为管理员
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    boolean isAdmin(User user);
}
