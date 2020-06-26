package com.demo.stuartabhi.nurisslife.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.demo.stuartabhi.nurisslife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment {


    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActionBar().setTitle("Contact");
        getActionBar().setSubtitle("Keep in Touch");

        ScrollView sv=(ScrollView)inflater.inflate(R.layout.fragment_contact_us,container,false);
        final EditText ename=(EditText)sv.findViewById(R.id.contact_name);
        final EditText eemail=(EditText)sv.findViewById(R.id.contact_email);
        final EditText etext=(EditText)sv.findViewById(R.id.msg_leave);
        final Button send=(Button)sv.findViewById(R.id.btn_send);
        final TextView phone=(TextView)sv.findViewById(R.id.phone1);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Intent.ACTION_CALL);
                intent1.setData(Uri.parse("tel:+91 9557627315"));
                startActivity(intent1);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ename.getText().toString().trim();
                String email=eemail.getText().toString().trim();
                String message=etext.getText().toString().trim();
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                String[] TO = {"nurissmarket@gmail.com"};
                intent.setType("message/rfc822");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,TO);
                intent.putExtra(Intent.EXTRA_TEXT,name);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                startActivity(Intent.createChooser(intent,email));
            }
        });
        return sv;
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }

}
