package com.reiya.pixiv.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.reiya.pixiv.R;
import com.reiya.pixiv.util.Value;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class FavoriteUserDialog extends DialogFragment {
    private ToAdd toAdd;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.concern))
                .setPositiveButton(getString(R.string.pub), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toAdd.add(Value.PUBLIC);
                    }
                })
                .setNegativeButton(getString(R.string.pri), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toAdd.add(Value.PRIVATE);
                    }
                })
                .setNeutralButton(getString(R.string.negative), null);
        return builder.create();
    }

    public interface ToAdd {
        void add(String type);
    }

    public void setToAdd(ToAdd toAdd) {
        this.toAdd = toAdd;
    }
}
