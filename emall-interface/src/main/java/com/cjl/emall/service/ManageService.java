package com.cjl.emall.service;

import com.cjl.emall.bean.BaseAttrInfo;
import com.cjl.emall.bean.BaseCatalog1;
import com.cjl.emall.bean.BaseCatalog2;
import com.cjl.emall.bean.BaseCatalog3;

import java.util.List;

public interface ManageService {
    public List<BaseCatalog1> getCatalog1();

    public List<BaseCatalog2> getCatalog2(String catalog1Id);

    public List<BaseCatalog3> getCatalog3(String catalog2Id);

    public List<BaseAttrInfo> getAttrList(String catalog3Id);

}
