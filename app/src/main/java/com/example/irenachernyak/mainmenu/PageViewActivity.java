package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irenachernyak on 8/27/15.
 */
public class PageViewActivity extends AppCompatActivity {

    InfoPagerAdapter pagerAdapter;
    com.viewpagerindicator.CirclePageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_layout);
        this.setTitle(getString(R.string.before_after_pager_title));

        List<Fragment> fragments = getFragments();
        pagerAdapter = new InfoPagerAdapter(this, getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pagerAdapter);

        // ViewPager Indicator
        pageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        pageIndicator.setFillColor(R.color.button_material_dark);
        pageIndicator.setStrokeColor(R.color.button_material_dark);
//        pageIndicator.setFades(false);
        pageIndicator.setViewPager(pager);

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        ((VideoPageFragment)pagerAdapter.getItem(0)).stopVideo();
//    }

    private List<Fragment> getFragments(){
        List<Fragment> flist = new ArrayList<Fragment>();

        //to play videos
//        flist.add(VideoPageFragment.newInstance(getString(R.string.video2)));
//        flist.add(VideoPageFragment.newInstance(getString(R.string.video1)));

        // to display B&A pics
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_1));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_2));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_3));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_4));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_5));
        flist.add(ImagePageFragment.newInstance(R.drawable.before_and_after_6));

        return flist;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        ((VideoPageFragment)pagerAdapter.getItem(0)).playVideo();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        ((VideoPageFragment)pagerAdapter.getItem(0)).pauseVideo();
//    }

}
