package com.demo.stuartabhi.nurisslife.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demo.stuartabhi.nurisslife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Latestarticle extends Fragment {

private TextView tv1,tv2,tv3;

    public Latestarticle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final ScrollView sv= (ScrollView) inflater.inflate(R.layout.fragment_latestarticle,container,false);
        getActionBar().setTitle("Latest Articles");
        getActionBar().setSubtitle("Subscribe Here");
        tv1=(TextView)sv.findViewById(R.id.imageviewc1);
        tv2=(TextView)sv.findViewById(R.id.imageviewc2);
        tv3=(TextView)sv.findViewById(R.id.imageviewc3);
        TextView textView1=(TextView)sv.findViewById(R.id.articlec);
        textView1.setSelected(true);

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
        return sv;
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}
