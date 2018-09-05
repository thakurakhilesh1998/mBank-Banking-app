package com.akhileshthakur.mbank;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

import static com.akhileshthakur.mbank.config.URL;
import static com.akhileshthakur.mbank.config.URL2;

public class register2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    User user = null;
    Firebase ref,ref2= null;
EditText name1,phone2,email2;

EditText phone1,address,pincode,number,pannumber,income,bal;
    TextView dob;
//int accountid;
RadioGroup gender;
RadioButton male,female;
Spinner spinner,account_type;
Button rst,create;
String rdbutton;
String addressproof;
ImageView date;
    String abc;
    String password;
    int year2,month2,day2;
    static final int DIALOG_ID=0;
String Name1,Phone1,Email2,Phone2,Address,Pincode,Pannumber,Income,Dob,Number;
int Balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
final Calendar cal=Calendar.getInstance();
year2=cal.get(Calendar.YEAR);
month2=cal.get(Calendar.MONTH);
        day2=cal.get(Calendar.DAY_OF_MONTH);
        spinner=(Spinner)findViewById(R.id.spinner);
        account_type=(Spinner)findViewById(R.id.account_type);
       String [] documents={"---Select Address Proof---","Aadhaar Card","Ration Card","Bonafide certificate","Voter Card"};
        String [] account={"--Select Account Type--","Saving","Current"};
       ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,documents);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,account);
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);
       account_type.setAdapter(adapter1);
       account_type.setOnItemSelectedListener(this);
        showDialogOnButtonClick();
       //abc=new customspinner2().val();
       create=(Button)findViewById(R.id.create);
       rst=(Button)findViewById(R.id.rst);

//to clear the field
       rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1.setText("");
                phone1.setText("");
                dob.setText("");
                phone2.setText("");
                email2.setText("");
                address.setText("");
                pincode.setText("");
                number.setText("");
                pannumber.setText("");
                income.setText("");
                spinner.setSelection(0);
                account_type.setSelection(0);
                gender.clearCheck();
                Toast.makeText(getApplicationContext(),"value"+abc,Toast.LENGTH_SHORT).show();
                }
        });

       //to create account


    }

    public void showDialogOnButtonClick()
    {
        date=(ImageView)findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id)
    {
        if(id==DIALOG_ID);
        return new DatePickerDialog(this,dnew,year2,month2,day2);
   //     return null;
    }
    private DatePickerDialog.OnDateSetListener dnew=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            year2=year;
            month2=month+1;
            day2=day;
            dob.setText(year2+"/"+month2+"/"+day2);
        }
    };
    // data base connectivity
    public void onStart()
    {
        super.onStart();
        dob=(TextView)findViewById(R.id.dob);
        name1=(EditText)findViewById(R.id.name1);
        phone1=(EditText)findViewById(R.id.phone1);
        phone2=(EditText)findViewById(R.id.phone2);
        email2=(EditText)findViewById(R.id.email2);
        address=(EditText)findViewById(R.id.address);
        pincode=(EditText)findViewById(R.id.pincode);
        number=(EditText)findViewById(R.id.number);
        pannumber=(EditText)findViewById(R.id.pannumber);
        income=(EditText)findViewById(R.id.income);
        gender=(RadioGroup)findViewById(R.id.gender);

        bal=(EditText)findViewById(R.id.bal);
        Email2=getIntent().getExtras().getString("email2");
        Phone2=getIntent().getExtras().getString("phone2");
        Name1=getIntent().getExtras().getString("namer");
        name1.setText(getIntent().getExtras().getString("namer"));
        email2.setText(getIntent().getExtras().getString("email2"));
        phone2.setText(getIntent().getExtras().getString("phone2"));
        password=getIntent().getExtras().getString("password");



                create.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                ref = new Firebase(URL);
                ref2=new Firebase(URL2);
                final User user = new User();
                if (TextUtils.isEmpty(bal.getText().toString().trim())) {
                    bal.setError("enter valid father name");
                    return;
                }
                Balance=Integer.parseInt(bal.getText().toString());
                if (TextUtils.isEmpty(phone1.getText().toString().trim())) {
                    phone1.setError("enter valid father name");
                    return;
                }
                Phone1= phone1.getText().toString().trim();


                if (TextUtils.isEmpty(address.getText().toString().trim())) {
                    address.setError("enter a valid fathers name");
                    return;
                }
                Address= address.getText().toString().trim();
                if (pincode.getText().length()<6 || TextUtils.isEmpty(pincode.getText().toString().trim())) {
                    pincode.setError("Please enter a valid address");
                    return;

                }
                Pincode= pincode.getText().toString().trim();

                if (pannumber.getText().length()<10 || TextUtils.isEmpty(pannumber.getText().toString().trim())) {
                    pannumber.setError("Please enter a pincode");
                    return;
                }
                Pannumber= pannumber.getText().toString().trim();

                if (number.getText().length()<12 || TextUtils.isEmpty(number.getText().toString().trim())) {
                    number.setError("enter a pannumber");
                    return;
                }
                Number= number.getText().toString().trim();

                if (TextUtils.isEmpty(income.getText().toString().trim())) {
                    income.setError("enter");
                    return;
                }
                Income= income.getText().toString().trim();

                if (TextUtils.isEmpty(dob.getText().toString().trim())) {
                    dob.setError("Please enter a dob");
                    return;
                }
                Dob= dob.getText().toString().trim();

                if(account_type.getSelectedItemPosition()==0)
                {
                    TextView errorText=(TextView)account_type.getSelectedView();
                    errorText.setError("select one option");
                }

                if(spinner.getSelectedItemPosition()==0)
                {
                    TextView errorText = (TextView)spinner.getSelectedView();
                    errorText.setError("select one option");
                }

                if(gender.getCheckedRadioButtonId()<=0)
                {
                    female.setError("select item");
                }

                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(Email2))
                        {
                            Toast.makeText(getApplicationContext(),"email is already exists",Toast.LENGTH_SHORT).show();
                        }
                        else {
                        final String sample=User.EncodeString(Email2);
                        user.setName(Name1);
                        user.setEmail(sample);
                        user.setPhone(Phone2);
                        user.setPhone1(Phone1);
                        //   user.setAccountId(accountid);
                        user.setPass1(password);
                        user.setAddress(Address);
                        user.setPincode(Pincode);
                        user.setPannumber(Pannumber);
                        user.setIncome(Income);
                        user.setDob(Dob);
                        user.setNumber(Number);
                        user.setGender(rdbutton);
                        user.setSpinner(addressproof);
                        user.setAccount_type(abc);
                        user.setBal(Balance);


                        ref.child("Users Details").child(sample).setValue(user);
                        Toast.makeText(getApplicationContext(),"welcome to users home",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),tabbed_login.class);
                        startActivity(i);

                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });

    }
 public void radioButtonClicked(View view)
 {
    boolean checked=((RadioButton) view).isChecked();

    switch ((view.getId()))
    {
        case R.id.male:
            if(checked)
         rdbutton="Male";
            break;
        case R.id.female:
            if(checked)
                rdbutton="Female";


    }

 }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spinner1 = (Spinner) adapterView;

        if(spinner1.getId() == R.id.spinner) {


            addressproof = adapterView.getItemAtPosition(i).toString();
        }
        else if(spinner1.getId()==R.id.account_type) {

       abc=adapterView.getItemAtPosition(i).toString();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {



    }
}
