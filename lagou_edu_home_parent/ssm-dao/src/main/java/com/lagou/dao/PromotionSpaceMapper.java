package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    /*查询所有广告位*/

    public List<PromotionSpace> findAllPromotionSpace();

    /*添加广告位*/
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*回显广告位名称*/
    public PromotionSpace findPromotionSpaceById(Integer id);

    /*修改广告位信息*/
    public void updatePromotionSpace(PromotionSpace promotionSpace);

}
