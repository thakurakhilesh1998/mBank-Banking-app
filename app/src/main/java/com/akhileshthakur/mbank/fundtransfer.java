package com.akhileshthakur.mbank;


import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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
public class fundtransfer extends Fragment {
    Firebase ref_User=null;
EditText accname,acc245;
int old;
int deff,a45;
Button click;
String new2,Acc24;
View view;
    public fundtransfer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Firebase.setAndroidContext(getActivity());
        view=inflater.inflate(R.layout.fragment_fundtransfer, container, false);
        return view;

    }
    public void onStart()
    {
        super.onStart();
        accname=(EditText)view.findViewById(R.id.accname);
        acc245=(EditText)view.findViewById(R.id.acc24);
        click=(Button)view.findViewById(R.id.click);
        final String acc24=getArguments().getString("email1");
        ref_User=new Firebase(config.URL2);
        ref_User.child(acc24).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                        User user1=dataSnapshot.getValue(User.class);
                       old=user1.getBal();
                                 }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(accname.getText().toString().trim())) {
                    accname.setError("enter a valid fathers name");
                    return;
                }
          final String new4=accname.getText().toString().trim();
final String new2=User.EncodeString(new4);

                if (TextUtils.isEmpty(acc245.getText().toString().trim())) {
                    acc245.setError("enter a valid fathers name");
                    return;
                }
                Acc24=acc245.getText().toString().trim();
                deff=Integer.parseInt(Acc24);
                ref_User=new Firebase(config.URL2);
                if(new2.equals(acc24))
                {
                    Toast.makeText(getContext(),"Fund transfer to the same account",Toast.LENGTH_SHORT).show();
                }
                else
                {
                try
                {
                ref_User.child(new2).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(old>=deff)
                        {
                            try{
                            User user1=dataSnapshot.getValue(User.class);
                            a45=user1.getBal();
                            a45=a45+deff;
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference abb=database.getReference();
                            abb.child("Users Details/").child(new2).child("bal").setValue(a45);
                            old=old-deff;
                            database = FirebaseDatabase.getInstance();
                            abb = database.getReference();
                            abb.child("Users Details/").child(acc24).child("bal").setValue(old);
                            NotificationCompat.Builder mBuilder=(NotificationCompat.Builder) new NotificationCompat.Builder(getActivity()).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("ADD Money")
                                    .setContentText("money transfered");
                            NotificationManager notificationManager=(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(0,mBuilder.build());


                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getContext(),"Invalid Username",Toast.LENGTH_SHORT).show();
                            }
                            }
                            else
                            {
                                Toast.makeText(getContext(),"Insuffient Balance",Toast.LENGTH_SHORT).show();
                            }
                        }



                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),"Invalid Username",Toast.LENGTH_SHORT).show();
                }
                }

            }
        });
    }

}
