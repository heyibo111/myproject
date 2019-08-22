package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
@CommandMeta(
        name="QQZH",
        desc="启停账号",
        group="账号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("启停账号");
    }
}
