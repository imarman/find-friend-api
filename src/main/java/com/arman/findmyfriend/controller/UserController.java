package com.arman.findmyfriend.controller;

import com.arman.findmyfriend.entity.User;
import com.arman.findmyfriend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Arman
 * @date 2022/9/24 00:57
 */
@RestController
@RequestMapping
public class UserController {


    @Resource
    UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

}
