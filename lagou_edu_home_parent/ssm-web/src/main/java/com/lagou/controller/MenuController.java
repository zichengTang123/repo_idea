package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(@RequestParam Integer currentPage, Integer pageSize){
        PageInfo<Menu> pageInfo = menuService.findAllMenu(currentPage, pageSize);
        return new ResponseResult(true, 200, "查询menu成功", pageInfo);
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        if(id == -1){
            //新增操作
            List<Menu> menuList = menuService.findMenuListByPid(id);
            for (Menu menu : menuList) {
                menu.setSubMenuList(null);
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", menuList);
            return new ResponseResult(true, 200, "查询成功", map);
        } else {
            //修改操作
            List<Menu> menuList = menuService.findMenuListByPid(-1);
            for (Menu menu : menuList) {
                menu.setSubMenuList(null);
            }
            Menu menu = menuService.findMenuById(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", menuList);
            return new ResponseResult(true, 200, "查询成功", map);
        }
    }



}
