package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVO);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public User loginUser(User user) throws Exception {
        User user1 = userMapper.loginUser(user);
        if(user1.getPassword() == null){
            return null;
        } else{
            boolean verify = Md5.verify(user.getPassword(), "lagou", user1.getPassword());
            if(verify){
                return user1;
            } else{
                return null;
            }
        }
    }

    @Override
    public List<Role> findUserRoleById(Integer id) {
        List<Role> roleList = userMapper.findUserRoleById(id);
        return roleList;
    }

    @Override
    public void userContextRole(UserRoleVO userRoleVO) {
        userMapper.deleteRoleByUserId(userRoleVO.getUserId());
        User_Role_relation user_role_relation = new User_Role_relation();
        for (Integer roleId : userRoleVO.getRoleIdList()) {
            user_role_relation.setRoleId(roleId);
            user_role_relation.setUserId(userRoleVO.getUserId());
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            userMapper.insertUserRoleRelation(user_role_relation);
        }
    }


    @Override
    public ResponseResult getUserPermissions(Integer id) {
//1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
//2.获取角色ID,保存到 list
        List<Integer> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(role.getId());
        }

        if(list.isEmpty()){
            ResponseResult result = new ResponseResult(true,200,"该用户无角色",null);
            return result;
        } else {
            //3.根据角色id查询 父菜单
            List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);
//4.封装父菜单下的子菜单Web层：UserController
            for (Menu menu : parentMenu) {
                List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
                menu.setSubMenuList(subMenu);
            }
            //5.获取资源权限
            List<Resource> resourceList = userMapper.findResourceByRoleId(list);
//6.封装数据
            Map<String,Object> map = new HashMap<>();
            map.put("menuList",parentMenu); //menuList: 菜单权限数据
            map.put("resourceList",resourceList);//resourceList: 资源权限数据
            ResponseResult result = new ResponseResult(true,200,"响应成功",map);
            return result;
        }
    }

}
