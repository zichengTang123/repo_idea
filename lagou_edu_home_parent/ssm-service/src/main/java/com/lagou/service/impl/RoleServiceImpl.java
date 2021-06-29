package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.zip.DataFormatException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }

    @Override
    public void roleContextMenu(Integer roleId, List<Integer> menuIdList) {
        roleMapper.deleteMenuByRoleId(roleId);
        Role_menu_relation role_menu_relation = new Role_menu_relation();
        for (Integer menuId : menuIdList) {
            Date date = new Date();
            role_menu_relation.setMenuId(menuId);
            role_menu_relation.setRoleId(roleId);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.insertMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        //删除关联关系
        roleMapper.deleteMenuByRoleId(roleId);
        //删除角色信息
        roleMapper.deleteRoleById(roleId);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {
        //1.获取到resourceResourceCategoryVO集合
        List<ResourceResourceCategoryVO> resourceResourceCategoryVOList = roleMapper.findResourceListByRoleId(id);
        //2.新建resourceCategoryId集合
        ArrayList<Integer> resourceCategoryIdList = new ArrayList<>();
        //3.新建ResourceCategory集合
        ArrayList<ResourceCategory> resourceCategoryList = new ArrayList<>();

        //4.遍历resourceResourceCategoryVO集合，获取到resourceCategoryIdList
        for (ResourceResourceCategoryVO resourceResourceCategoryVO : resourceResourceCategoryVOList) {
            resourceCategoryIdList.add(resourceResourceCategoryVO.getCategoryId());
        }
        //5.得到去重之后的resourceCategoryIdList
        Set set = new HashSet(resourceCategoryIdList);
        resourceCategoryIdList = new ArrayList<>(set);
        //6.遍历resourceCategoryIdList，在每个CategoryId下进行resourceCategory的构建，构建好了之后加入到集合中
        for (Integer categoryId : resourceCategoryIdList) {
            //新建两个list集合用于接收数据
            ResourceCategory resourceCategory = new ResourceCategory();
            ArrayList<Resource> resourceList = new ArrayList<>();
            //设置categoryId
            resourceCategory.setId(categoryId);
            //调用方法得到相应的categoryName
            String categoryName = resourceCategoryMapper.findCategoryNameById(categoryId);
            //设置categoryName
            resourceCategory.setName(categoryName);
            for (ResourceResourceCategoryVO resourceResourceCategoryVO : resourceResourceCategoryVOList) {
                if(resourceResourceCategoryVO.getCategoryId() == categoryId){
                    resourceList.add(resourceResourceCategoryVO.getResource());
                }
            }
            resourceCategory.setResourceList(resourceList);
            resourceCategoryList.add(resourceCategory);
        }
        return resourceCategoryList;
    }

    @Override
    public void roleContextResource(RoleResourceRelationVO roleResourceRelationVO) {
        //删除已有连接
        roleMapper.deleteRoleResourceRelationById(roleResourceRelationVO.getRoleId());
        List<Integer> resourceIdList = roleResourceRelationVO.getResourceIdList();
        //封装数据，新增连接
        for (Integer resourceId : resourceIdList) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResource_id(resourceId);
            roleResourceRelation.setRole_id(roleResourceRelationVO.getRoleId());
            roleResourceRelation.setCreated_time(new Date());
            roleResourceRelation.setUpdated_time(new Date());
            roleResourceRelation.setUpdated_by("system");
            roleResourceRelation.setCreated_by("system");
            roleMapper.insertRoleResourceRelation(roleResourceRelation);
        }
    }

}
