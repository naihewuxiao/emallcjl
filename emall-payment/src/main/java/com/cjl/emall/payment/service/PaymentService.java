package com.cjl.emall.payment.service;

public interface PaymentService {
    void sendPaymentResult(String orderId, String result);
}
