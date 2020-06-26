package com.demo.stuartabhi.nurisslife.Fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.demo.stuartabhi.nurisslife.R;

import java.io.File;


public class Carrers extends Fragment {

    private TextView tv3,tv4;
    private static final int RESULT_LOAD_IMAGE=1;
    private static final int RESULT_LOAD_PDF=0;
    Uri URI=null;
    String attachmentFile;
    int columnIndex;
    private TextInputEditText ti1,ti2,ti3,ti4,ti5,ti6,ti7,ti8,ti9,ti10;
    public Carrers(){}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActionBar().setTitle("Career");
        getActionBar().setSubtitle("Your Chance");

        final ScrollView sv=(ScrollView)inflater.inflate(R.layout.activity_carrers,container,false);
        ti1=(TextInputEditText)sv.findViewById(R.id.studentfname);
        ti2=(TextInputEditText)sv.findViewById(R.id.studentlname);
        ti3=(TextInputEditText)sv.findViewById(R.id.studentdob);
        ti4=(TextInputEditText)sv.findViewById(R.id.studentstate);
        ti5=(TextInputEditText)sv.findViewById(R.id.stucity);
        ti6=(TextInputEditText)sv.findViewById(R.id.studentemail);
        ti7=(TextInputEditText)sv.findViewById(R.id.studentcontct);
        ti8=(TextInputEditText)sv.findViewById(R.id.sturesidentnum);
        ti9=(TextInputEditText)sv.findViewById(R.id.stucurrloc);
        ti10=(TextInputEditText)sv.findViewById(R.id.stucorrespondance);
        tv3=(TextView)sv.findViewById(R.id.pictv);
        tv4=(TextView)sv.findViewById(R.id.resumetv);
        final Button bt1=(Button)sv.findViewById(R.id.btn_browse);
        final Button bt2=(Button)sv.findViewById(R.id.btn_browse1);
        final Button btnsubmit=(Button)sv.findViewById(R.id.btn_submit);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent imageintent=new Intent();
                imageintent.setType("image/*");
                imageintent.setAction(Intent.ACTION_GET_CONTENT);
                imageintent.putExtra("return-data",true);
                startActivityForResult(Intent.createChooser(imageintent,"Complete action using"),RESULT_LOAD_IMAGE);

            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pdfi=new Intent();
                pdfi.setType("application/pdf");
                pdfi.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(pdfi,"Complete action using"),RESULT_LOAD_PDF);
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                final String sfname=ti1.getText().toString();
                final String slname=ti2.getText().toString();
                final String sdob=ti3.getText().toString();
                final String sstate=ti4.getText().toString();
                final String scity=ti5.getText().toString();
                final String semail=ti6.getText().toString();
                final String scontact=ti7.getText().toString();
                final String sresid=ti8.getText().toString();
                final String scurrent=ti9.getText().toString();
                final String scorrpo=ti10.getText().toString();
                final String sphoto=tv3.getText().toString();
                final String sresume=tv4.getText().toString();
                if(TextUtils.isEmpty(sfname))
                {
                    Toast.makeText(getActivity(),"Enter First name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(slname))
                {
                    Toast.makeText(getActivity(),"Enter Last name!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sdob))
                {
                    Toast.makeText(getActivity(),"Enter DOB!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sstate))
                {
                    Toast.makeText(getActivity(),"Enter State!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(scity))
                {
                    Toast.makeText(getActivity(),"Enter City!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(semail))
                {
                    Toast.makeText(getActivity(),"Enter Email!",Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(scontact))
                {
                    Toast.makeText(getActivity(),"Enter Contact No.!",Toast.LENGTH_SHORT).show();
                    return;
                }if(TextUtils.isEmpty(sresid))
                {
                    Toast.makeText(getActivity(),"Enter Residence No.!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(scurrent))
                {
                    Toast.makeText(getActivity(),"Enter Current Location!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(scorrpo))
                {
                    Toast.makeText(getActivity(),"Enter Correspondance Address!",Toast.LENGTH_SHORT).show();
                    return;
                }


                Log.i("Send Email", "");
                String[] TO = {"nurissmarket@gmail.com"};
                String[] CC = {""};
                Intent emailint=new Intent(Intent.ACTION_SEND);
                emailint.setData(Uri.parse("mailto:"));
                //emailint.setType("message/rfc822");
                emailint.setType("plain/text");
                //emailint.setType("application/image");
                emailint.putExtra(Intent.EXTRA_EMAIL, TO);
                emailint.putExtra(Intent.EXTRA_CC, CC);
                if(URI!=null)
                {
                    emailint.putExtra(Intent.EXTRA_STREAM,URI);
                }

                emailint.putExtra(Intent.EXTRA_SUBJECT, "Application");
                emailint.putExtra(Intent.EXTRA_TEXT, "Personal Details:\n\nFirst Name: "+sfname+"\nLast Name: "+slname
                        +"\nDOB: "+sdob+"\nState: "+sstate+"\nCity: "+scity+"\n\nContact Details:\n\nEmail: "+semail
                            +"\nContact Number: "+scontact+"\nResident Number: "+sresid+"\nCurrent Location: "+scurrent
                                +"\nCorrespondance Address: "+scorrpo);

                    startActivity(Intent.createChooser(emailint, "Send Email..."));
                    getActivity().finish();
                    Log.i("Finished sending mail", "");
                } catch (Throwable e) {
                    Toast.makeText(getContext(), "Request Failed Try Again!", Toast.LENGTH_SHORT).show();
                }
        }
        });
        return sv;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_LOAD_IMAGE && resultCode==Activity.RESULT_OK) {
             //  Uri uri = data.getData();
           // String filepath1=uri.getPath();
            //tv4.setText(filepath1);
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            attachmentFile = cursor.getString(columnIndex);
            Log.e("Attachment Path:", attachmentFile);
            URI = Uri.parse("file://" + attachmentFile);
            cursor.close();
            tv3.setText(filePathColumn[0]);
            }
           //else if (requestCode == RESULT_LOAD_PDF && resultCode==Activity.RESULT_OK)  {
        else{
         Uri uri = data.getData();
            String filepath1=uri.getPath();
            tv4.setText(filepath1);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)getActivity()).getSupportActionBar();
    }
}
