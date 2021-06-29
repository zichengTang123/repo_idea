package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /*分页查询广告信息*/
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*新增广告*/
    public void savePromotionAd(PromotionAd promotionAd);


    /*通过id回显广告*/
    public PromotionAd findPromotionAdById(Integer id);

    /*修改广告信息*/
    public void updatePromotionAd(PromotionAd promotionAd);

    /*修改上线状态*/
    public void updatePromotionAdStatus(PromotionAd promotionAd);


}
