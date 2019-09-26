package com.reiya.pixiv.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.reiya.pixiv.R;
import com.reiya.pixiv.adapter.ColorAdapter;
import com.reiya.pixiv.bean.Theme;

/**
 * Created by lenovo on 2016/1/27.
 */
public class ColorSelectDialog extends DialogFragment {
    private ColorAdapter.OnColorSelected onColorSelected;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getContext(), R.layout.layout_color, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        ColorAdapter adapter = new ColorAdapter(getContext());
        recyclerView.setAdapter(adapter);
        builder.setTitle(getString(R.string.theme_color))
                .setView(view);
        final Dialog dialog = builder.create();
        adapter.setOnSelected(new ColorAdapter.OnColorSelected() {
            @Override
            public void onSelected(String color, int code) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                editor.putString(getString(R.string.key_theme_color), color);
                editor.apply();
                Theme.set(color);
                Intent intent = getActivity().getIntent();
                getActivity().setResult(1, intent);
                onColorSelected.onSelected(color, code);
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public void setOnColorSelected(ColorAdapter.OnColorSelected onColorSelected) {
        this.onColorSelected = onColorSelected;
    }
}
