
package com.akhileshthakur.mbank;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {

    View view;
    ProgressDialog progress;
    Button account,addmoney,recent,Fund,Recharge,FD,UPI,Debit,ATM;

    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        progress = new ProgressDialog(getContext());
        view=inflater.inflate(R.layout.fragment_home, container, false);
    final String email = this.getArguments().getString("abc");


        return view;



    }

    public void onStart()
    {
        super.onStart();
        final String email = this.getArguments().getString("abc");
        account=(Button)view.findViewById(R.id.account);
        addmoney=(Button)view.findViewById(R.id.addmoney);
        recent=(Button)view.findViewById(R.id.recent);
        Fund=(Button) view.findViewById(R.id.Fund);
        Recharge=(Button)view.findViewById(R.id.Recharge);
        FD=(Button)view.findViewById(R.id.FD);
        UPI=(Button)view.findViewById(R.id.UPI);
        Debit=(Button)view.findViewById(R.id.Debit);
        ATM=(Button)view.findViewById(R.id.ATM);


        Debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment=new debit();
                Bundle args=new Bundle();
                args.putString("email1",email);
                fragment.setArguments(args);
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        FD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new coming();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new coming();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        UPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new coming();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=new Recharge();
                Bundle args=new Bundle();
                args.putString("email1",email);
                fragment.setArguments(args);
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame,fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

      addmoney.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Fragment fragment=new addmoney();
              Bundle args=new Bundle();
              args.putString("email1",email);
              fragment.setArguments(args);
              FragmentManager fm=getActivity().getSupportFragmentManager();
              FragmentTransaction ft=fm.beginTransaction();
              ft.replace(R.id.frame,fragment);
              ft.addToBackStack(null);
              ft.commit();
          }
      });


Fund.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Fragment fragment=new fundtransfer();
        Bundle args=new Bundle();
        args.putString("email1",email);
        fragment.setArguments(args);
        FragmentManager fm=getActivity().getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
});


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getContext(),list_details.class);
                i.putExtra("email",email);
                startActivity(i);
//                Fragment fragment=new details();
//                Bundle args=new Bundle();
//                args.putString("email1",email);
//                fragment.setArguments(args);
//                FragmentManager fm=getActivity().getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                ft.replace(R.id.frame,fragment);
//                ft.addToBackStack(null);
//                ft.commit();
            }
        });

        ATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),atm.class);
                startActivity(i);

            }
        });
    }

}
