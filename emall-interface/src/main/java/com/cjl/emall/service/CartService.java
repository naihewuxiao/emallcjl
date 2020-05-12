package com.cjl.emall.service;

import com.cjl.emall.bean.CartInfo;

import java.util.List;

public interface CartService {
    /**
     *
     * @param skuId 商品id
     * @param userId 用户id
     * @param skuNum 商品数据
     */
    void  addToCart(String skuId,String userId,Integer skuNum);

    /**
     *
     * @param userId 用户Id
     * @return
     */
    List<CartInfo> getCartList(String userId);

    /**
     * 合并购物车
     * @param cartListCK cookie 中的购物车
     * @param userId 用户Id
     * @return
     */
    List<CartInfo> mergeToCartList(List<CartInfo> cartListCK, String userId);

    /**
     *
     * @param skuId 商品Id
     * @param isChecked 商品的状态
     * @param userId 用户的Id
     */
    void checkCart(String skuId, String isChecked, String userId);

    /**
     *
     * @param userId 用户Id
     * @return
     */
    List<CartInfo> getCartCheckedList(String userId);
}
