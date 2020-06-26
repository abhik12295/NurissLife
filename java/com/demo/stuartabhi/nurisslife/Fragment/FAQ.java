package com.demo.stuartabhi.nurisslife.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demo.stuartabhi.nurisslife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FAQ extends Fragment {


    public FAQ() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActionBar().setTitle("FAQ");
        getActionBar().setSubtitle("Clear");
        ScrollView sv=(ScrollView)inflater.inflate(R.layout.fragment_faq,container,false);
        Button bt=(Button)sv.findViewById(R.id.faq1);
        Button bt1=(Button)sv.findViewById(R.id.faq2);
        Button bt2=(Button)sv.findViewById(R.id.faq3);
        final TextView tv=(TextView)sv.findViewById(R.id.tfaq1);
        final TextView tv1=(TextView)sv.findViewById(R.id.tfaq2);
        final TextView tv2=(TextView)sv.findViewById(R.id.tfaq3);
        tv.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility((tv.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setVisibility((tv1.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setVisibility((tv2.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
            }
        });
        return sv;
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }

}
