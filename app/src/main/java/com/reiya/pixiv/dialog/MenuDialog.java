package com.reiya.pixiv.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 2015/11/23 0023.
 */
public class MenuDialog extends DialogFragment {
    private String[] items;
    private DialogInterface.OnClickListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, listener);
        return builder.create();
    }

    public void setListener(String[] items, DialogInterface.OnClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        items = null;
        listener = null;
    }
}
