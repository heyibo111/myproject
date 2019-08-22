package com.cashsystem.dao;

import com.cashsystem.entity.Goods;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends BaseDao {
    public List<Goods> queryAllGoods() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = this.getConnection(true);
            String sql = "select id,name,introduce,stock,unit,price,discount from goods";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            //返回结果集到resultSet
            while (resultSet.next()) {
                //解析resultSet，拿到对应的goods;
                Goods goods = this.extractgoods(resultSet);
                if (goods != null) {
                    list.add(goods);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Goods extractgoods(ResultSet resultSet) {
        Goods goods = new Goods();
        try {
            goods.setId(resultSet.getInt("id"));
            goods.setName(resultSet.getString("name"));
            goods.setIntroduce(resultSet.getString("introduce"));
            goods.setStock(resultSet.getInt("stock"));
            goods.setUnit(resultSet.getString("unit"));
            goods.setPrice(resultSet.getInt("price"));
            goods.setDiscount(resultSet.getInt("discount"));
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public boolean goodsPutAway(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into goods (name,introduce,stock,unit, price,discount)" +
                    "values(?,?,?,?,?,?)";
            //获取你插入的语句的id值
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setString(4, goods.getUnit());
            preparedStatement.setInt(5, goods.getPrice());
            preparedStatement.setInt(6, goods.getDiscount());

            effect = (preparedStatement.executeUpdate() == 1);
            resultSet = preparedStatement.getGeneratedKeys();//获取自增的主键
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                goods.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return effect;
    }

    public Goods getGoods(int goodsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Goods goods = null;
        try {
            connection = this.getConnection(true);
            String sql = "select id,name,introduce,stock,unit,price,discount " +
                    "from goods where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                goods = this.extractgoods(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public boolean modifyGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "update goods set name=?,introduce=?,stock=?,unit=?,price=?," +
                    "discount=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setString(4, goods.getUnit());
            preparedStatement.setInt(5, goods.getPrice());
            preparedStatement.setInt(6, goods.getDiscount());
            preparedStatement.setInt(7, goods.getId());

            effect = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(null, preparedStatement, connection);
        }
        return effect;
    }

    public boolean soldOutGoods(int goodsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "delete from goods where id=? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            effect = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(null, preparedStatement, connection);
        }
        return effect;
    }

    //支付成功之后，更新货物库存
    public boolean updateAfterPay(Goods goods, int goodsBuyNum) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        boolean effect=false;
        try{
            connection=this.getConnection(true);
            String sql=" update goods set stock=? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,goods.getStock()-goodsBuyNum);
            preparedStatement.setInt(2,goods.getId());
            if(preparedStatement.executeUpdate()==1){
                effect = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResource(null,preparedStatement,connection);
        }
        return effect;
    }
}
