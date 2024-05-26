package id.dojo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Account> accounts = new ArrayList<>();
    private List<PassHistory> passHistories = new ArrayList<>();


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts() {
        this.accounts = Saver.retrieveObject();
    }

    public void setAccount(Account account){
        accounts.add(account);
    }

    public List<PassHistory> getPassHistories() {
        return passHistories;
    }

    public void setPassHistories() {
        this.passHistories = Saver.retrieveHistory();
    }

    public String mainMenu(){
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

    public void addAccount(){
        String name = Utils.input("Masukkan nama akun");
        String userName = Utils.input("Masukkan username");
        String password = Utils.input("Masukkan password");

        setAccounts();
        setAccount(new Account(name,userName,password));
    }

    public void detailAccount(){
        boolean isExist = false;
        String target = Utils.input("Masukkan nama akun");

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

    void deleteAccount(){
        setAccounts();
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

    public void changePass(){
        setAccounts();
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
            setPassHistories();
            passHistories.add(new PassHistory(accountName, oldPass, newPass));
            Saver.saveHistory(passHistories);
        }else{
            Utils.message("Akun tidak ada!");
        }

    }

    void showAccountList(){
        setAccounts();
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

    void showhistoryList(){
        setPassHistories();
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
