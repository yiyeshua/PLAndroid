package com.yiyeshu.plandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wakehao.bar.BottomNavigationBar;
import com.wakehao.bar.BottomNavigationItem;
import com.yiyeshu.plandroid.fragment.BlankFragment;
import com.yiyeshu.plandroid.fragment.HomeFragment;
import com.yiyeshu.plandroid.fragment.MeFragment;
import com.yiyeshu.plandroid.fragment.NewsFragmen;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private AppBarLayout appbar;
    private FrameLayout fragmentContainer;
    private BottomNavigationBar bar;
    private RelativeLayout frameContent;
    private CoordinatorLayout mainContent;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        setupDrawerContent(navigationView);
        setUpProfileImage();

       // switchToBook();
       // https://github.com/WakeHao/BottomNavigationBar
        initListener();
    }

    private void initListener() {

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


    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        bar = (BottomNavigationBar) findViewById(R.id.bar);
        frameContent = (RelativeLayout) findViewById(R.id.frame_content);
        mainContent = (CoordinatorLayout) findViewById(R.id.main_content);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}
