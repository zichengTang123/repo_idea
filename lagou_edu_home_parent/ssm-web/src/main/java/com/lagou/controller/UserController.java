package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询用户成功", pageInfo);
        return responseResult;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user1 = userService.loginUser(user);
        ResponseResult responseResult = null;
        if(user1 != null){
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());
            map.put("user", user1);
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user1.getId());
            session.setAttribute("access_token", access_token);
            responseResult = new ResponseResult(true,1,"响应成功",map);
        } else {
            responseResult = new ResponseResult(true,400,"用户名密码错误",null);
        }
        return responseResult;
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> roleList = userService.findUserRoleById(id);
        return new ResponseResult(true, 200, "角色回显成功", roleList);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserRoleVO userRoleVO){
        userService.userContextRole(userRoleVO);
        return new ResponseResult(true, 200, "修改成功", null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的 token
        String token = request.getHeader("Authorization");
//获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");
//判断
        if(token.equals(access_token)){
            int user_id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else{
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }
    }
}
