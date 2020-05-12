package com.cjl.emall.cart.mapper;

import com.cjl.emall.bean.CartInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CartInfoMapper extends Mapper<CartInfo> {
    // 根据用户Id 查询商品的实时价格
    List<CartInfo> selectCartListWithCurPrice(String userId);
}
