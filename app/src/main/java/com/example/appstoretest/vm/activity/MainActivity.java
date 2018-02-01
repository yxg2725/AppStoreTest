package com.example.appstoretest.vm.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.appstoretest.R;
import com.example.appstoretest.vm.adapter.ViewPagerAdapter;

import static com.example.appstoretest.R.id.drawer;


public class MainActivity extends AppCompatActivity {

  private Toolbar mToolbar;
  private NavigationView navigationView;
  private DrawerLayout drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
  }

  private void initView() {
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);

    drawer = (DrawerLayout) findViewById(R.id.drawer);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,mToolbar,R.string.open,R.string.close);
    toggle.syncState();
    drawer.addDrawerListener(toggle);

    navigationView = (NavigationView) findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(listener);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(viewPagerAdapter);

    tabLayout.setupWithViewPager(viewPager);
    //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
  }


  private NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      item.setChecked(true);
      drawer.closeDrawers();
      return false;
    }
  };

}
