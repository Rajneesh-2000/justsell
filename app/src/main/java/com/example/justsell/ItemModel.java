package com.example.justsell;

import java.util.ArrayList;

public class ItemModel {
    private String Itemname,Itemdesc,itemid;
    private ArrayList<String>ImageUrls;

    public ItemModel() {
    }

    public ItemModel(String itemname, String itemdesc, String itemid, ArrayList<String> imageUrls) {
        Itemname = itemname;
        Itemdesc = itemdesc;
        this.itemid = itemid;
        ImageUrls = imageUrls;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public String getItemdesc() {
        return Itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        Itemdesc = itemdesc;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public ArrayList<String> getImageUrls() {
        return ImageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        ImageUrls = imageUrls;
    }
}
