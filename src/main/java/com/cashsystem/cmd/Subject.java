package com.cashsystem.cmd;

import com.cashsystem.entity.Account;

public class Subject {
    private Account account;
    public void setAccount(Account account){
        this.account=account;
    }
    public Account getAccount(){
        return this.account;
    }
}
