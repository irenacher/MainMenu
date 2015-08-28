package com.example.irenachernyak.mainmenu;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by irenachernyak on 8/27/15.
 */
public class InfoPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private Context context;

    public InfoPagerAdapter(Context ctxt, FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
        this.context = ctxt;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return "Before And After";
    }
}
