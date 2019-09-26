package com.reiya.pixiv.other;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reiya.pixiv.R;
import com.reiya.pixiv.adapter.ColorAdapter;
import com.reiya.pixiv.bean.Theme;
import com.reiya.pixiv.dialog.ColorSelectDialog;
import com.reiya.pixiv.dialog.PathSelectDialog;
import com.reiya.pixiv.util.IO;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Theme.getTheme());
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);

        getFragmentManager().beginTransaction().replace(R.id.fragment, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        Preference preferenceCache;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_settings);

            preferenceCache = findPreference(getString(R.string.key_current_cache_size));
            preferenceCache.setSummary(IO.getImageCacheSize());
            findPreference(getString(R.string.key_columns_count)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    getActivity().setResult(2, getActivity().getIntent());
                    return true;
                }
            });
            findPreference(getString(R.string.key_list_style)).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    getActivity().setResult(3, getActivity().getIntent());
                    return true;
                }
            });
        }

        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            if (preference.getKey().equals(getString(R.string.key_current_cache_size))) {
                final String s = IO.getImageCacheSize();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(getActivity()).clearDiskCache();
                        if (getActivity() == null) {
                            return;
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.cleared) + s + getString(R.string.pic_cache), Toast.LENGTH_SHORT).show();
                                preferenceCache.setSummary(IO.getImageCacheSize());
                            }
                        });
                    }
                });
            } else if (preference.getKey().equals(getString(R.string.key_path))) {
                PathSelectDialog pathSelectDialog = new PathSelectDialog();
                pathSelectDialog.show(((FragmentActivity) getActivity()).getSupportFragmentManager(), "Path");
            } else if (preference.getKey().equals(getString(R.string.key_theme_color))) {
                ColorSelectDialog colorSelectDialog = new ColorSelectDialog();
                colorSelectDialog.setOnColorSelected(new ColorAdapter.OnColorSelected() {
                    @Override
                    public void onSelected(String color, int code) {
                        Activity activity = getActivity();
                        activity.finish();
                        Intent intent = activity.getIntent();
                        activity.startActivity(intent);
                        activity.overridePendingTransition(0, 0);
                    }
                });
                colorSelectDialog.show(((FragmentActivity) getActivity()).getSupportFragmentManager(), "Color");
            }
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
