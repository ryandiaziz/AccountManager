package id.dojo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts;
        List<PassHistory> passHistories;
        Saver.fileName = "/home/ryan/Projects/AccountManager/src/main/resources/account.txt";
        Saver.fileHistory = "/home/ryan/Projects/AccountManager/src/main/resources/pass_history.txt";
        boolean isRunning = true;

        while (isRunning){
            String item = Menu.mainMenu();

            switch (item.toLowerCase()){
                case "1":
                    accounts = Saver.retrieveObject();
                    Menu.showAccountList(accounts);
                    break;
                case "2":
                    Menu.detailAccount();
                    break;
                case "3":
                    accounts = Saver.retrieveObject();
                    Account newAccount = Menu.addAccount();
                    accounts.add(newAccount);

                    Saver.saveObject(accounts);
                    break;
                case "4":
                    accounts = Saver.retrieveObject();
                    Menu.changePass(accounts);
                    break;
                case "5":
                    passHistories = Saver.retrieveHistory();
                    Menu.showhistoryList(passHistories);
                    break;
                case "6":
                    accounts = Saver.retrieveObject();
                    Menu.deleteAccount(accounts);
                    break;
                case "x":
                    isRunning = false;
                    break;
                default:
                    Utils.message("Pilihan tidak diketahui");
                    break;
            }
        }
    }
}
