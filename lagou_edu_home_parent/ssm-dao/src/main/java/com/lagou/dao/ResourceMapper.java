package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /*按条件查询所有资源*/
    public List<Resource> findAllResource(ResourceVO resourceVO);
}
