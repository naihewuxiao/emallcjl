package com.cjl.emall.service;

import com.cjl.emall.bean.OrderInfo;
import com.cjl.emall.bean.enums.ProcessStatus;

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

    // 根据订单的Id 修改订单状态
    void updateOrderStatus(String orderId, ProcessStatus paid);
    // 发送通知给库存
    void sendOrderStatus(String orderId);
}
