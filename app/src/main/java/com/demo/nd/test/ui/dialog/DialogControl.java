package com.demo.nd.test.ui.dialog;

/**
 * Created by Administrator on 2015/7/9.
 */
public interface DialogControl {
    public abstract void hideWaitDialog();

    public abstract WaitDialog showWaitDialog();

    public abstract WaitDialog showWaitDialog(int resid);

    public abstract WaitDialog showWaitDialog(String text);
}
