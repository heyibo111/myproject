package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstractCommand;
@CommandMeta(
        name="CZMM",
        desc="重置密码",
        group="账号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("重置密码");
    }
}
