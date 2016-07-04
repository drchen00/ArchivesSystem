package com.action;

/**
 * Created by drc on 16-5-26.
 */
public class LoanArchiveAction extends SwitchArchiveStatusAction {
    @Override
    protected boolean checkStatus(int status) {
        return status == 0;
    }

    @Override
    public boolean isResult() {
        return super.isResult();
    }

    @Override
    protected int getStatus() {
        return 1;
    }

    @Override
    protected int getAction() {
        return 1;
    }

    public String execute() {
        return super.execute();
    }
}
