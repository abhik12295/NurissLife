package com.demo.stuartabhi.nurisslife.NurissHome;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.Login.MainHome;
import com.demo.stuartabhi.nurisslife.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourProgram extends Fragment {

    private int year,day,month;
    private TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tperson;
    private EditText etname,etdrname,etdrname1,etdrname2,etdrname3,etdrname4,etdrname5;
    private EditText etvarea,etvarea1,etvarea2,etvarea3,etvarea4,etvarea5,etmedname;
    private DatePicker pick;
    private TimePicker tpicker,tpicker1,tpicker2,tpicker3,tpicker4,tpicker5;
    private Button tbutton,tbutton1,tbutton2,tbutton3,tbutton4,tbutton5,savebtn;
    private RadioGroup radioGroup;
    int selectedId=0;
    private RadioButton radioButton;
    private String format = "";
    final private static String FIREBASE_URL="https://nuriss-life.firebaseio.com/";



    public TourProgram() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActionBar().setTitle("Tour Program");
        getActionBar().setSubtitle("Submit Report");
        final ScrollView sv=(ScrollView)inflater.inflate(R.layout.fragment_tour_program,container,false);

        Firebase.setAndroidContext(this.getContext());

        etname=(EditText)sv.findViewById(R.id.tour_name);
        //Doctor name
        etdrname=(EditText)sv.findViewById(R.id.dr_name);
        etdrname1=(EditText)sv.findViewById(R.id.dr_name1);
        etdrname2=(EditText)sv.findViewById(R.id.dr_name2);
        etdrname3=(EditText)sv.findViewById(R.id.dr_name3);
        etdrname4=(EditText)sv.findViewById(R.id.dr_name4);
        etdrname5=(EditText)sv.findViewById(R.id.dr_name5);
        //Visiting Area
        etvarea=(EditText)sv.findViewById(R.id.dr_area);
        etvarea1=(EditText)sv.findViewById(R.id.dr_area1);
        etvarea2=(EditText)sv.findViewById(R.id.dr_area2);
        etvarea3=(EditText)sv.findViewById(R.id.dr_area3);
        etvarea4=(EditText)sv.findViewById(R.id.dr_area4);
        etvarea5=(EditText)sv.findViewById(R.id.dr_area5);
        //Doctor Shop
        etmedname=(EditText)sv.findViewById(R.id.dr_shop);

        pick=(DatePicker)sv.findViewById(R.id.datepicker1);
       //Time picker
        tpicker=(TimePicker)sv.findViewById(R.id.time_picker);
        tpicker1=(TimePicker)sv.findViewById(R.id.time_picker1);
        tpicker2=(TimePicker)sv.findViewById(R.id.time_picker2);
        tpicker3=(TimePicker)sv.findViewById(R.id.time_picker3);
        tpicker4=(TimePicker)sv.findViewById(R.id.time_picker4);
        tpicker5=(TimePicker)sv.findViewById(R.id.time_picker5);

        radioGroup=(RadioGroup)sv.findViewById(R.id.radiogroup1);

        pick.setMinDate(System.currentTimeMillis()-1000);
        pick.setMaxDate(System.currentTimeMillis()+1000);
       //Date Picked
        tv=(TextView)sv.findViewById(R.id.date_picked);
        //Time Picked
        tv1=(TextView)sv.findViewById(R.id.time_picked);
        tv2=(TextView)sv.findViewById(R.id.time_picked1);
        tv3=(TextView)sv.findViewById(R.id.time_picked2);
        tv4=(TextView)sv.findViewById(R.id.time_picked3);
        tv5=(TextView)sv.findViewById(R.id.time_picked4);
        tv6=(TextView)sv.findViewById(R.id.time_picked5);
        //Time changed
        tbutton=(Button)sv.findViewById(R.id.time_button);
        tbutton1=(Button)sv.findViewById(R.id.time_button1);
        tbutton2=(Button)sv.findViewById(R.id.time_button2);
        tbutton3=(Button)sv.findViewById(R.id.time_button3);
        tbutton4=(Button)sv.findViewById(R.id.time_button4);
        tbutton5=(Button)sv.findViewById(R.id.time_button5);

        tperson=(TextView)sv.findViewById(R.id.textViewPersons);
        //Submit Button
        savebtn=(Button)sv.findViewById(R.id.btn_save_tour);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        showTime(hour, min);

        tv.setText(new StringBuilder().append(month + 1).append("/").append(day).append("/")
                .append(year).append(" "));
        pick.init(year,month,day,null);

        tbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText(getCurrentTime());
            }
        });
        tbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setText(getCurrentTime1());
            }
        });
        tbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setText(getCurrentTime2());
            }
        });
        tbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv4.setText(getCurrentTime3());
            }
        });
        tbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv5.setText(getCurrentTime4());
            }
        });
        tbutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv6.setText(getCurrentTime5());
            }
        });



        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



              /*  AlertDialog.Builder  adb=new AlertDialog.Builder(view.getContext())
                        .setTitle("Please Wait")
                        .setMessage("Submitting response....").setCancelable(false);
                final AlertDialog ad=adb.create();
                ad.show();
                Timer t=new Timer();
                t.schedule(new CloseDialogTimerTask(ad),5000);*/

                if(radioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getContext(),"Please select",Toast.LENGTH_LONG).show();
                }
                else{

                    selectedId=radioGroup.getCheckedRadioButtonId();
                    radioButton=(RadioButton)sv.findViewById(selectedId);
                   // Toast.makeText(getContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
                }

                Firebase ref=new Firebase(FIREBASE_URL);

                String mrname=etname.getText().toString().trim();

                String drname=etdrname.getText().toString().trim();
                String drname1=etdrname1.getText().toString().trim();
                String drname2=etdrname2.getText().toString().trim();
                String drname3=etdrname3.getText().toString().trim();
                String drname4=etdrname4.getText().toString().trim();
                String drname5=etdrname5.getText().toString().trim();

                String vararea=etvarea.getText().toString().trim();
                String vararea1=etvarea1.getText().toString().trim();
                String vararea2=etvarea2.getText().toString().trim();
                String vararea3=etvarea3.getText().toString().trim();
                String vararea4=etvarea4.getText().toString().trim();
                String vararea5=etvarea5.getText().toString().trim();

                String visitdate=tv.getText().toString().trim();

                String vclinic=etmedname.getText().toString().trim();

               String nodr=radioButton.getText().toString();

                String visitime1=tv1.getText().toString().trim();
                String visitime2=tv2.getText().toString().trim();
                String visitime3=tv3.getText().toString().trim();
                String visitime4=tv4.getText().toString().trim();
                String visitime5=tv5.getText().toString().trim();
                String visitime6=tv6.getText().toString().trim();


                final Details detail=new Details();
                detail.setMrname(mrname);
                detail.setDrname(drname);
                detail.setDrname1(drname1);
                detail.setDrname2(drname2);
                detail.setDrname3(drname3);
                detail.setDrname4(drname4);
                detail.setDrname5(drname5);
                detail.setVararea(vararea);
                detail.setVararea1(vararea1);
                detail.setVararea2(vararea2);
                detail.setVararea3(vararea3);
                detail.setVararea4(vararea4);
                detail.setVararea5(vararea5);
                detail.setVisitdate(visitdate);
                detail.setVisitime1(visitime1);
                detail.setVisitime2(visitime2);
                detail.setVisitime3(visitime3);
                detail.setVisitime4(visitime4);
                detail.setVisitime5(visitime5);
                detail.setVisitime6(visitime6);
                detail.setVclinic(vclinic);
                detail.setNodr(nodr);
                if(vclinic=="")
                {
                    Toast.makeText(getActivity(),"Please select dr.",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(mrname))
                {
                    Toast.makeText(getActivity(),"Please mention your Full name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(drname))
                {
                    Toast.makeText(getActivity(),"Please mention Doctor's name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(vararea))
                {
                    Toast.makeText(getActivity(),"Please mention Visting area!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(visitime1))
                {
                    Toast.makeText(getActivity(),"Please mention visit time!",Toast.LENGTH_SHORT).show();
                    return;
                }



                ref.child("Details").push().setValue(detail);
                if(detail.toString()==null)
                {
                 Toast.makeText(getContext(),"Try again",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"Successfully submit response",Toast.LENGTH_LONG).show();
                    FragmentManager fm=getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame, new MainHome()).addToBackStack(null).commit();
                }

            }
        });
        return sv;
    }


    private String getCurrentTime() {
        String currentTime="Visit Time: "+tpicker.getCurrentHour()+":"+tpicker.getCurrentMinute();
        return currentTime;
    }
    private String getCurrentTime1() {
        String currentTime="Visit Time: "+tpicker1.getCurrentHour()+":"+tpicker1.getCurrentMinute();
        return currentTime;
    }
    private String getCurrentTime2() {
        String currentTime="Visit Time: "+tpicker2.getCurrentHour()+":"+tpicker2.getCurrentMinute();
        return currentTime;
    }
    private String getCurrentTime3() {
        String currentTime="Visit Time: "+tpicker3.getCurrentHour()+":"+tpicker3.getCurrentMinute();
        return currentTime;
    }
    private String getCurrentTime4() {
        String currentTime="Visit Time: "+tpicker4.getCurrentHour()+":"+tpicker4.getCurrentMinute();
        return currentTime;
    }
    private String getCurrentTime5() {
        String currentTime="Visit Time: "+tpicker5.getCurrentHour()+":"+tpicker5.getCurrentMinute();
        return currentTime;
    }


    public void setTime(View view) {
        int hour =tpicker.getCurrentHour();
        int min = tpicker.getCurrentMinute();
        showTime(hour, min);
    }

    private void showTime(int hour, int min) {

        if (hour == 0) {
            hour += 12;
            format = "AM";
        }
        else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        tv1.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
        tv2.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
        tv3.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
        tv4.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
        tv5.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
        tv6.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));

    }

    private ActionBar getActionBar() {
            return ((AppCompatActivity)getActivity()).getSupportActionBar();
        }



    private class CloseDialogTimerTask extends TimerTask {
            private AlertDialog ad;
        public CloseDialogTimerTask(AlertDialog ad) {
            this.ad=ad;
        }

        @Override
        public void run() {
            if(ad.isShowing())
            {
                ad.dismiss();
            }
        }
    }
}
