package com.akhileshthakur.mbank;


import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationManager;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.akhileshthakur.mbank.config.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class addmoney extends Fragment implements AdapterView.OnItemSelectedListener {
Spinner add;
EditText fill;
String add2;
String bal24;
View view;
Button add4;
int balance;
String abbc;
Firebase ref_User=null;
public addmoney() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getActivity());
        view=inflater.inflate(R.layout.fragment_addmoney, container, false);
        return view;
    }
    public void onStart()
    {
        super.onStart();
        fill=(EditText)view.findViewById(R.id.fill);
        add4=(Button)view.findViewById(R.id.add4);
        add=(Spinner)view.findViewById(R.id.add);
        String [] account={"--Add Cash--","Cash","Paytm","Online"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,account);
        add.setAdapter(adapter);
        add.setOnItemSelectedListener(this);


        add4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(add.getSelectedItemPosition()==0)
        {
            TextView errorText = (TextView)add.getSelectedView();
            errorText.setError("select one option");
        }
        if(add.getSelectedItemPosition()==2)
        {
            TextView errorText = (TextView)add.getSelectedView();
            errorText.setError("Coming Soon");

        }
        if(add.getSelectedItemPosition()==3)
        {
            TextView errorText = (TextView)add.getSelectedView();
            errorText.setError("Coming Soon");

        }
        if(add.getSelectedItemPosition()==1)
        {
            if (TextUtils.isEmpty(fill.getText().toString().trim())) {
                fill.setError("it can't be empty");
                return;
            }
            abbc=fill.getText().toString();
            final int deff=Integer.parseInt(abbc);
            final String email=getArguments().getString("email1");
            ref_User=new Firebase(config.URL2);
            ref_User.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    User user1=dataSnapshot.getValue(User.class);
                    balance=user1.getBal();

                    balance=balance+deff;
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference abb=database.getReference();
                    abb.child("Users Details/").child(email).child("bal").setValue(balance);
                    Toast.makeText(getContext(),"successflly ",Toast.LENGTH_SHORT).show();
                    NotificationCompat.Builder mBuilder=(NotificationCompat.Builder) new NotificationCompat.Builder(getActivity()).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("ADD Money")
                            .setContentText("money"+deff+"added sncessfully to your account");
                    NotificationManager notificationManager=(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0,mBuilder.build());
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });






    }


//        Firebase firebase =new Firebase(URL);
//        firebase.child(email).child("bal").setValue(bal24);




    }
});

    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Spinner abc=(Spinner) adapterView;
        add2=adapterView.getItemAtPosition(i).toString();
    }
    public void onNothingSelected(AdapterView<?> adapterView) {



    }
}
