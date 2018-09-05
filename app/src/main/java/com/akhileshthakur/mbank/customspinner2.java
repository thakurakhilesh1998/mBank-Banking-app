package com.akhileshthakur.mbank;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class customspinner2 implements AdapterView.OnItemSelectedListener {
    String _accounttype;

    @Override


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        _accounttype=adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String val()
    {
        return _accounttype;


    }
}
