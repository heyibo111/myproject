package com.cashsystem.dao;

import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import com.cashsystem.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends BaseDao {

//操作数据库
    public Account login(String username,String password) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Account account=null;
        try{
            //拿到连接
            connection=this.getConnection(true);
            String sql="select id,username,password,name,account_type,account_status " +
                    "from account where username=?and password=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,DigestUtils.md5Hex(password));
            resultSet=preparedStatement.executeQuery();
            //返回结果集到resultSet
            if(resultSet.next()){
                //解析resultSet，拿到对应的account
                account=this.extractAccount(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    private Account extractAccount(ResultSet resultSet){
        Account account=new Account();
        try {
            account.setID(resultSet.getInt("id"));
            account.setUsername(resultSet.getString("username"));
            account.setPassword(resultSet.getString("password"));
            account.setName(resultSet.getString("name"));
            account.setAccountType(AccountType.valueOf(resultSet.getInt("account_type")));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getInt("account_status")));
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return account;
    }

    public boolean Register(Account account){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        boolean effect=false;
        try{
            connection=this.getConnection(true);
            String sql="insert into account (username,password,name,account_type,account_status )" +
                    "values (?,?,?,?,?)";
            //获取你插入的语句的id值
            preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,DigestUtils.md5Hex(account.getPassword()));
            preparedStatement.setString(3,account.getName());
            preparedStatement.setInt(4,account.getAccountType().getFlg());
            preparedStatement.setInt(5,account.getAccountStatus().getFlg());

            effect=(preparedStatement.executeUpdate()==1);
            resultSet=preparedStatement.getGeneratedKeys();//获取自增的主键
            if(resultSet.next()){
                 Integer id=resultSet.getInt(1);
                 account.setID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet,preparedStatement,connection);
        }
        return  effect;
    }
    public List<Account> queryAllAccount(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Account> accountList=new ArrayList<>();
        try{
            connection=this.getConnection(true);
            String sql="select id,username,password,name,account_type,account_status " +
                    "from account";
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                accountList.add(this.extractAccount(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet,preparedStatement,connection);
        }
        return accountList;
    }
}
