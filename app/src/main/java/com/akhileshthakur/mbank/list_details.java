package com.akhileshthakur.mbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class list_details extends AppCompatActivity {
ListView lv2;
Button b4;
String key5;
    Firebase ref_User=null;
    ArrayList<String> list;

    ArrayAdapter <String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);
        Firebase.setAndroidContext(getApplicationContext());

        lv2=(ListView)findViewById(R.id.lv2);
        b4=(Button)findViewById(R.id.b4);
        key5=getIntent().getExtras().getString("email");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.info,R.id.tv2,list);
        ref_User = new Firebase(config.URL2);
        ref_User.child(key5).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String Name =(dataSnapshot.child("name").getValue().toString());
                String Phone =(dataSnapshot.child("phone").getValue().toString());
                String Email =(dataSnapshot.child("email").getValue().toString());
                String father_name =(dataSnapshot.child("phone1").getValue().toString());
                String Pannumber =(dataSnapshot.child("pannumber").getValue().toString());
                String Address =(dataSnapshot.child("address").getValue().toString());
                String Pincode =(dataSnapshot.child("pincode").getValue().toString());
                String Dob =(dataSnapshot.child("dob").getValue().toString());
                String Gender =(dataSnapshot.child("gender").getValue().toString());
                String Accountype =(dataSnapshot.child("account_type").getValue().toString());
                list.add("Name:  "+Name);
                list.add("Father's Name: "+father_name);
                list.add("Phone: "+Phone);
                list.add("Email: "+Email);
                list.add("Accountype: "+Accountype);
                list.add("Pannumber "+Pannumber);
                list.add("Address: "+Address);
                list.add("Dob: "+Dob);
                list.add("Pincode: "+Pincode);
                list.add("Gender: "+Gender);
                lv2.setAdapter(adapter);



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),usersdetails.class);
                i.putExtra("username",key5);
                startActivity(i);
            }
        });
    }

}
