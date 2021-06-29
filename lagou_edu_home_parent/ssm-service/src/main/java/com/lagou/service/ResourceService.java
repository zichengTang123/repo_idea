package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

public interface ResourceService {

    /*按条件查询资源数据*/
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);
}
