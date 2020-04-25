package com.cjl.emall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cjl.emall.bean.*;
import com.cjl.emall.manage.mapper.*;
import com.cjl.emall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service(timeout = 600000)
public class ManageServiceImpl implements ManageService {
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private  SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private  SpuSaleAttrValueMapper spuSaleAttrValueMapper;


    @Override
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();
        return baseCatalog1List;
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2=new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);

        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3=new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);

        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {

        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.getBaseAttrInfoListByCatalog3Id(Long.parseLong(catalog3Id));
        return baseAttrInfoList;

    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //如果有主键就进行更新，如果没有就插入
        if(baseAttrInfo.getId()!=null&&baseAttrInfo.getId().length()>0){
            baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        }else{
            //防止主键被赋上一个空字符串
            if(baseAttrInfo.getId().length()==0){
                baseAttrInfo.setId(null);
            }
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }
        //把原属性值全部清空
        BaseAttrValue baseAttrValue4Del = new BaseAttrValue();
        baseAttrValue4Del.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue4Del);

        //重新插入属性值
        if(baseAttrInfo.getAttrValueList()!=null&&baseAttrInfo.getAttrValueList().size()>0) {
            for (BaseAttrValue attrValue : baseAttrInfo.getAttrValueList()) {
                //防止主键被赋上一个空字符串
                if(attrValue.getId().length()==0){
                    attrValue.setId(null);
                }
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(attrValue);
            }
        }

    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        // 创建属性对象
        BaseAttrInfo attrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        // 创建属性值对象
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        // 根据attrId字段查询对象
        baseAttrValue.setAttrId(attrInfo.getId());
        List<BaseAttrValue> attrValueList = baseAttrValueMapper.select(baseAttrValue);
        // 给属性对象中的属性值集合赋值
        attrInfo.setAttrValueList(attrValueList);
        // 将属性对象返回
        return attrInfo;
    }

    @Override
    public void deleteAttrInfo(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.delete(baseAttrInfo);
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);
    }

    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo){
        List<SpuInfo> spuInfoList = spuInfoMapper.select(spuInfo);
        return  spuInfoList;
    }


    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //保存主表 通过主键存在判断是修改 还是新增
        if(spuInfo.getId()==null||spuInfo.getId().length()==0){
            spuInfo.setId(null);
            spuInfoMapper.insertSelective(spuInfo);
        }else{
            spuInfoMapper.updateByPrimaryKey(spuInfo);
        }

        //保存图片信息 先删除
        Example spuImageExample=new Example(SpuImage.class);
        spuImageExample.createCriteria().andEqualTo("spuId",spuInfo.getId());
        spuImageMapper.deleteByExample(spuImageExample);

        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if(spuImageList!=null) {
            for (SpuImage spuImage : spuImageList) {
                if(spuImage.getId()!=null&&spuImage.getId().length()==0){
                    spuImage.setId(null);
                }
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(spuImage);
            }
        }

        //保存销售属性信息  先删除 再插入
        Example spuSaleAttrExample=new Example(SpuSaleAttr.class);
        spuSaleAttrExample.createCriteria().andEqualTo("spuId",spuInfo.getId());
        spuSaleAttrMapper.deleteByExample(spuSaleAttrExample);

        //保存销售属性值信息  先删除 再插入
        Example spuSaleAttrValueExample=new Example(SpuSaleAttrValue.class);
        spuSaleAttrValueExample.createCriteria().andEqualTo("spuId",spuInfo.getId());
        spuSaleAttrValueMapper.deleteByExample(spuSaleAttrValueExample);

        //保存属性信息，属性值
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if(spuSaleAttrList!=null) {
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                if(spuSaleAttr.getId()!=null&&spuSaleAttr.getId().length()==0){
                    spuSaleAttr.setId(null);
                }
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(spuSaleAttr);
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                    if(spuSaleAttrValue.getId()!=null&&spuSaleAttrValue.getId().length()==0){
                        spuSaleAttrValue.setId(null);
                    }
                    spuSaleAttrValue.setSpuId(spuInfo.getId());
                    spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                }
            }
        }
    }

    @Override
    public List<SpuImage> getSpuImageList(String spuId){
        SpuImage spuImage=new SpuImage();
        spuImage.setSpuId(spuId);
        List<SpuImage> spuImageList = spuImageMapper.select(spuImage);
        return spuImageList;
    }

    @Override
    public  List<SpuSaleAttr> getSpuSaleAttrList(String spuId){
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.selectSpuSaleAttrList(Long.parseLong(spuId));
        return spuSaleAttrList;
    }

    @Override
    public List<SpuSaleAttrValue> getSpuSaleAttrValueList(SpuSaleAttrValue spuSaleAttrValue) {
        List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttrValueMapper.select(spuSaleAttrValue);
        return spuSaleAttrValueList;
    }
}
