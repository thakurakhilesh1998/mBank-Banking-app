package com.akhileshthakur.mbank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class change extends Fragment {
User user=null;
View view;
EditText new2,new4;
String New2,New4;
Button change2;
    Firebase ref_User=null;

    public change() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_change, container, false);

        return view;
    }
    public void onStart()
    {
        super.onStart();
        new2=(EditText)view.findViewById(R.id.new2);
        new4=(EditText)view.findViewById(R.id.new4);
        change2=(Button)view.findViewById(R.id.change2);
        change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((new2.getText().length() < 6) || TextUtils.isEmpty(new2.getText().toString().trim())) {
                    new2.setError("it can not be empty");
                    return;
                }

                New2=new2.getText().toString();
                if ((new4.getText().length() < 6) || TextUtils.isEmpty(new4.getText().toString().trim())) {
                    new4.setError("it can not be empty");
                    return;
                }
                New4=new4.getText().toString();
                if(New4.equals(New2))
                {
                    new4.setError("same password");
                }

                final String acc24=getArguments().getString("email1");


                ref_User=new Firebase(config.URL2);
                ref_User.child(acc24).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                    String pass1=(dataSnapshot.child("pass1").getValue().toString());

                    if(New2.equals(pass1))
                    {
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        DatabaseReference abb=database.getReference();
                        abb.child("Users Details/").child(acc24).child("pass1").setValue(New4);
                        Toast.makeText(getContext(),"Password changed successfully",Toast.LENGTH_SHORT).show();
                    }
else {
                        Toast.makeText(getContext(),"Password mismatch",Toast.LENGTH_SHORT).show();

                    }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }
        });
    }
}
