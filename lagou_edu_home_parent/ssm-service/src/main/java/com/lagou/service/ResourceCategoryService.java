package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {

    /*查询所有资源分类信息*/
    public List<ResourceCategory> findAllResourceCategory();

    /*新增资源分类信息*/
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /*修改资源分类信息*/
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /*删除资源分类信息*/
    public void deleteResourceCategory(Integer id);
}
