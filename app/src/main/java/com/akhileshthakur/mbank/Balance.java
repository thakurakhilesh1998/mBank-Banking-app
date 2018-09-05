package com.akhileshthakur.mbank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Balance extends Fragment {
    Firebase ref_User=null;
TextView bal;
View view;
    public Balance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

view=inflater.inflate(R.layout.fragment_balance, container, false);
        Firebase.setAndroidContext(getActivity());
        bal=(TextView)view.findViewById(R.id.bal);

return view;
    }
    public void onStart()
    {
        super.onStart();
        bal=(TextView)view.findViewById(R.id.bal);
        final String email=getArguments().getString("abc");
        ref_User=new Firebase(config.URL2);
        ref_User.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                        User user1=dataSnapshot.getValue(User.class);
                     int bal2=user1.getBal();
                  String Bal=String.valueOf(bal2);

                        bal.setText(Bal);


           }
            public void onCancelled(FirebaseError firebaseError) {


                Toast.makeText(getContext(),"Database Error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}