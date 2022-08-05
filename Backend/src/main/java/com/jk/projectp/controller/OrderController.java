package com.jk.projectp.controller;

import com.jk.projectp.model.Order;
import com.jk.projectp.model.User;
import com.jk.projectp.result.BaseResult;
import com.jk.projectp.service.OrderService;
import com.jk.projectp.result.ResponseCode;
import com.jk.projectp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @PostMapping(value = "/api/createOrder")
    @ResponseBody
    public BaseResult<String> createOrder(@RequestBody Order requestOrder, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // check role here
        orderService.createOrder(user, requestOrder);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }

    @CrossOrigin
    @PostMapping(value = "/api/updateOrder")
    @ResponseBody
    public BaseResult<String> updateOrder(@RequestBody Order requestOrder, HttpServletRequest request) {
        User user = userService.checkLogin(request);
        if (user == null) {
            return new BaseResult<>(ResponseCode.NOT_LOGIN);
        }
        // check role here
        orderService.updateOrder(requestOrder);
        return new BaseResult<>(ResponseCode.SUCCESS);
    }
}
