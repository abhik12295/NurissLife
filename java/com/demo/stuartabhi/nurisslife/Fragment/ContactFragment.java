package com.demo.stuartabhi.nurisslife.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.demo.stuartabhi.nurisslife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {
    private CustomMapFragment mapFragment;
    private FragmentTransaction transaction;


    public ContactFragment() {
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
        View rootView=inflater.inflate(R.layout.fragment_contact,container,false);
        getActionBar().setTitle("Map");
        getActionBar().setSubtitle("Reach To Us");
        mapFragment=new CustomMapFragment();
        transaction=getChildFragmentManager().beginTransaction();
        transaction.add(R.id.map_container,mapFragment).commit();
        return rootView;
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }
}
