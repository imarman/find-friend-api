package com.arman.findmyfriend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.arman.findmyfriend.entity.User;
import com.arman.findmyfriend.service.UserService;
import com.arman.findmyfriend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




