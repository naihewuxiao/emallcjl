package com.cjl.emall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjl.emall.bean.SkuInfo;
import com.cjl.emall.bean.SpuImage;
import com.cjl.emall.bean.SpuSaleAttr;
import com.cjl.emall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class SkuManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping(value ="spuImageList" ,method = RequestMethod.GET)
    @ResponseBody
    public List<SpuImage> getSpuImageList(@RequestParam Map<String,String> map){
        String spuId = map.get("spuId");
        List<SpuImage> spuImageList = manageService.getSpuImageList(spuId);
        return spuImageList;
    }


    @RequestMapping(value = "saveSku",method = RequestMethod.POST)
    @ResponseBody
    public String saveSku(SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return "success";
    }

    @RequestMapping(value="skuInfoListBySpu")
    @ResponseBody
    public List<SkuInfo>  getSkuInfoListBySpu(HttpServletRequest httpServletRequest){
        String spuId = httpServletRequest.getParameter("spuId");
        List<SkuInfo> skuInfoList = manageService.getSkuInfoListBySpu(spuId);
        return skuInfoList;
    }
}
