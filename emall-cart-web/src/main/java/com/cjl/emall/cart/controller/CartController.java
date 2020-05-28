package com.cjl.emall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cjl.emall.bean.CartInfo;
import com.cjl.emall.bean.SkuInfo;
import com.cjl.emall.cart.handler.CartCookieHandler;
import com.cjl.emall.service.CartService;
import com.cjl.emall.service.ManageService;
import com.cjl.emall.config.LoginRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CartController {
    @Reference
    private CartService cartService;

    @Autowired
    private CartCookieHandler cartCookieHandler;

    @Reference
    private ManageService manageService;

    @RequestMapping("addToCart")
    @LoginRequire(autoRedirect = false)
    public String addToCart(HttpServletRequest request, HttpServletResponse response){
        // 获取userId，skuId，skuNum
        String skuId = request.getParameter("skuId");
        String skuNum = request.getParameter("skuNum");

        String userId = (String) request.getAttribute("userId");
        // 判断用户是否登录
        if (userId!=null){
            // 说明用户登录
            cartService.addToCart(skuId,userId,Integer.parseInt(skuNum));
        }else{
            // 说明用户没有登录没有登录放到cookie中
            cartCookieHandler.addToCart(request,response,skuId,userId,Integer.parseInt(skuNum));
        }
        // 取得sku信息对象
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        request.setAttribute("skuInfo",skuInfo);
        request.setAttribute("skuNum",skuNum);
        return "success";
    }

    @RequestMapping("cartList")
    @LoginRequire(autoRedirect = false)
    public String cartList(HttpServletRequest request, HttpServletResponse response){
        // 根据userId 判断
        String userId = (String) request.getAttribute("userId");
        if (userId!=null){
            // 从redis --- mysql
            // 先判断cookie是否有购物车
            List<CartInfo> cartListCK = cartCookieHandler.getCartList(request);
            List<CartInfo> cartList = null;
            if (cartListCK!=null && cartListCK.size()>0){
                // 有：合并，将cookie购物车删除
                cartList = cartService.mergeToCartList(cartListCK,userId);
                // 删除
                cartCookieHandler.deleteCartCookie(request,response);
            } else {
                // 没有：直接显示redis - mysql。
                cartList = cartService.getCartList(userId);
            }
            request.setAttribute("cartList",cartList);
        }else {
            // cookie 中查询
            List<CartInfo> cartList = cartCookieHandler.getCartList(request);
            request.setAttribute("cartList",cartList);
        }

        return "cartList";
    }

    /**
     * 获取选中的状态
     */
    @RequestMapping("checkCart")
    @LoginRequire(autoRedirect = false)
    @ResponseBody
    public void checkCart(HttpServletRequest request,HttpServletResponse response){
        String skuId = request.getParameter("skuId");
        String isChecked = request.getParameter("isChecked");
        // 获取用户Id ，来判断用户是否登录
        String userId = (String) request.getAttribute("userId");

        if (userId!=null){
            // 登录了，记录当前的商品状态
            cartService.checkCart(skuId,isChecked,userId);
        }else{
            // 操作cookie
            cartCookieHandler.checkCart(request,response,skuId,isChecked);
        }
    }

    @RequestMapping("toTrade")
    @LoginRequire(autoRedirect = true)
    public String toTrade(HttpServletRequest request,HttpServletResponse response){
        String userId = (String) request.getAttribute("userId");
        List<CartInfo> cookieHandlerCartList = cartCookieHandler.getCartList(request);
        if (cookieHandlerCartList!=null && cookieHandlerCartList.size()>0){
            cartService.mergeToCartList(cookieHandlerCartList, userId);
            cartCookieHandler.deleteCartCookie(request,response);
        }
        return "redirect://order.emall.com/trade";
    }

}
