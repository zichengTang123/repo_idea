package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        return new ResponseResult(true, 200, "查询成功", pageInfo);
    }

    /*图片上传的方法*/
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        //判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取原文件名
        String originalFilename = file.getOriginalFilename();
        String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFilename);
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }

        file.transferTo(filePath);

        HashMap<String, String> map = new HashMap<>();
        map.put("fileName", newFilename);
        map.put("filaPath", "http://localhost:8080/upload/"+newFilename);
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId() == null){
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "新增广告成功", null);
        } else{
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "修改广告成功", null);
        }

    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "回显广告数据成功", promotionAd);
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(PromotionAd promotionAd){
        promotionAdService.updatePromotionAdStatus(promotionAd);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", promotionAd.getStatus());
        return new ResponseResult(true, 200, "修改广告上线状态成功", null);
    }

}
