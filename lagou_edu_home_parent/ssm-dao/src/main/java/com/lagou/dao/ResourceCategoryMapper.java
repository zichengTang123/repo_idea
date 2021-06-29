package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {

    /*查询所有资源分类信息*/
    public List<ResourceCategory> findAllResourceCategory();

    /*新增资源分类信息*/
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /*修改资源分类信息*/
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /*删除资源分类信息*/
    public void deleteResourceCategory(Integer id);

    /*通过id查找资源类别的名称*/
    public String findCategoryNameById(Integer id);

}
