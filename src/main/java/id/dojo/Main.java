package id.dojo;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Saver.fileName = "/home/ryan/Projects/AccountManager/src/main/resources/account.txt";
        Saver.fileHistory = "/home/ryan/Projects/AccountManager/src/main/resources/pass_history.txt";
        boolean isRunning = true;

        while (isRunning){
            String item = menu.mainMenu();

            switch (item.toLowerCase()){
                case "1":
                    menu.showAccountList();
                    break;
                case "2":
                    menu.detailAccount();
                    break;
                case "3":
                    menu.addAccount();
                    Saver.saveObject(menu.getAccounts());
                    break;
                case "4":
                    menu.changePass();
                    break;
                case "5":
                    menu.showhistoryList();
                    break;
                case "6":
                    menu.deleteAccount();
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
