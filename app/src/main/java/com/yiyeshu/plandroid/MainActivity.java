package com.yiyeshu.plandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wakehao.bar.BottomNavigationBar;
import com.wakehao.bar.BottomNavigationItem;
import com.yiyeshu.plandroid.base.BaseActivity;
import com.yiyeshu.plandroid.module.fragment.BlankFragment;
import com.yiyeshu.plandroid.module.fragment.HomeFragment;
import com.yiyeshu.plandroid.module.fragment.MeFragment;
import com.yiyeshu.plandroid.module.fragment.NewsFragmen;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.bar)
    BottomNavigationBar bar;
    @BindView(R.id.frame_content)
    RelativeLayout frameContent;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
       //setSupportActionBar(toolbar);
        setToolbar(toolbar,"霹雳");

    }

    @Override
    protected void initData() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        setupDrawerContent(navigationView);
        setUpProfileImage();
    }

    protected void initListener() {

        bar.setOnNavigationItemSelectedListener(new BottomNavigationBar.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull BottomNavigationItem item, int selectedPosition) {
                return true;
            }

            @Override
            public void onNavigationItemSelectedAgain(@NonNull BottomNavigationItem item, int reSelectedPosition) {
                Log.e("TAG", "====: 1111111111111111111111111");
            }
        });


    }

    private void switchToBook() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        toolbar.setTitle(R.string.navigation_book);
    }

    private void switchToExample() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragmen()).commit();
        toolbar.setTitle(R.string.navigation_example);
    }

    private void switchToBlog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
        toolbar.setTitle(R.string.navigation_my_blog);
    }


    private void switchToAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MeFragment()).commit();
        toolbar.setTitle(R.string.navigation_about);
    }


    private void setUpProfileImage() {
        View headerView = navigationView.inflateHeaderView(R.layout.navigation_header);
        View profileView = headerView.findViewById(R.id.profile_image);
        if (profileView != null) {
            profileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToBlog();
                    drawerLayout.closeDrawers();
                    navigationView.getMenu().getItem(1).setChecked(true);
                }
            });
        }

    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.navigation_item_book:
                                switchToBook();
                                bar.setItemSelected(0);
                                break;
                            case R.id.navigation_item_example:
                                switchToExample();
                                bar.setItemSelected(1);
                                break;
                            case R.id.navigation_item_blog:
                                switchToBlog();
                                bar.setItemSelected(2);
                                break;
                            case R.id.navigation_item_about:
                                switchToAbout();
                                bar.setItemSelected(3);
                                break;

                        }
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

}
