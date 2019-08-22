package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.entity.Account;

import java.util.List;

@CommandMeta(
        name="CKZH",
        desc="查看账户",
        group="账号信息"
)
@AdminCommand
public class AccountBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("查看账户");
        List<Account> accountList = this.accountService.queryAllAccount();
        if(accountList.isEmpty()) {
            System.out.println("暂时没有账号存在");
        }else{
            System.out.println("-----------------账户信息列表------------------");
            System.out.println("| 编号 | 姓名 | 账号 | 密码 | 类型 | 状态 |");
            for (Account account: accountList ) {

                String str =  new StringBuilder().append("| ").append(account.getID()).append("  ")
                        .append("| ").append(account.getName()).append(" ")
                        .append("| ").append(account.getUsername()).append(" ")
                        .append("| ").append("******").append(" ")
                        .append("| ").append(account.getAccountType().getDesc()).append(" ")
                        .append("| ").append(account.getAccountStatus().getDesc()).append(" ")
                        .append("| ").toString();
                System.out.println(str);
            }
            System.out.println("----------------------------------------------");
        }
    }
}
