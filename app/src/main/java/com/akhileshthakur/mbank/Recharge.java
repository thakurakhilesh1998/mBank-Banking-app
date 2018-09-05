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


/**
 * A simple {@link Fragment} subclass.
 */
public class Recharge extends Fragment {
    Firebase ref_User=null;
EditText amon25,e2,e4;
Button recharge4;
int deff;
View view;
String amount,state,number;
    public Recharge() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getActivity());
        view=inflater.inflate(R.layout.fragment_recharge, container, false);
        return view;

    }
    public void onStart()
    {
        super.onStart();
        amon25=(EditText)view.findViewById(R.id.amon25);
        e2=(EditText)view.findViewById(R.id.e2);
        e4=(EditText)view.findViewById(R.id.e4);
        recharge4=(Button)view.findViewById(R.id.show24);
        final String acc24=getArguments().getString("email1");

        ref_User=new Firebase(config.URL2);
        recharge4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e2.getText().length()<10 || TextUtils.isEmpty(e2.getText().toString().trim())) {
                    e2.setError("enter a valid number");
                    return;
                }
                number=e2.getText().toString().trim();
                if (TextUtils.isEmpty(amon25.getText().toString().trim())) {
                    amon25.setError("enter a valid amount");
                    return;
                }
                amount=amon25.getText().toString().trim();

                if (TextUtils.isEmpty(e4.getText().toString().trim())) {
                    e4.setError("enter a valid state");
                    return;
                }
                state=e4.getText().toString().trim();
                deff=Integer.parseInt(amount);
                ref_User.child(acc24).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user1=dataSnapshot.getValue(User.class);
                        int abc4=user1.getBal();
                        if(deff<abc4)
                        {
                        abc4=abc4-deff;
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference abb=database.getReference();
                            abb.child("Users Details/").child(acc24).child("bal").setValue(abc4);
                            Toast.makeText(getContext(),"Recharge of  "+number+"  is sccessfull",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getContext(),"Insufficient Balance",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(getContext(),"Database error",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    }
