package com.demo.stuartabhi.nurisslife.Appintro1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.stuartabhi.nurisslife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleLayout extends Fragment {

        private static final String ARG_LAYOUT_RES_ID = "layoutResId";


    public static SampleLayout newInstance(int layoutResId) {
            SampleLayout sampleSlide = new SampleLayout();
            Bundle args = new Bundle();
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
            sampleSlide.setArguments(args);
            return sampleSlide;
        }

        private int layoutResId;

      public SampleLayout() {}

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID))
                layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(layoutResId, container, false);
        }

}
