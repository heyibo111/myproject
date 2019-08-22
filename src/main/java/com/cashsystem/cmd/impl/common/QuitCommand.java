package com.cashsystem.cmd.impl.common;
import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstractCommand;
@CommandMeta(
        name="TCXT",
        desc="退出系统",
        group="公共命令信息"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("退出系统，欢迎下次使用！");
        this.scanner.close();
        System.exit(0);//正常退出
    }
}
