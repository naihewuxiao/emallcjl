package com.cjl.emall.manage.mapper;

import com.cjl.emall.bean.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {
    public List<SpuSaleAttr> selectSpuSaleAttrList(long spuId);
}
