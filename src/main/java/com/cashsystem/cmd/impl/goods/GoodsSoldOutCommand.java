package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.entity.Goods;

@CommandMeta(
        name="XJSP",
        desc="下架商品",
        group="商品信息"
)
@AdminCommand
public class GoodsSoldOutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
       System.out.println("下架商品");
        System.out.println("请输入下架商品的编号");
        int goodsId=Integer.parseInt(scanner.nextLine());
        Goods goods=this.goodsService.getGoods(goodsId);
        if (goods==null){
            System.out.println("下架商品不存在");
            return;
        }else{
            System.out.println("商品原信息如下：");
            System.out.println(goods);
            System.out.println("您确定要将此商品下架吗？ y/n");
            String flg=scanner.next();
            if("y".equalsIgnoreCase(flg)){
                boolean effect=this.goodsService.soldOutGoods(goodsId);
                if(effect){
                    System.out.println("商品下架成功！");
                }else{
                    System.out.println("商品下架失败......");
                }
            }else{
                System.out.println("您选择了不下架此商品");
            }
        }
    }
}
