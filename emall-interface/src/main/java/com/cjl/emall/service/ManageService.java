package com.cjl.emall.service;

import com.cjl.emall.bean.*;

import java.util.List;

public interface ManageService {
    List<BaseCatalog1> getCatalog1();

    List<BaseCatalog2> getCatalog2(String catalog1Id);

    List<BaseCatalog3> getCatalog3(String catalog2Id);

    List<BaseAttrInfo> getAttrList(String catalog3Id);

    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    BaseAttrInfo getAttrInfo(String attrId);

    void deleteAttrInfo(BaseAttrInfo baseAttrInfo);

    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);
}
