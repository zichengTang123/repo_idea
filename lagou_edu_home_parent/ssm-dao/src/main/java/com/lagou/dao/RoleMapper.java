package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /*根据条件查询角色*/

    public List<Role> findAllRole(Role role);

    /*根据Id删除角色对应的menu*/
    public void deleteMenuByRoleId(Integer roleId);

    /*新增角色信息*/
    public void insertMenu(Role_menu_relation role_menu_relation);


    /*删除角色*/
    public void deleteRoleById(Integer roleId);

    /*获取当前角色拥有的 资源信息*/
    public List<ResourceResourceCategoryVO> findResourceListByRoleId(Integer id);

    /*根据roleId删除对应资源连接*/
    public void deleteRoleResourceRelationById(Integer id);

    /*根据roleID, 以及resourceIdList为角色和资源建立连接*/
    public void insertRoleResourceRelation(RoleResourceRelation roleResourceRelation);


}
