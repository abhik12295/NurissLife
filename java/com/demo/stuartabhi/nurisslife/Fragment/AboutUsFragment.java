package com.demo.stuartabhi.nurisslife.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.demo.stuartabhi.nurisslife.R;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */

public class AboutUsFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {

   // private TextView tv1,tv2;
    private SliderLayout mDemoSlider;


    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ScrollView root= (ScrollView) inflater.inflate(R.layout.fragment_about_us,container,false);
        getActionBar().setTitle("About Us");
        getActionBar().setSubtitle("Read more");
        HTextView hTextView=(HTextView)root.findViewById(R.id.abouttextlong);
        hTextView.setAnimateType(HTextViewType.TYPER);
        hTextView.animateText("Our Services");
        ImageView fb = (ImageView) root.findViewById(R.id.fb);

      //  tv1=(TextView)root.findViewById(R.id.abouttextlong);
       // tv2=(TextView)root.findViewById(R.id.abouttextsmall);

        mDemoSlider = (SliderLayout)root.findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Excellent Support",R.mipmap.aboutu);
        file_maps.put("On Time Delivery",R.mipmap.aboutimag);
        file_maps.put("Low Cost",R.mipmap.nuriis);
        file_maps.put("Bulk Order", R.mipmap.slider4);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                           .image(file_maps.get(name))
                                 .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(this);
                        //add your extra information
                         textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                     .putString("extra",name);
                        mDemoSlider.addSlider(textSliderView);
                     }

        //mDemoSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);
      //  mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        //mDemoSlider.setDuration(4000);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.facebook.com/nurisslifecare/");
                Intent fbintent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(fbintent);
            }
        });

        return root;
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }


        @Override
         public void onStop() {
            // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
                 mDemoSlider.stopAutoCycle();
                super.onStop();
             }


              @Override
    public void onSliderClick(BaseSliderView slider) {
                 Toast.makeText(getContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
             }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
