package com.demo.stuartabhi.nurisslife;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.stuartabhi.nurisslife.Fragment.AboutUsFragment;
import com.demo.stuartabhi.nurisslife.Fragment.Carrers;
import com.demo.stuartabhi.nurisslife.Fragment.ContactFragment;
import com.demo.stuartabhi.nurisslife.Fragment.ContactUs;
import com.demo.stuartabhi.nurisslife.Fragment.FAQ;
import com.demo.stuartabhi.nurisslife.Fragment.Homefragment;
import com.demo.stuartabhi.nurisslife.Fragment.Latestarticle;
import com.demo.stuartabhi.nurisslife.Fragment.ProductsFragment;
import com.demo.stuartabhi.nurisslife.Fragment.ShareFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CharSequence mtitle,mtitle1;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private Fragment fragment=null;
    private DrawerLayout drawer;
    private MenuItem refreshMenuItem;


    //For Internet Connection
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    private Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;
    private boolean internetConnected=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mtitle="Nuriss Life Care";
        mtitle1="Start Here";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendemail();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(null==savedInstanceState) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Homefragment()).commit();
            getSupportActionBar().setTitle(mtitle);
            getSupportActionBar().setSubtitle(mtitle1);

        }

    }

    private void sendemail() {
        Log.i("Send Email", "");
        String[] TO = {"nurissmarket@gmail.com"};
        String[] CC = {""};
        Intent emailintent = new Intent(Intent.ACTION_SEND);
        emailintent.setData(Uri.parse("mailto:"));
        emailintent.setType("message/rfc822");
        emailintent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailintent.putExtra(Intent.EXTRA_CC, CC);
        emailintent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject:");
        emailintent.putExtra(Intent.EXTRA_TEXT, "Email message");
        try {
            startActivity(Intent.createChooser(emailintent, "Send Email..."));
            finish();
            Log.i("Finished sending mail", "");
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "No email client installed", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
                else{
                    super.onBackPressed();
                }
            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homemain, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {

            String url="http://www.nurisslifecare.com";
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
            return true;
        }
        if(id==R.id.menu_refresh)
        {
            // refresh
            refreshMenuItem = item;
            refreshMenuItem.setActionView(R.layout.action_progressbar);
            refreshMenuItem.expandActionView();

            // load the data from server
            SyncData task= new SyncData();
            task.execute("test");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SyncData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // not making real request in this demo
            // for now we use a timer to wait for sometime
            try {
                Thread.sleep(3000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            refreshMenuItem.collapseActionView();
            // remove the progress bar view
            refreshMenuItem.setActionView(null);
        }
    };




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            fragment=new Homefragment();
        } else if (id == R.id.nav_aboutus) {
            fragment=new AboutUsFragment();

        } else if (id == R.id.nav_products) {
            fragment=new ProductsFragment();

        } else if (id == R.id.nav_career) {
            fragment = new Carrers();

        } else if (id == R.id.nav_faq) {
            fragment=new FAQ();

        } else if (id == R.id.nav_share) {
            fragment=new ShareFragment();

        } else if (id == R.id.nav_contact) {
            fragment=new ContactFragment();
        }
        else if (id == R.id.nav_contact1) {
            fragment = new ContactUs();
        }

        else if(id==R.id.nav_latest_article)
        {
            fragment=new Latestarticle();
        }
        if(fragment!= null)
        {
            fragmentManager=getSupportFragmentManager();
           fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).addToBackStack("null").commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     *  Method to register runtime broadcast receiver to show snackbar alert for internet connection..
     */
    private void registerInternetCheckReceiver() {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, internetFilter);
    }

    /**
     *  Runtime Broadcast receiver inner class to capture internet connectivity events
     */
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = getConnectivityStatusString(context);
            setSnackbarMessage(status,false);
        }
    };

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = getConnectivityStatus(context);
        String status = null;
        if (conn == TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }
    private void setSnackbarMessage(String status,boolean showBar) {
        String internetStatus="";
        if(status.equalsIgnoreCase("Wifi enabled")||status.equalsIgnoreCase("Mobile data enabled")){
            internetStatus="Internet Connected";
        }else {
            internetStatus="Lost Internet Connection";
        }
        snackbar = Snackbar
                .make(coordinatorLayout, internetStatus, Snackbar.LENGTH_LONG)
                .setAction("X", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
        // Changing message text color
        snackbar.setActionTextColor(Color.WHITE);
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        if(internetStatus.equalsIgnoreCase("Lost Internet Connection")){
            if(internetConnected){
                snackbar.show();
                internetConnected=false;
                
            }
        }else{
            if(!internetConnected){
                internetConnected=true;
                snackbar.show();
            }
        }
}

}