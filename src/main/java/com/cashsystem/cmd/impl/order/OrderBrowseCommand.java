package com.cashsystem.cmd.impl.order;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.entity.Order;

import java.util.List;

@CommandMeta(
        name="CKDD",
        desc="查看订单",
        group="订单信息"
)
@CustomerCommand
public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("我的订单列表：");
       List<Order> orderList=this.orderService.queryOrderByAccount(subject.getAccount().getID());
       if(orderList.isEmpty()){
           System.out.println("暂时没有订单");
       }else{
           for (Order order:orderList){
               System.out.println("---------------------开始分割线---------------------");
               System.out.println(order);
               System.out.println("---------------------结束分割线---------------------");
           }
       }
    }
}
