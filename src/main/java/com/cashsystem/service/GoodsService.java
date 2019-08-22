package com.cashsystem.service;

import com.cashsystem.dao.GoodsDao;
import com.cashsystem.entity.Goods;

import java.util.List;

public class GoodsService {
    private GoodsDao goodsDao;
    public GoodsService(){
        this.goodsDao=new GoodsDao();
    }
    public List<Goods> queryAllGoods(){
        return this.goodsDao.queryAllGoods();
    }
    //上架商品
    public boolean goodsPutAway(Goods goods){
        return this.goodsDao.goodsPutAway(goods);
    }
    //通过goodsId拿到对应的货物
    public Goods getGoods(int goodsId){
        return this.goodsDao.getGoods(goodsId);
    }
    //更新商品
    public boolean modifyGoods(Goods goods){
        return this.goodsDao.modifyGoods(goods);
    }
    //下架商品
    public boolean soldOutGoods(int goodsId){
        return this.goodsDao.soldOutGoods(goodsId);
    }
    //支付成功之后更新商品
    public boolean updateAfterPay(Goods goods, int goodsNum) {
       return  this.goodsDao.updateAfterPay(goods,goodsNum);
    }

}
