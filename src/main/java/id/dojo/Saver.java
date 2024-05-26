package id.dojo;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Saver {
    public static String fileName;
    public static String fileHistory;

    public static void saveObject(List<Account> accounts){
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);

            for (Account account : accounts){
                objectOutputStream.writeObject(account);
            }

            String base64 = Base64.getEncoder().encodeToString(byteOut.toByteArray());

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(base64.getBytes());

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
                try {
                    FileInputStream fileIn = new FileInputStream(fileName);
                    byte[] base64Bytes = new byte[fileIn.available()];
                    fileIn.read(base64Bytes);

                    byte[] objectBytes = Base64.getDecoder().decode(new String(base64Bytes));
                    ByteArrayInputStream byteIn = new ByteArrayInputStream(objectBytes);
                    ObjectInputStream in = new ObjectInputStream(byteIn);

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
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOut);

            for (PassHistory passHistory : passHistories){
                objectOutputStream.writeObject(passHistory);
            }

            String base64 = Base64.getEncoder().encodeToString(byteOut.toByteArray());

            FileOutputStream fileOutputStream = new FileOutputStream(fileHistory);
            fileOutputStream.write(base64.getBytes());

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
            try {
                FileInputStream fileIn = new FileInputStream(fileHistory);

                byte[] base64Bytes = new byte[fileIn.available()];
                fileIn.read(base64Bytes);

                byte[] objectBytes = Base64.getDecoder().decode(new String(base64Bytes));
                ByteArrayInputStream byteIn = new ByteArrayInputStream(objectBytes);

                ObjectInputStream in = new ObjectInputStream(byteIn);

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
