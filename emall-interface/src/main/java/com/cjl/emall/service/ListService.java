package com.cjl.emall.service;

import com.cjl.emall.bean.SkuInfo;
import com.cjl.emall.bean.SkuLsInfo;
import com.cjl.emall.bean.SkuLsParams;
import com.cjl.emall.bean.SkuLsResult;

public interface ListService {
    void saveSkuInfo(SkuInfo skuInfo);
    // 根据检索条件查询数据
    SkuLsResult search(SkuLsParams skuLsParams);

    void incrHotScore(String skuId);
}
