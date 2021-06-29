package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /*分页查询用户信息*/
    public List<User> findAllUserByPage(UserVO userVO);

    /*用户登录*/
    public User loginUser(User user);

    /*通过用户id查找role*/
    public List<Role> findUserRoleById(Integer id);

    /*通过用户id删除role*/
    public void deleteRoleByUserId(Integer id);

    /*通过roleid和userid来写入数据*/
    public void insertUserRoleRelation(User_Role_relation user_role_relation);

    public List<Role> findUserRelationRoleById(int id);
    /**
     * 根据角色id,查询角色拥有的顶级菜单信息
     * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 根据PID 查询子菜单信息
     * */
    public List<Menu> findSubMenuByPid(int pid);
    /**
     * 获取用户拥有的资源权限信息
     * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
