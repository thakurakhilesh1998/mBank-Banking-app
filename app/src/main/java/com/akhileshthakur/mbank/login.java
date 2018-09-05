package com.akhileshthakur.mbank;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.net.URL;

public class login extends Fragment {

    Firebase ref_User=null;
    View view;
EditText username,pass;
String Username,Pass;
Button login;

    public login() {
    }
    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.login,container,false);

        Firebase.setAndroidContext(getActivity());

        return view;

    }
    public void onStart() {

        super.onStart();

        username = (EditText)view.findViewById(R.id.username);
        pass = (EditText)view.findViewById(R.id.pass);
        login = (Button)view.findViewById(R.id.click);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(username.getText().toString().trim())) {
                    username.setError("Please enter a valid name");
                    return;
                }
                Username = username.getText().toString().trim();
                final String ab=User.EncodeString(Username);

                if (TextUtils.isEmpty(pass.getText().toString().trim())) {
                    pass.setError("enter the password");
                    return;
                }
                Pass = pass.getText().toString().trim();




                ref_User = new Firebase(config.URL2);

                ref_User.child(ab).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot1) {



                        try{
                        String username2=(dataSnapshot1.child("email").getValue().toString());
                                String user_pass = (dataSnapshot1.child("pass1").getValue().toString());
                        ProgressDialog progress;
                        progress = new ProgressDialog(getContext());
                        progress.setTitle("Please Wait!!");
                        progress.setMessage("Logging");
                        progress.setCancelable(true);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.show();
                       if(username2.equals(ab) && user_pass.equals(Pass))
                            {

                                    Toast.makeText(getContext(), "user authenticated successfully", Toast.LENGTH_SHORT).show();

                                    Intent i2 = new Intent(getContext(), usersdetails.class);
                                    i2.putExtra("username",ab);

                                    startActivity(i2);
                    }
                    else
{
    Toast.makeText(getContext(), "Incorrect username or Pass ", Toast.LENGTH_SHORT).show();
}
                        }
                    catch (Exception e)
                    {
                        Toast.makeText(getContext(), "Incorrect username or Pass ", Toast.LENGTH_SHORT).show();
                    }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });
    }


}