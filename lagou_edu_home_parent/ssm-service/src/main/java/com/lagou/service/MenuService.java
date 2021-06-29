package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    /*查询所有的menu*/
    public List<Menu> findMenuListByPid(Integer pid);

    /*通过roleId查找对应的menu*/
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*查询所有菜单*/
    public PageInfo<Menu> findAllMenu(Integer currentPage, Integer pageSize);

    /*通过id查询menu*/
    public Menu findMenuById(Integer id);
}
