package com.cjl.emall.service;

import com.cjl.emall.bean.OrderInfo;

public interface OrderService {
    String  saveOrder(OrderInfo orderInfo);

    /**
     * 生成订单号
     * @param userId
     * @return
     */
    String getTradeNo(String userId);

    boolean checkTradeCode(String userId,String tradeCodeNo);

    void  delTradeNo(String userId);

    boolean checkStock(String skuId, Integer skuNum);

    OrderInfo getOrderInfo(String orderId);
}
