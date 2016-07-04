package com.action;

/**
 * Created by drc on 16-5-26.
 */
public class ReturnArchiveAction extends SwitchArchiveStatusAction {
    @Override
    public boolean isResult() {
        return super.isResult();
    }

    @Override
    protected int getStatus() {
        return 0;
    }

    @Override
    protected int getAction() {
        return 2;
    }

    @Override
    protected boolean checkStatus(int status) {
        return status == 1;
    }

    public String execute() {
        return super.execute();
    }
}
