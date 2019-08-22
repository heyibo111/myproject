package com.cashsystem.cmd.impl.entrance;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import com.cashsystem.entity.Account;

@CommandMeta(
        name="ZC",
        desc="注册",
        group="入口命令"
)
@EntranceCommand
public class RegisterCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("*****注册******");
        System.out.println("请输入用户名：");
        String username=scanner.nextLine();
        System.out.println("请输入密码：");
        String password1=scanner.nextLine();
        System.out.println("请再次输入密码：");
        String password2=scanner.nextLine();
        if(!password1.equals(password2)){
            System.out.println("对不起！您两次输入的密码不一致！");
            return;
        }else{
            System.out.println("请输入姓名：");
            String name=scanner.nextLine();
            System.out.println("请输入你的账户类型：1.管理员  2.客户");
            int accountTypeFlag=scanner.nextInt();
            AccountType accountType=AccountType.valueOf(accountTypeFlag);
            System.out.println("请输入用户的状态： 1 启用 2 启停");
            int accountStatusFlag=scanner.nextInt();
            AccountStatus accountStatus=AccountStatus.valueOf(accountStatusFlag);

            final Account account=new Account();
            account.setUsername(username);
            account.setPassword(password1);
            account.setName(name);
            account.setAccountType(accountType);
            account.setAccountStatus(accountStatus);

            boolean effect=this.accountService.Register(account);
            if(effect){
                System.out.println("恭喜您注册成功！");
            }else{
                System.out.println("注册失败...");
            }
        }
    }
}
