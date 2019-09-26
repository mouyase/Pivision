package com.reiya.pixiv.collection;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.reiya.pixiv.R;
import com.reiya.pixiv.base.BaseFragment;
import com.reiya.pixiv.bean.Theme;
import com.reiya.pixiv.dialog.BookmarkTagFilterDialog;
import com.reiya.pixiv.dialog.DownloadAllDialog;

public class FavoriteWorksActivity extends AppCompatActivity {
    private String mType;
    private FavoriteWorksFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Theme.getTheme());
        setContentView(R.layout.activity_favorite_works);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24px);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setCurrentPart(String type, FavoriteWorksFragment fragment) {
        mType = type;
        mFragment = fragment;
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private final String[] titles = new String[]{getString(R.string.pub), getString(R.string.pri)};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FavoriteWorksFragment.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 0, 0, R.string.concern).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, 0, 0, R.string.tag).setIcon(R.drawable.ic_label_white_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add(1, 1, 1, R.string.save_all_works).setIcon(R.drawable.ic_file_download_white_24px).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                BookmarkTagFilterDialog bookmarkTagFilterDialog = new BookmarkTagFilterDialog();
                bookmarkTagFilterDialog.setType(mType);
                bookmarkTagFilterDialog.setOnBookmarkTagSelectedCallback(mFragment);
                bookmarkTagFilterDialog.show(getSupportFragmentManager(), BookmarkTagFilterDialog.class.getName());
                break;
            case 1:
                DownloadAllDialog downloadAllDialog = new DownloadAllDialog();
                downloadAllDialog.setType(DownloadAllDialog.TYPE_BOOKMARK);
                downloadAllDialog.show(getSupportFragmentManager(), DownloadAllDialog.class.getName());
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
