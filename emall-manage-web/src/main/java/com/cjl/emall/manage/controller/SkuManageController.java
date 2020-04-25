package com.cjl.emall.manage.controller;

import com.cjl.emall.bean.SpuImage;
import com.cjl.emall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

public class SkuManageController {

    @Autowired
    private ManageService manageService;

    @RequestMapping(value ="spuImageList" ,method = RequestMethod.GET)
    @ResponseBody
    public List<SpuImage> getSpuImageList(@RequestParam Map<String,String> map){
        String spuId = map.get("spuId");
        List<SpuImage> spuImageList = manageService.getSpuImageList(spuId);
        return spuImageList;
    }
}
