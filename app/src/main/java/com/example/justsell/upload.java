package com.example.justsell;

public class upload {
    private String  Itemname;
    private  String Itemprice;
    private String address;
    private String Itemdesc;
    private String number;
    private String mImageurl;

    public upload(String trim, String s) {
        //empty constructer needed
    }
    upload(String name,  String itemprice , String itemdesc,String location,String contact,String imageurl){
        if (name.trim().equals("")){
            name="NO Name";
        }


        Itemname =name;

        Itemprice=itemprice;
        address=location;
        Itemdesc= itemdesc;
        number=contact;
        mImageurl=imageurl;

    }

    public String getName(){
        return Itemname;
    }
    private void setName(String name){
        Itemname=name;
    }

   // }

    public String getitemprice(){
        return Itemprice;
    }
    private void setitemprice(String itemprice){
        Itemprice=itemprice;
    }

    public String getlocation(){
        return address;
    }
    private void setLocation(String location){
        address=location;
    }

    public String getItemdesc(){
        return Itemdesc;
    }
    private void setItemdesc(String itemdesc){
        Itemdesc=itemdesc;
    }

    public String getcontact(){
        return number;
    }

    private void setcontact(String contact){
        number=contact;
    }

     public String getImageurl(){
       return mImageurl;
    }

     public void setImageurl(String imageurl){
          mImageurl=imageurl;
     }

}
