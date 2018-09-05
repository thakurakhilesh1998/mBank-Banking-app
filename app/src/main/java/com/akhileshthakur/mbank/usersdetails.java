package com.akhileshthakur.mbank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class usersdetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Firebase ref_User=null;
    TextView name2,email2;
    ImageView imageView,input1;
    private static final int CAMERA_REQUEST=123;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersdetails);
        Firebase.setAndroidContext(this);

//       Phone2=getIntent().getExtras().getString("phone2");

        input1=(ImageView)findViewById(R.id.input1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Bundle bundle=getIntent().getExtras();
//        final String email24=bundle.getString("username");
        final String email24=getIntent().getExtras().getString("username");
        ref_User=new Firebase(config.URL2);
        ref_User.child(email24).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                        name2=(TextView)findViewById(R.id.name);
                        email2=(TextView)findViewById(R.id.email);
                        User ab=dataSnapshot.getValue(User.class);
                        String nam=ab.getName();
                       name2.setText(nam);
                        email2.setText(email24);
                    }



            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(),"database Connection error",Toast.LENGTH_SHORT).show();
            }
        });

        String test=getIntent().getExtras().getString("username");
        Bundle bundle = new Bundle();
        // bundle.putString("username",test);
        bundle.putString("abc",test);
        home fragment=new home();
        fragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.usersdetails, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//                if (id == R.id.action_settings) {
//
//
//                        return true;
//                       }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
//id for home
        if (id == R.id.nav_home) {
            String test=getIntent().getExtras().getString("username");
            Bundle bundle = new Bundle();
           // bundle.putString("username",test);
            bundle.putString("abc",test);
            home fragment=new home();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame,fragment);
            ft.commit();


        } else if (id == R.id.nav_money) {
            String test2=getIntent().getExtras().getString("username");
            Bundle bundle = new Bundle();
            // bundle.putString("username",test);
            bundle.putString("abc",test2);
            Balance fragment=new Balance();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame,fragment);
            ft.commit();



        } else if (id == R.id.nav_setting) {
            String test2=getIntent().getExtras().getString("username");
            Bundle bundle = new Bundle();
            // bundle.putString("username",test);
            bundle.putString("abc",test2);
            setting fragment=new setting();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame,fragment);
            ft.commit();


        } else if (id == R.id.nav_help) {

            about fragment=new about();

            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame,fragment);
            ft.commit();



        } else if (id == R.id.nav_share) {

            Intent share=new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT,"Please Install this app to have a awesome banking experience");
            share.setPackage("com.whatsapp");
            startActivity(share);


        } else if (id == R.id.nav_logout) {

            Intent i1=new Intent(getApplicationContext(),tabbed_login.class);
            startActivity(i1);
            Toast.makeText(getApplicationContext(),"Logged Out Successfully",Toast.LENGTH_SHORT);

        }
        else if( id==R.id.exit)
        {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(usersdetails.this);
//            builder.setTitle("Exit");
//            builder.setMessage("Do you want to exit ??");
//            builder.setPositiveButton("Yes, Exit Now", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
//                }
//            });
//            builder.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
