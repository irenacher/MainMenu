package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irenachernyak on 8/27/15.
 */
public class PageViewActivity extends AppCompatActivity {

    InfoPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_layout);
        this.setTitle(getString(R.string.before_after_pager_title));

        List<Fragment> fragments = getFragments();
        pagerAdapter = new InfoPagerAdapter(this, getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pagerAdapter);


    }

    private List<Fragment> getFragments(){
        List<Fragment> flist = new ArrayList<Fragment>();
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_1));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_2));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_3));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_4));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_5));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_6));

        return flist;
    }

}
