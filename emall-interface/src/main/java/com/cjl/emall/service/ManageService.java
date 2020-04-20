package com.cjl.emall.service;

import com.cjl.emall.bean.*;

import java.util.List;

public interface ManageService {
    /**
     * 获取一级目录
     * @return
     */
    List<BaseCatalog1> getCatalog1();

    /**
     * 根据一级目录获取二级目录
     * @param catalog1Id
     * @return
     */
    List<BaseCatalog2> getCatalog2(String catalog1Id);

    /**
     * 根据二级目录获取三级目录
     * @param catalog2Id
     * @return
     */
    List<BaseCatalog3> getCatalog3(String catalog2Id);

    /**
     * 根据三级目录获取属性列表
     * @param catalog3Id
     * @return
     */
    List<BaseAttrInfo> getAttrList(String catalog3Id);

    /**
     * 存储属性信息
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 获取属性信息
     * @param attrId
     * @return
     */
    BaseAttrInfo getAttrInfo(String attrId);

    /**
     * 删除属性信息
     * @param baseAttrInfo
     */
    void deleteAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 获取SPU列表
     * @param spuInfo
     * @return
     */
    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

    /**
     * 获取销售属性列表
     * @return
     */
    List<BaseSaleAttr> getBaseSaleAttrList();

    /**
     * 存储SPU信息
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 根据spuId查询spu图片数据
     * @param spuId
     * @return
     */
    List<SpuImage> getSpuImageList(String spuId);

    /**
     * 根据spuId查询销售属性列表
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);

    /**
     * 查询销售属性值列表
     * @param spuSaleAttrValue
     * @return
     */
    List<SpuSaleAttrValue> getSpuSaleAttrValueList(SpuSaleAttrValue spuSaleAttrValue);
}
