package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /*查询所有的资源分类信息*/
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> categoryList = resourceCategoryMapper.findAllResourceCategory();
        return categoryList;
    }

    /*保存资源分类信息*/
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        //封装数据
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        resourceCategory.setCreatedTime(new Date());
        resourceCategory.setUpdatedTime(new Date());
        //执行saveResourceCategory方法
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    /*更新资源分类信息*/
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        //封装数据
        resourceCategory.setUpdatedTime(new Date());
        //执行update方法
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /*删除资源分类信息*/
    @Override
    public void deleteResourceCategory(Integer id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }
}
