package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roleList = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "查询角色成功", roleList);
    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findMenuListByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);
        return new ResponseResult(true, 200, "查询成功", map);
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam Integer roleId){
        List<Integer> integerList = menuService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "查询菜单成功", integerList);
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.roleContextMenu(roleMenuVO.getRoleId(), roleMenuVO.getMenuIdList());
        return new ResponseResult(true, 200, "更新menu成功", null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(@RequestParam Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "删除成功", null);
    }

    /*通过roleid查找资源*/
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(@RequestParam Integer roleId){
        List<ResourceCategory> categoryList = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true, 200, "查询成功", categoryList);
    }

    /*给角色分配资源*/
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceRelationVO roleResourceRelationVO){
        roleService.roleContextResource(roleResourceRelationVO);
        return new ResponseResult(true, 200, "分配资源成功", null);
    }

}
