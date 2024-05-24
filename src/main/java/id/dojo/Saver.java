package id.dojo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Saver {
    public static String fileName;
    public static String fileHistory;

    public static void saveObject(List<Account> accounts){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Account account : accounts){
                objectOutputStream.writeObject(account);
            }

            objectOutputStream.flush();

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Account> retrieveObject(){
        File file = new File(fileName);
        List<Account> accounts = new ArrayList<>();

        if(file.exists() && file.length() > 0){
                try (FileInputStream fileIn = new FileInputStream(fileName);
                     ObjectInputStream in = new ObjectInputStream(fileIn)) {

                    while (true) {
                        try {
                            Account account = (Account) in.readObject();
                            accounts.add(account);
                        } catch (EOFException e) {
                            break;
                        }
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }

        return accounts;
    }

    public static void saveHistory(List<PassHistory> passHistories){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileHistory);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (PassHistory passHistory : passHistories){
                objectOutputStream.writeObject(passHistory);
            }

            objectOutputStream.flush();

            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<PassHistory> retrieveHistory(){
        File file = new File(fileHistory);
        List<PassHistory> passHistories = new ArrayList<>();

        if(file.exists() && file.length() > 0){
            try (FileInputStream fileIn = new FileInputStream(fileHistory);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

                while (true) {
                    try {
                        PassHistory passHistory = (PassHistory) in.readObject();
                        passHistories.add(passHistory);
                    } catch (EOFException e) {
                        break;
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return passHistories;
    }
}
