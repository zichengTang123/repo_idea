package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenuListByPid(Integer pid) {
        List<Menu> menuList = menuMapper.findMenuListByPid(pid);
        return menuList;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> list = menuMapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public PageInfo<Menu> findAllMenu(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Menu> menuList = menuMapper.findAllMenu();
        PageInfo<Menu> pageInfo = new PageInfo<>(menuList);
        return pageInfo;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }
}
