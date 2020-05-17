package com.cjl.emall.payment.service.impl;

import com.cjl.emall.config.ActiveMQUtil;
import com.cjl.emall.payment.service.PaymentService;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ActiveMQUtil activeMQUtil;

    @Override
    public void sendPaymentResult(String OrderId, String result){
        Connection connection = activeMQUtil.getConnection();
        try {
            connection.start();
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            // 创建队列
            Queue paymentResultQueue = session.createQueue("PAYMENT_RESULT_QUEUE");
            MessageProducer producer = session.createProducer(paymentResultQueue);
            MapMessage mapMessage = new ActiveMQMapMessage();
            mapMessage.setString("orderId",OrderId);
            mapMessage.setString("result",result);
            producer.send(mapMessage);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
