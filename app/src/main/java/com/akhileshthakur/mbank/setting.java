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


/**
 * A simple {@link Fragment} subclass.
 */
public class setting extends Fragment {

View view;
TextView Password;
Button log;
    public setting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_setting, container, false);
        return view;

    }
    public void onStart()
    {
        super.onStart();


        Password=(TextView)view.findViewById(R.id.Password);
        log=(Button)view.findViewById(R.id.log);

        Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String acc24=getArguments().getString("abc");
                Fragment fragment=new change();
                Bundle args=new Bundle();
                args.putString("email1",acc24);
                fragment.setArguments(args);
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getContext(),tabbed_login.class);
                startActivity(i1);
                Toast.makeText(getContext(),"Logged Out Successfully",Toast.LENGTH_SHORT);
            }
        });

}


}
