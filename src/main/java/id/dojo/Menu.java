package id.dojo;

import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner  scanner = new Scanner(System.in);

    static String mainMenu(){
        Utils.menuHeader("Menu Utama");
        Utils.border();
        Utils.menuItem("Pilih","Nama Menu");
        Utils.border();
        Utils.menuItem("1","Tampilkan Daftar Akun");
        Utils.menuItem("2","Tampilkan Akun");
        Utils.menuItem("3","Tambah Akun");
        Utils.menuItem("4","Ubah Password");
        Utils.menuItem("5","Riwayat Perubahan Password");
        Utils.menuItem("6","Hapus Akun");
        Utils.menuItem("x","Keluar");
        Utils.border();
        return Utils.input("Masukkan pilihan menu");
    }

    static Account addAccount(){
        String name = Utils.input("Masukkan nama akun");
        String userName = Utils.input("Masukkan username");
        String password = Utils.input("Masukkan password");

        return new Account(name,userName,password);
    }

    static void detailAccount(){
        boolean isExist = false;
        String target = Utils.input("Masukkan nama akun");

        List<Account> accounts = Saver.retrieveObject();

        for (Account account : accounts){
            if (account.getName().equalsIgnoreCase(target)){
                while (true){
                    Utils.border("-------------------");
                    Utils.menuAccountDetail("Nama", "Username", "Password");
                    Utils.border("-------------------");
                    Utils.menuAccountDetail(account.getName(),account.getUserName(),account.getPassword());
                    Utils.border("-------------------");

                    if (Utils.input("( x ) Untuk keluar").equalsIgnoreCase("x")) break;
                    Utils.message("Pilihan tidak diketahui");
                }

                isExist = true;
                break;
            }
        }

        if (!isExist){
            Utils.message("Akun tidak ditemukan!");
        }
    }

    static void deleteAccount(List<Account> accounts){
        if (accounts.isEmpty()) {
            Utils.message("Tidak ada data!");
            return;
        }
        boolean isExist =false;

        String target = Utils.input("Masukkan nama akun");

        List<Account> newAccounts = new ArrayList<>();

        for (Account account : accounts){
            if (account.getName().equalsIgnoreCase(target)){
                isExist = true;
                continue;
            }
            newAccounts.add(account);
        }

        if (isExist){
            Saver.saveObject(newAccounts);
        }else{
            Utils.message("Akun tidak ada!");
        }

    }

    static void changePass(List<Account> accounts){
        if (accounts.isEmpty()) {
            Utils.message("Tidak ada data!");
            return;
        }

        boolean isExist = false;
        String oldPass ="";
        String accountName = "";
        String target = Utils.input("Masukkan nama akun");
        String newPass = Utils.input("Masukkan password baru");

        List<Account> newAccounts = new ArrayList<>();

        for (Account account : accounts){
            if (account.getName().equalsIgnoreCase(target)){
                accountName = account.getName();
                oldPass = account.getPassword();
                account.setPassword(newPass);
                isExist = true;
            }
            newAccounts.add(account);
        }

        if (isExist){
            Saver.saveObject(newAccounts);
            List<PassHistory> passHistories = Saver.retrieveHistory();
            passHistories.add(new PassHistory(accountName, oldPass, newPass));
            Saver.saveHistory(passHistories);
        }else{
            Utils.message("Akun tidak ada!");
        }

    }

    static void showAccountList(List<Account> accounts){
        if (accounts.isEmpty()){
            Utils.message("Tidak ada data!");
        }else{
            while (true){
                Utils.border();
                Utils.menuAccount("Nama Akun", "Username");
                Utils.border();
                for(Account account : accounts){
                    Utils.menuAccount(account.getName(), account.getUserName());
                }
                Utils.border();

                if(Utils.input("( x ) Untuk keluar").equalsIgnoreCase("x")) break;
                Utils.message("Pilihan tidak diketahui");
            }

        }
    }

    static void showhistoryList(List<PassHistory> passHistories){
        if (passHistories.isEmpty()){
            Utils.message("Tidak ada perubahan password!");
        }else{
            while (true){
                Utils.border("------------------------------------------------------");
                Utils.menuHistory("Tanggal Berubah", "Nama Akun", "Password Lama", "Password Baru");
                Utils.border("------------------------------------------------------");
                for(PassHistory passHistory : passHistories){
                    Utils.menuHistory(passHistory.getChangeAt(), passHistory.getAccountName(), passHistory.getOldPass(), passHistory.getNewPass());
                }
                Utils.border("------------------------------------------------------");

                if(Utils.input("( x ) Untuk keluar").equalsIgnoreCase("x")) break;
                Utils.message("Pilihan tidak diketahui");
            }

        }
    }
}
