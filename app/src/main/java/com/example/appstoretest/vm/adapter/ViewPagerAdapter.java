package com.example.appstoretest.vm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.appstoretest.R;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.vm.fragment.FragmentFactory;

/**
 * Created by ant on 2018/1/31.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

  private final String[] titles;

  public ViewPagerAdapter(FragmentManager fm) {
    super(fm);

    titles = UIUtils.getStrings(R.array.content_title);
  }

  @Override
  public Fragment getItem(int position) {
    Fragment fragment = FragmentFactory.createFragment(position);
    return fragment;
  }

  @Override
  public int getCount() {
    return titles.length;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titles[position];
  }
}
