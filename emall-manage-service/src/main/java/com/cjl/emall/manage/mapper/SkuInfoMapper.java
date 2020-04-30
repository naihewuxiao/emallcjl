package com.cjl.emall.manage.mapper;

import com.cjl.emall.bean.SkuInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SkuInfoMapper extends Mapper<SkuInfo> {
    List<SkuInfo> selectSkuInfoListBySpu(long spuId);
}
