package com.cjl.emall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjl.emall.bean.*;
import com.cjl.emall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("attrListPage")
    public String arrtListPage(){
        return "attrListPage";
    }

    /***
     * 获得一级分类
     * @return
     */
    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatalog1(){
        List<BaseCatalog1> catalog1List = manageService.getCatalog1();
        return catalog1List;
    }


    /***
     * 获得二级分类
     * @param map
     * @return
     */
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(@RequestParam Map<String,String> map){
        String catalog1Id =   map.get("catalog1Id") ;
        List<BaseCatalog2> catalog2List = manageService.getCatalog2(catalog1Id);
        return catalog2List;
    }

    /***
     * 获得三级分类
     * @param map
     * @return
     */
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(@RequestParam Map<String,String> map){
        String catalog2Id =   map.get("catalog2Id") ;
        List<BaseCatalog3> catalog3List = manageService.getCatalog3(catalog2Id);
        return catalog3List;
    }

    /***
     * 获得属性列表
     * @param map
     * @return
     */
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo>  attrInfoList (@RequestParam Map<String,String> map){
        String catalog3Id =  map.get("catalog3Id") ;
        List<BaseAttrInfo> attrList = manageService.getAttrList(catalog3Id);
        return attrList;
    }

    @RequestMapping(value = "saveAttrInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveAttrInfo(BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
        return "success";
    }

    @RequestMapping(value = "getAttrValueList",method = RequestMethod.POST)
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId){
        BaseAttrInfo attrInfo = manageService.getAttrInfo(attrId);
        return attrInfo.getAttrValueList();
    }

    @RequestMapping(value = "deleteAttrInfo",method = RequestMethod.POST)
    @ResponseBody
    public String deleteAttrValue(BaseAttrInfo baseAttrInfo){
        manageService.deleteAttrInfo(baseAttrInfo);
        return "success";
    }


    //spu相关controller
    @RequestMapping("spuListPage")
    public String spuListPage(){return "spuListPage";}

    @RequestMapping("spuList")
    @ResponseBody
    public List<SpuInfo> getSpuInfoList(@RequestParam Map<String,String> map){
        String catalog3Id = map.get("catalog3Id");
        SpuInfo spuInfo =new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        List<SpuInfo> spuInfoList = manageService.getSpuInfoList(spuInfo);
        return spuInfoList;
    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttrList(){
        return   manageService.getBaseSaleAttrList();
    }

    @RequestMapping(value = "saveSpuInfo",method = RequestMethod.POST)
    @ResponseBody
    public  String saveSpuInfo(SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return  "success";
    }

    @RequestMapping(value ="spuImageList" ,method = RequestMethod.GET)
    @ResponseBody
    public  List<SpuImage> getSpuImageList(@RequestParam Map<String,String> map){
        String spuId = map.get("spuId");
        List<SpuImage> spuImageList = manageService.getSpuImageList(spuId);
        return spuImageList;
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<SpuSaleAttr> getSpuSaleAttrList(HttpServletRequest httpServletRequest){
        String spuId = httpServletRequest.getParameter("spuId");
        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrList(spuId);
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            Map map=new HashMap();
            map.put("total",spuSaleAttrValueList.size());
            map.put("rows",spuSaleAttrValueList);
            // String spuSaleAttrValueJson = JSON.toJSONString(map);
            spuSaleAttr.setSpuSaleAttrValueJson(map);
        }
        return spuSaleAttrList;
    }

    @RequestMapping("spuSaleAttrValueList")
    @ResponseBody
    public List<SpuSaleAttrValue> getSpuSaleAttrValueList(HttpServletRequest httpServletRequest){
        String spuId = httpServletRequest.getParameter("spuId");
        String saleAttrId = httpServletRequest.getParameter("saleAttrId");
        SpuSaleAttrValue spuSaleAttrValue=new SpuSaleAttrValue();
        spuSaleAttrValue.setSpuId(spuId);
        spuSaleAttrValue.setSaleAttrId(saleAttrId);
        List<SpuSaleAttrValue> spuSaleAttrValueList = manageService.getSpuSaleAttrValueList(spuSaleAttrValue);
        return spuSaleAttrValueList;
    }
}
