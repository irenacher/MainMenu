package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by irenachernyak on 8/27/15.
 */
public class ImagePageFragment extends Fragment{

    public static final ImagePageFragment newInstance(int  resourceId){

        ImagePageFragment f = new ImagePageFragment();

        Bundle bdl = new Bundle(1);

        bdl.putInt("ImageId", resourceId);

        f.setArguments(bdl);
        f.setHasOptionsMenu(true);

        return f;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.image_page_fragment_layout, container, false);
        ImageView iv = (ImageView)view.findViewById(R.id.infoImageView);
        int resourceId = getArguments().getInt("ImageId", 0);
        iv.setImageResource(resourceId);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
