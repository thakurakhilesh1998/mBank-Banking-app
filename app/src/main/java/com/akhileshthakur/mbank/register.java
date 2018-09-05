package com.akhileshthakur.mbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.analytics.FirebaseAnalytics;

import java.net.URI;

import static com.akhileshthakur.mbank.config.URL;
import static com.akhileshthakur.mbank.config.URL2;

public class register extends Fragment {
    View view;
    Button proceed, reset;

    EditText name, email, phone, pass1, passconfirm;

    String Name, Email, Phone;
    String Pass1, Passconfirm;
    Firebase ref= null;


    public register() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register, container, false);


        return view;
    }

    public void onStart() {

        super.onStart();
        proceed = (Button) view.findViewById(R.id.proceeed);
        reset = (Button) view.findViewById(R.id.reset);

        name = (EditText) view.findViewById(R.id.name);
        phone = (EditText) view.findViewById(R.id.phone);
        email = (EditText) view.findViewById(R.id.email);
        pass1 = (EditText) view.findViewById(R.id.pass1);
        passconfirm = (EditText) view.findViewById(R.id.passconfirm);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(name.getText().toString().trim())) {
                    name.setError("Please enter a valid name");
                    return;
                }
                Name = name.getText().toString().trim();

                if ((phone.getText().length() < 10) || (TextUtils.isEmpty(phone.getText().toString().trim()))) {
                    phone.setError("Please enter a valid Phone");
                    return;
                }
                Phone = phone.getText().toString().trim();

                if (((email.getText().length() < 6) || (TextUtils.isEmpty(email.getText().toString().trim())))) {
                    email.setError("Email must be at least six characters long");
                    return;
                }
                Email = email.getText().toString().trim();
                final String ab=User.EncodeString(Email);
                if ((pass1.getText().length() < 6) || (TextUtils.isEmpty(pass1.getText().toString().trim()))) {
                    pass1.setError("Not valid");
                    return;
                }
                Pass1 = pass1.getText().toString().trim();
                if ((passconfirm.getText().length() < 6) || (TextUtils.isEmpty(pass1.getText().toString().trim()))) {
                    pass1.setError("Not valid");
                    return;
                }
                Passconfirm = passconfirm.getText().toString().trim();

                if (!Pass1.equals(Passconfirm)) {
                    pass1.setError("Passwords do not match");
                    passconfirm.setError("Passwords do not match");
                    return;
                }

ref=new Firebase(URL2);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(ab))
                        {
                            Toast.makeText(getContext(),"email is already exists",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "deatils registered successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getContext(), register2.class);
                            i.putExtra("namer",Name);
                            i.putExtra("email2",ab);
                            i.putExtra("phone2",Phone);
                            i.putExtra("password",Pass1);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });




            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                email.setText("");
                pass1.setText("");
                passconfirm.setText("");
                phone.setText("");
            }
        });


    }
}




