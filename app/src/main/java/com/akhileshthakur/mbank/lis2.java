package com.akhileshthakur.mbank;

public class lis2 {
    private String Name,Phone,father_name,Pannumber,Address,Pincode,Dob,Gender,Accountype;

    public lis2()
    {

    }
    public lis2(String Name,String Phone,String father_name,String Pannumber,String Address,String Pincode,String Dob,String Gender,String Accountype){

        this.Name=Name;
        this.Phone=Phone;
        this.father_name=father_name;
        this.Pannumber=Pannumber;
        this.Address=Address;
        this.Pincode=Pincode;
        this.Dob=Dob;
        this.Gender=Gender;
        this.Accountype=Accountype;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getPannumber() {
        return Pannumber;
    }

    public void setPannumber(String pannumber) {
        Pannumber = pannumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAccountype() {
        return Accountype;
    }

    public void setAccountype(String accountype) {
        Accountype = accountype;
    }
}
