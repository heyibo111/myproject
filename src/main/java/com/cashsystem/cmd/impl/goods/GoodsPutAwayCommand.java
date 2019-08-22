package com.cashsystem.cmd.impl.goods;
import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.entity.Goods;

@CommandMeta(
        name="SJSP",
        desc="上架商品",
        group="商品信息"
)
@AdminCommand
public class GoodsPutAwayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("上架商品");
        System.out.println("请输入商品名称：");
        String name=scanner.nextLine();
        System.out.println("请输入商品简介：");
        String introduce =scanner.nextLine();
        System.out.println("请输入商品库存：");
        //进行强转
        Integer stock =Integer.parseInt(scanner.nextLine());
        System.out.println("请输入库存单位： 个，包，箱，瓶...... ");
        String unit =scanner.nextLine();
        System.out.println("请输入商品价格：单位(元)，保留小数点后2位");
        Integer price=new Double(100*scanner.nextDouble()).intValue();
        System.out.println("请输入商品折扣： 例：88表示88折");
        Integer discount=scanner.nextInt();

        Goods goods=new Goods();

        goods.setName(name);
        goods.setIntroduce(introduce);
        goods.setStock(stock);
        goods.setUnit(unit);
        goods.setPrice(price);
        goods.setDiscount(discount);

        boolean effect=this.goodsService.goodsPutAway(goods);
        if(effect){
            System.out.println("恭喜您上架成功！");
        }else{
            System.out.println("上架失败...");
        }
    }
}
