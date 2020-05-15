package com.cjl.emall.payment.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjl.emall.bean.OrderInfo;
import com.cjl.emall.config.LoginRequire;
import com.cjl.emall.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Controller
public class PaymentController {
    @Reference
    private OrderService orderService;

    @RequestMapping("index")
    @LoginRequire
    public String index(HttpServletRequest request, Model model){
        // 获取订单的id
        String orderId = request.getParameter("orderId");
        OrderInfo orderInfo = orderService.getOrderInfo(orderId);
        model.addAttribute("orderId",orderId);
        model.addAttribute("totalAmount",orderInfo.getTotalAmount());
        return "index";
    }
    @RequestMapping(value = "alipay/submit",method = RequestMethod.POST)
    @ResponseBody
    public String submitPayment(HttpServletRequest request, HttpServletResponse response){
        //TODO 接入阿里支付
       return "SUCCESS";
    }
}
