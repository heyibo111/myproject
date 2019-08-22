package com.cashsystem.cmd;

import com.cashsystem.cmd.impl.AbstractCommand;
import com.cashsystem.entity.Account;

import static com.cashsystem.cmd.Commands.getCachedHelpCommands;

public class Checkstand extends AbstractCommand {
    public static void main(String[] args) {
        Subject subject=new Subject();
        new Checkstand().execute(subject);   }

    @Override
    public void execute(Subject subject) {
        Commands.getCachedHelpCommands().execute(subject);
        while(true){
            System.out.println(">>>>>");
            //DL
            String line=scanner.nextLine();
            String commandCode=line.trim().toUpperCase();
            Account account=subject.getAccount();
            if(account==null){
                // Commands.getEntranceCommand(commandCode)返回一个login command的对象
                Commands.getEntranceCommand(commandCode).execute(subject);
            }else {
                switch (account.getAccountType()){
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).execute(subject);
                        break;
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).execute(subject);
                        break;
                        default:
                }
            }
        }
    }
}
