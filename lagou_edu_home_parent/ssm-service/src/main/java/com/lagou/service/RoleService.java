package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleResourceRelationVO;

import java.util.List;

public interface RoleService {

    /*根据条件查询角色*/

    public List<Role> findAllRole(Role role);

    /*通过roleId和menuIdList更新menu*/
    public void roleContextMenu(Integer roleId, List<Integer> menuIdList);

    /*删除角色*/
    public void deleteRole(Integer roleId);


    public List<ResourceCategory> findResourceListByRoleId(Integer id);

    /*根据roleID, 以及resourceIdList为角色和资源建立连接*/
    public void roleContextResource(RoleResourceRelationVO roleResourceRelationVO);



}
