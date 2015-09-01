package com.example.irenachernyak.mainmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VideoPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoPageFragment extends android.support.v4.app.Fragment {

    private static final String ARG_PARAM1 = "VideoPath";
    private String mParam1;
    private WebView videoView;

//    private OnFragmentInteractionListener mListener;


    public static VideoPageFragment newInstance(String videoPath) {
        VideoPageFragment fragment = new VideoPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, videoPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.video_page_fragment_layout, container, false);
        videoView = (WebView)view.findViewById(R.id.videoView1);
        mParam1 = getArguments().getString(ARG_PARAM1);

        videoView.setWebChromeClient(new WebChromeClient());
        videoView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        videoView.setWebViewClient(new WebViewClient());
        videoView.getSettings().setJavaScriptEnabled(true);
        String videoId = mParam1.substring(mParam1.lastIndexOf('/') + 1);
        String url = String.format("http://player.vimeo.com/video/%s?title=0&amp;amp;byline=1&amp;amp;autoplay=1&quot;", videoId);
//        videoView.loadUrl(mParam1);
        videoView.loadUrl(url);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        videoView.loadUrl("about:blank"); // stops audio when user taps back button
    }


}
