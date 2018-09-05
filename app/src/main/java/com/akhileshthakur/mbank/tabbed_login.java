package com.akhileshthakur.mbank;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class tabbed_login extends AppCompatActivity {







    AppBarLayout appBarLayout;
    TabLayout tabLayout;
    ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_login);

        Firebase.setAndroidContext(this);


        appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new login(),"Login");
        adapter.AddFragment(new register(),"Register");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //action on proceed




    }
}
