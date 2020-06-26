package com.demo.stuartabhi.nurisslife.Login;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.Fragment.ShareFragment;
import com.demo.stuartabhi.nurisslife.NurissHome.ChatApp;
import com.demo.stuartabhi.nurisslife.NurissHome.TourProgram;
import com.demo.stuartabhi.nurisslife.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainHome extends Fragment {

    private Button btnLogout;
    private FirebaseAuth auth;
    public FirebaseAuth.AuthStateListener authlistener;
    private ConnectivityManager connMgr;
    private NetworkInfo networkInfo;
    private CardView cardView,cardView1;

    public MainHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActionBar().setTitle("Nuriss Home");
        getActionBar().setSubtitle("M.R.");
        auth = FirebaseAuth.getInstance();
        final FirebaseUser[] user = {FirebaseAuth.getInstance().getCurrentUser()};
        authlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user[0] = firebaseAuth.getCurrentUser();
                if (user[0] == null) {
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame, new ShareFragment()).addToBackStack(null).commit();

                }
            }
        };

        ScrollView sv2 = (ScrollView) inflater.inflate(R.layout.fragment_main_home, container, false);
       // txtName = (TextView) sv2.findViewById(R.id.name);
        //txtEmail = (TextView) sv2.findViewById(R.id.email);
        btnLogout = (Button) sv2.findViewById(R.id.btnLogout);
        cardView = (CardView) sv2.findViewById(R.id.cardview);
        cardView1 = (CardView) sv2.findViewById(R.id.cardview1);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fetch data
                signOut();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connMgr = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame, new TourProgram()).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(), "Please Connect to Internet and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connMgr = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame, new ChatApp()).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(), "Please Connect to Internet and try again", Toast.LENGTH_SHORT).show();
                }
            }


        });
        return sv2;
    }

    private void signOut() {
        auth.signOut();
    }


    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            connMgr = (ConnectivityManager) getActivity()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                // fetch data
                signOut();
            } else {
                // display error
                Toast.makeText(getContext(), "Please Connect to Internet and try again", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authlistener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authlistener != null) {
            auth.removeAuthStateListener(authlistener);
        }

    }
}
