package com.lagou.controller;


import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有广告位成功", promotionSpaceList);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId() == null){
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "新增广告位成功", null);
        } else{
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "修改广告位成功", null);
        }

    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询广告位名称成功", promotionSpace);
        return responseResult;
    }


}
