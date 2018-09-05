package com.akhileshthakur.mbank;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class debit extends Fragment {
View view;
TextView test3;
       ImageView card4;
Firebase ref=null;


    public debit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view=inflater.inflate(R.layout.fragment_debit, container, false);
        return view;
    }
public void onStart()
{

    super.onStart();
    card4=(ImageView)view.findViewById(R.id.card4);
    test3=(TextView)view.findViewById(R.id.test3);
    String email=getArguments().getString("email1");

    ref=new Firebase(config.URL2);
    try
    {
    ref.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            try
            {
String image2=(dataSnapshot.child("image").getValue().toString());
            Picasso.with(getContext()).load(image2).into(card4);
        }
        catch (Exception d)
        {
            test3.setText("NO ANY ATM LINKED TO THIS ACCOUNT");
        }
        }
        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });
    }
    catch (Exception e)
    {
        test3.setText("NO ANY ATM LINKED TO THIS ACCOUNT");
    }
}
}
