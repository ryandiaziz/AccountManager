package id.dojo;

import java.io.Serializable;

public class PassHistory implements Serializable {
    final private String changeAt;
    final private String accountName;
    final private String oldPass;
    final private String newPass;

    public PassHistory(String accountName, String oldPass, String newPass){
        this.accountName = accountName;
        this.changeAt = Utils.getDate();
        this.oldPass = oldPass;
        this.newPass = newPass;
    }

    public String getChangeAt() {
        return changeAt;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getOldPass() {
        return oldPass;
    }

    public String getNewPass() {
        return newPass;
    }
}
