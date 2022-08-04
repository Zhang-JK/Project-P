package com.jk.projectp.service;

import com.jk.projectp.dao.OrderDAO;
import com.jk.projectp.model.Order;
import com.jk.projectp.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    public void createOrder(User user, @NotNull Order order) {
        order.setProposer(user);
        orderDAO.save(order);
    }

    public void updateOrder(@NotNull Order order) {
        orderDAO.save(order);
    }
}
