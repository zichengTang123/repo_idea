package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {

    /*分页查询用户信息*/
    public PageInfo findAllUserByPage(UserVO userVO);

    /*用户登录*/
    public User loginUser(User user) throws Exception;

    /*通过用户id查找role*/
    public List<Role> findUserRoleById(Integer id);

    /*更新user-role-relation*/
    public void userContextRole(UserRoleVO userRoleVO);

    /*获取菜单权限，进行动态展示*/
    ResponseResult getUserPermissions(Integer id);

    /*通过用户id获取资源*/

}
