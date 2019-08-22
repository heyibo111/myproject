package com.cashsystem.entity;
import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import lombok.Data;
@Data
public class Account {
    private  Integer ID;
    private String username;
    private String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
