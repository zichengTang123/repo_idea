package com.lagou.dao;

import com.lagou.domain.Test;

import java.util.List;

public interface TestMapper {

    /*
    * 查询所有的实例*/

    public List<Test> findAllTest();
}
