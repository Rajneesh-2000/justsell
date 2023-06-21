package com.example.justsell;

public class FurnitureRecyclelass {

    String name,contact,imageurl,itemdesc,itemprice,location;

    public FurnitureRecyclelass() {


    }

    public FurnitureRecyclelass(String name, String contact, String imageurl, String itemdesc, String itemprice, String location) {
        this.name = name;
        this.contact = contact;
        this. imageurl=imageurl;
        this.itemdesc = itemdesc;
        this.itemprice = itemprice;
        this.location = location;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageurl(){return  imageurl;}

    public void setImageurl(String imageurl){
        this.imageurl=imageurl;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}





