package com.demo.stuartabhi.nurisslife.Login;


import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.Fragment.ShareFragment;
import com.demo.stuartabhi.nurisslife.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {

    private Button btnRegister,btnResetPassword;
    private TextView btnLinkTologin;
    private TextInputEditText inputEmail,inputPassword;
    private ProgressDialog pDialog;
    private FirebaseAuth mfirebaseauth;
    private String email,password;
    private FragmentManager fragmentManager;


    public SignUp() {
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
        getActionBar().setTitle("Sign Up");
        getActionBar().setSubtitle("Report Here(M.R)");

        ScrollView sv1=(ScrollView) inflater.inflate(R.layout.fragment_sign_up,container,false);
        inputEmail=(TextInputEditText)sv1.findViewById(R.id.emailregister);
        inputPassword=(TextInputEditText)sv1.findViewById(R.id.passwordregister);
        btnRegister=(Button)sv1.findViewById(R.id.btn_register);
        btnLinkTologin=(TextView)sv1.findViewById(R.id.btnLinktoLogin);
        btnResetPassword=(Button)sv1.findViewById(R.id.btn_reset_password);
        pDialog=new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        mfirebaseauth= FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=inputEmail.getText().toString().trim();
                password=inputPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getContext(),"Enter Email address!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getContext(),"Enter Password!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<6)
                {
                    Toast.makeText(getContext(),"Password to short,enter minimun 6 characters!",Toast.LENGTH_LONG).show();
                    return;
                }
                pDialog.setMessage("Logging...");
                showDialog();
                //create user
                mfirebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getActivity(),"createUserwithEmail:onComplete"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                        hideDialog();
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(getActivity(),"Authentication failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            fragmentManager=getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame,new MainHome()).commit();
                        }
                    }
                });
            }
        });


        btnLinkTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //  getActivity().finish();
                fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,new ShareFragment()).commit();
            }
        });


        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,new ResetPassword()).commit();
            }
        });
        return sv1;
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
