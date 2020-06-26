package com.demo.stuartabhi.nurisslife.Fragment;




import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.Login.MainHome;
import com.demo.stuartabhi.nurisslife.Login.ResetPassword;
import com.demo.stuartabhi.nurisslife.Login.SignUp;
import com.demo.stuartabhi.nurisslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment  {

    private TextInputEditText inputemail,inputpass;
    private FirebaseAuth fauth;
    private Button btnLogin,btnReset;
    private TextView tv1;
    private ProgressDialog pDialog;

    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fauth=FirebaseAuth.getInstance();
        if(fauth.getCurrentUser()!=null)
        {
            FragmentManager fm1 = getFragmentManager();
            fm1.beginTransaction().replace(R.id.content_frame,new MainHome()).addToBackStack(null).commit();
        }
        getActionBar().setTitle("Nuriss Talk");
        getActionBar().setSubtitle("Report Here(M.R)");
        ScrollView sv=(ScrollView)inflater.inflate(R.layout.fragment_share,container,false);
        inputemail=(TextInputEditText)sv.findViewById(R.id.input_email);
        inputpass=(TextInputEditText)sv.findViewById(R.id.input_password);
        tv1=(TextView)sv.findViewById(R.id.link_signup);
        btnLogin=(Button)sv.findViewById(R.id.btn_login);
        btnReset=(Button)sv.findViewById(R.id.btn_reset_password);
        pDialog=new ProgressDialog(getActivity());
        pDialog.setCancelable(false);


        fauth=FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame,new ResetPassword()).addToBackStack(null).commit();

                } else {
                    // display error
                    Toast.makeText(getActivity(),"Please connect to internet and try again",Toast.LENGTH_LONG).show();
                }


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                    final String email=inputemail.getText().toString();
                    final String password=inputpass.getText().toString();
                    if(TextUtils.isEmpty(email))
                    {
                        Toast.makeText(getActivity(),"Enter Email address!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password))
                    {
                        Toast.makeText(getActivity(),"Enter password!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pDialog.setMessage("Logging...");
                    showDialog();

                    //authenticate user
                    fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            hideDialog();
                            if(!task.isSuccessful())
                            {
                                if(password.length()<6)
                                {
                                    inputpass.setError(getString(R.string.minimum_password));
                                }
                                else
                                {
                                    Toast.makeText(getActivity(),getString(R.string.auth_failed),Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                FragmentManager fm = getFragmentManager();
                                fm.beginTransaction().replace(R.id.content_frame,new MainHome()).addToBackStack(null).commit();
                            }
                        }
                    });

                } else {
                    // display error
                    Toast.makeText(getActivity(),"Please connect to internet and try again",Toast.LENGTH_LONG).show();
                }

            }
        });


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame,new SignUp()).addToBackStack(null).commit();

                } else {
                    // display error
                    Toast.makeText(getActivity(),"Please connect to internet and try again",Toast.LENGTH_LONG).show();
                }
            }
        });

        return sv;
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
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }
}