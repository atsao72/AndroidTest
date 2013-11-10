package com.androidtest.austin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment{
    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        final ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.level_select_menu, container, false);

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(R.id.levelSetText)).setText(getString(R.string.Level_Set) + Integer.toString(mPageNumber + 1));

        return rootView;
    }

    public int getPageNumber() {
        return mPageNumber;
    }

}
