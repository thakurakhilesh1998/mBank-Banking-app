package com.akhileshthakur.mbank;

import android.text.Editable;

public class User {
String Phone1,Address,Pincode,Number,Pannumber,Income,Dob,Gender,Spinner,Account_type,UID,image;
int bal;
//int Accountid;
    private String Name,Email,Pass1,Phone;

    public User(){

    }
public static String EncodeString(String string)
{
    return string.replace(".","_dot_");
}
public static String DecodeString(String string)
{
    return string.replace("_dot_",".");
}

public String getImage()
{
    return image;
}
public void setImage(String Image)
{
    image=Image;
}
    public String getPhone1()
    {
        return Phone1;
    }

    public void setPhone1(String phone1)
    {
        Phone1=phone1;
    }
    public String getAddress()
    {
        return Address;
    }
    public void setAddress(String address)
    {
        Address=address;
    }
    public String getPincode()
    {
        return Pincode;
    }
    public void setPincode(String pincode)
    {
        Pincode=pincode;
    }
    public String getNumber()
    {
        return Number;
    }
    public void setNumber(String number)
    {
        Number=number;
    }
//public void setAccountId(int accountId)
//{
//    Accountid=accountId;
//}
//public int getAccountid()
//{
//    return Accountid;
//}
    public String getPannumber()
    {
        return Pannumber;
    }
    public void setPannumber(String pannumber)
    {
        Pannumber=pannumber;
    }
    public String getIncome()
    {
        return Income;
    }
    public void setIncome(String income)
    {
        Income=income;
    }
    public String getDob()
    {
        return Dob;
    }
    public void setDob(String dob)
    {
        Dob=dob;
    }
    public String getGender()
    {
        return Gender;
    }
    public void setGender(String gender)
    {
        Gender=gender;
    }
    public void setSpinner(String spinner)
    {
        Spinner=spinner;
    }
    public String getSpinner()
    {
        return Spinner;
    }
    public String getAccount_type()
    {
        return Account_type;
    }
    public void setAccount_type(String account_type)
    {
        Account_type=account_type;
    }

    public int getBal()
    {
        return bal;
    }
    public void setBal(int Bal)
    {
        bal=Bal;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email)
    {
        Email=email;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name)
    {
        Name=name;
    }

    public String getPass1() {
        return Pass1;
    }
    public void setPass1(String pass1)
    {
        Pass1=pass1;
    }

    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone)
    {
        Phone=phone;
    }
}
