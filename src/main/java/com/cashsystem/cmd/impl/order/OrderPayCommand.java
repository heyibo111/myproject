package com.cashsystem.cmd.impl.order;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.common.OrderStatus;
import com.cashsystem.entity.Goods;
import com.cashsystem.entity.Order;
import com.cashsystem.entity.OrderItem;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CommandMeta(
        name="ZFDD",
        desc="支付订单",
        group="订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("请输入您想要买的货物id以及数量，多个货物之间用，号隔开：" +
                "格式：1-8，3-5");
        String string =scanner.nextLine();
        String[] strings=string.split(",");
        //把所有需要购买的商品， 存放至goodsList
        List<Goods> goodsList=new ArrayList<>();
        for(String goodsString: strings){
            //[0]  1   [1]  8
            String[] str=goodsString.split("-");
            Goods goods=this.goodsService.getGoods(Integer.parseInt(str[0]));
            goods.setBuyGoodsNum(Integer.parseInt(str[1]));
            goodsList.add(goods);
        }
        Order order=new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_id(subject.getAccount().getID());
        order.setAccount_name(subject.getAccount().getName());
        order.setCreate_time(LocalDateTime.now());
        int totalMoney = 0;
        int actualMoney = 0;

        for (Goods goods:goodsList){
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsNum(goods.getBuyGoodsNum());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsIntroduce(goods.getIntroduce());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPrice());
            orderItem.setGoodsDiscount(goods.getDiscount());
            order.orderItemList.add(orderItem);
            int currentMoney=goods.getBuyGoodsNum()*goods.getPrice();
            totalMoney += currentMoney;
            actualMoney += currentMoney*goods.getDiscount()/100;
        }
        order.setTotal_money(totalMoney);
        order.setActual_amount(actualMoney);
        order.setOrder_status(OrderStatus.PLAYING);

        //先进行展示当前订单的明细
        System.out.println(order);
        System.out.println("请确认是否支付订单：  确认支付：zf");
        String confirm= scanner.next();
        if("zf".equalsIgnoreCase(confirm)){
            order.setFinish_time(LocalDateTime.now());
            order.setOrder_status(OrderStatus.OK);
            boolean effect=this.orderService.commitOrder(order);  //提交订单
            if(effect){//插入订单和订单项成功
                for(Goods goods:goodsList){
                    boolean isUpdate =
                            this.goodsService.updateAfterPay(goods,goods.getBuyGoodsNum());
                    if(isUpdate){
                        System.out.println("库存更新成功！");
                    }else{
                        System.out.println("库存更新失败");
                    }
                }
            }
        }else{
            System.out.println("订单没有支付成功，请重新下单！");
        }
    }
}
