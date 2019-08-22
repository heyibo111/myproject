package com.cashsystem.service;

import com.cashsystem.dao.AccountDao;
import com.cashsystem.entity.Account;

import java.util.List;

public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }
    public Account login(String username,String password){
        return this.accountDao.login(username,password);
    }
    //注册的函数
    public boolean Register(Account account) {
        return this.accountDao.Register(account);
    }
    //查询所有账户
    public List<Account> queryAllAccount(){
        return this.accountDao.queryAllAccount();
    }
}
