package com.arman.findmyfriend.controller;

import com.arman.findmyfriend.common.BaseResponse;
import com.arman.findmyfriend.common.ErrorCode;
import com.arman.findmyfriend.common.ResultUtils;
import com.arman.findmyfriend.constant.UserConstant;
import com.arman.findmyfriend.model.entity.Tag;
import com.arman.findmyfriend.model.entity.User;
import com.arman.findmyfriend.exception.BusinessException;
import com.arman.findmyfriend.model.req.UserLoginRequest;
import com.arman.findmyfriend.model.req.UserRegisterRequest;
import com.arman.findmyfriend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Arman
 * @date 2022/9/24 00:57
 */
@RestController
@RequestMapping("user")
public class UserController {


    @Resource
    UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        if (Objects.isNull(user)) {
            throw new BusinessException(ErrorCode.NO_FIND_USER);
        }
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {

        User currentUser = userService.getLoginUser(request);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        // TODO 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }


    @GetMapping("/getUsersByTags")
    public BaseResponse<List<User>> getUserByTags(@RequestParam(required = false) List<String> tags) {
        if (CollectionUtils.isEmpty(tags)) throw new BusinessException(ErrorCode.PARAMS_ERROR);

        return ResultUtils.success(userService.searchUsersByTagsMemory(tags));
    }

    @PutMapping("/update")
    public BaseResponse<Integer> updateUser(HttpServletRequest request, @RequestBody User user) {
        // 校验参数
        if (Objects.isNull(user) || Objects.isNull(user.getId())) throw new BusinessException(ErrorCode.PARAMS_ERROR);

        User loginUser = userService.getLoginUser(request);


        return ResultUtils.success(userService.updateUser(loginUser, user));
    }

}
