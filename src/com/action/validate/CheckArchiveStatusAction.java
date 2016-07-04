package com.action.validate;

/**
 * Created by drc on 16-5-31.
 */
public class CheckArchiveStatusAction {
    private int status = -1;
    private boolean onLoan;
    private boolean inStore;

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public boolean isInStore() {
        return inStore;
    }

    private void check() {
        switch (status) {
            case 0:
                inStore = true;
                onLoan = false;
                break;
            case 1:
                inStore = false;
                onLoan = true;
                break;
        }
    }

    public String execute() {
        check();
        return "success";
    }
}
