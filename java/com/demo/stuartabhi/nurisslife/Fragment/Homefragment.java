package com.demo.stuartabhi.nurisslife.Fragment;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.demo.stuartabhi.nurisslife.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homefragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private ImageView im1,im2,im3,im4,im5,im6,im7,im8,im9,im10,im11,im12,im13,im14,im15,im16,imhome;
    private ImageView ia1,ia2,ia3;
    private static final String TAG = "Homefragment";
    private Button bt1;
    private TextView tv1,tv2,tv3;
    private SliderLayout mDemoSlider1;


    public Homefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActionBar().setTitle("Nuriss Lifecare");
        getActionBar().setSubtitle("Start Here");
        // Inflate the layout for this fragment
        final View sv=inflater.inflate(R.layout.fragment_homefragment,container,false);
        TextView textView1=(TextView)sv.findViewById(R.id.article);
        textView1.setSelected(true);

        im1=(ImageView)sv.findViewById(R.id.image1);
        im2=(ImageView)sv.findViewById(R.id.image2);
        im3=(ImageView)sv.findViewById(R.id.image3);
        im4=(ImageView)sv.findViewById(R.id.image4);
        im5=(ImageView)sv.findViewById(R.id.image5);
        im6=(ImageView)sv.findViewById(R.id.image6);
        im7=(ImageView)sv.findViewById(R.id.image7);
        im8=(ImageView)sv.findViewById(R.id.image8);
        im9=(ImageView)sv.findViewById(R.id.image9);
        im10=(ImageView)sv.findViewById(R.id.image10);
        im11=(ImageView)sv.findViewById(R.id.image11);
        im12=(ImageView)sv.findViewById(R.id.image12);
        im13=(ImageView)sv.findViewById(R.id.image13);
        im14=(ImageView)sv.findViewById(R.id.image14);
        im15=(ImageView)sv.findViewById(R.id.image15);
        im16=(ImageView)sv.findViewById(R.id.image16);
        imhome=(ImageView)sv.findViewById(R.id.image_home);

        ia1=(ImageView)sv.findViewById(R.id.art1);
        ia2=(ImageView)sv.findViewById(R.id.art2);
        ia3=(ImageView)sv.findViewById(R.id.art3);

        bt1=(Button)sv.findViewById(R.id.home_here);

        //Animation to products

        im1.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im2.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im3.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im4.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im5.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im6.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im7.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im8.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im9.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im10.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im11.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im12.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im13.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadeout));
        im14.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im15.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        im16.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        imhome.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        ia1.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        ia2.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));
        ia3.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fadein));

        tv1=(TextView)sv.findViewById(R.id.imageview1);
        tv2=(TextView)sv.findViewById(R.id.imageview2);
        tv3=(TextView)sv.findViewById(R.id.imageview3);

        mDemoSlider1 = (SliderLayout)sv.findViewById(R.id.slider1);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Rabriza DSR",R.mipmap.medic);
        file_maps.put("We Care",R.mipmap.metrix_screen);
        file_maps.put("Urnifer",R.mipmap.m);
        file_maps.put("Taxnuris-S", R.mipmap.cine);
        file_maps.put("Our Vision",R.mipmap.doctors);
        file_maps.put("Our Vision",R.mipmap.doctors);
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


            mDemoSlider1.addSlider(textSliderView);
        }

        mDemoSlider1.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider1.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider1.setDuration(4000);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://www.medicalnewstoday.com/articles/274295.php";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://www.medicalnewstoday.com/articles/274410.php";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://www.medicalnewstoday.com/articles/274351.php";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,new AboutUsFragment()).addToBackStack(null).commit();

            }
        });
        return sv;

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider1.stopAutoCycle();
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