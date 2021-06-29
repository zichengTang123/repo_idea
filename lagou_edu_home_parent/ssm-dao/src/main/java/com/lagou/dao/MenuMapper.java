package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*查询所有的menu*/
    public List<Menu> findMenuListByPid(Integer pid);

    /*通过roleId查找对应的menuID*/
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*查询所有菜单*/
    public List<Menu> findAllMenu();

    /*通过id查询menu*/
    public Menu findMenuById(Integer id);


}
