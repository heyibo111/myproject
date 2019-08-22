package com.cashsystem.service;

import com.cashsystem.dao.OrderDao;
import com.cashsystem.entity.Account;
import com.cashsystem.entity.Goods;
import com.cashsystem.entity.Order;

import java.util.List;

public class OrderService {
    private OrderDao orderDao;
    public OrderService(){
        this.orderDao=new OrderDao();
    }
    public boolean commitOrder(Order order){
       return this.orderDao.commitOrder(order);
    }
    public List<Order> queryOrderByAccount(Integer accountId) {
       return this.orderDao.queryOrderByAccount(accountId);
    }
}
