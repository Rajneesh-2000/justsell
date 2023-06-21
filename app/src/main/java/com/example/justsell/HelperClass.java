package com.example.justsell;

public class HelperClass {


    String name,number,address;
    private String mImageurl;


    public HelperClass(String name, String number, String address,String imageurl) {
        this.name = name;
        this.number = number;
        this.address = address;
        mImageurl=imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageurl(){
        return mImageurl;
    }

    public void setImageurl(String imageurl){
        mImageurl=imageurl;
    }
}
