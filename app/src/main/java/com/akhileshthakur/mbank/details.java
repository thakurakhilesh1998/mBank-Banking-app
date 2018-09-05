package com.akhileshthakur.mbank;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class details extends Fragment {
FirebaseDatabase database;
Firebase ref_User=null;
DatabaseReference ref;
View view;
TextView name,fathername,email1,phone,dob,gender,pannumber,address,account_type;
Button back;
    public details() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_details, container, false);
        Firebase.setAndroidContext(getActivity());
        String email=getArguments().getString("email1");
        Toast.makeText(getContext(),"email is"+email,Toast.LENGTH_SHORT).show();

        return view;
    }

    public void onStart()
    {
        super.onStart();

        name=(TextView)view.findViewById(R.id.name);
        fathername=(TextView)view.findViewById(R.id.fathername);
        email1=(TextView)view.findViewById(R.id.email);
        phone=(TextView)view.findViewById(R.id.phone);
        dob=(TextView)view.findViewById(R.id.dob);
        gender=(TextView)view.findViewById(R.id.gender);
        pannumber=(TextView)view.findViewById(R.id.pannumber);
        address=(TextView)view.findViewById(R.id.address);
        account_type=(TextView)view.findViewById(R.id.account_type);
         back=(Button)view.findViewById(R.id.back);
        final String email=getArguments().getString("email1");


        ref_User = new Firebase(config.URL2);
ref=FirebaseDatabase.getInstance().getReference().child("Users Details").child(email);
        ref_User.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String Name =(dataSnapshot.child("name").getValue().toString());
                String Phone =(dataSnapshot.child("phone").getValue().toString());

                String father_name =(dataSnapshot.child("phone1").getValue().toString());
                String Pannumber =(dataSnapshot.child("pannumber").getValue().toString());
                String Address =(dataSnapshot.child("address").getValue().toString());
                String Pincode =(dataSnapshot.child("pincode").getValue().toString());
                String Dob =(dataSnapshot.child("dob").getValue().toString());
                String Gender =(dataSnapshot.child("gender").getValue().toString());
                String Accountype =(dataSnapshot.child("account_type").getValue().toString());


                        name.setText(Name);
                        fathername.setText(father_name);
                      email1.setText(email);
                        phone.setText(Phone);
                        dob.setText(Dob);
                        gender.setText(Gender);
                        pannumber.setText(Pannumber);
                        address.setText(Address);

                        account_type.setText(Accountype);

                    }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
            });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.popBackStack();
                    }
                });


    }
}




