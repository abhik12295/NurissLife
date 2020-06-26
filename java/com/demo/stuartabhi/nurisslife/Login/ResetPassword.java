package com.demo.stuartabhi.nurisslife.Login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.Fragment.ShareFragment;
import com.demo.stuartabhi.nurisslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassword extends Fragment {

    private EditText inputemail;
    private Button btnReset,btnBack;
    private FirebaseAuth auth;
    private ProgressDialog pDialog;


    public ResetPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActionBar().setTitle("Reset Password");
        getActionBar().setSubtitle("Access again");
        ScrollView scrollView=(ScrollView)inflater.inflate(R.layout.fragment_reset_password,container,false);
        inputemail=(EditText)scrollView.findViewById(R.id.email);
        btnReset=(Button)scrollView.findViewById(R.id.btn_reset_password);
        btnBack=(Button)scrollView.findViewById(R.id.btn_back);
        pDialog=new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        auth=FirebaseAuth.getInstance();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame,new ShareFragment()).addToBackStack(null).commit();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputemail.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getActivity(),"Enter your registered email id!",Toast.LENGTH_LONG).show();
                    return;
                }
                pDialog.setMessage("Wait..");
                showDialog();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(getActivity(),"We have sent you instructions to reset your password!",Toast.LENGTH_LONG).show();
                   }
                        else
                   {
                       Toast.makeText(getActivity(),"Failed to send reset email! Try again",Toast.LENGTH_LONG).show();
                   }
                        hideDialog();
                    }
                });
            }
        });
        return scrollView;
    }
    private void showDialog() {
        if(!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog()
    {
        if(pDialog.isShowing())
            pDialog.dismiss();
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

}
