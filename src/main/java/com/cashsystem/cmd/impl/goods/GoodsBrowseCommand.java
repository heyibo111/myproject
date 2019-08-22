package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.entity.Goods;

import java.util.List;

@CommandMeta(
        name="LLSP",
        desc="浏览商品",
        group="商品信息"
)
@AdminCommand
@CustomerCommand
public class GoodsBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("浏览商品");
        //1.查询所有商品
        List<Goods> goodsList= this.goodsService.queryAllGoods();
        if(goodsList.isEmpty()){
            System.out.println("商品为空");
        }else{
            for (Goods goods:goodsList) {
                System.out.println(goods);
            }
        }
    }
}