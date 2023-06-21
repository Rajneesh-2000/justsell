package com.example.justsell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


public class Homefregment extends Fragment {


Activity Home;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Home = getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homefregment, container, false);


    }

    public void onStart() {
        super.onStart();

        ImageSlider imageSlider =(ImageSlider) Home.findViewById(R.id.image_slider);

        CardView furniture = (CardView) Home.findViewById(R.id.firnituresimage);
        CardView electronic = (CardView) Home.findViewById(R.id.electronicsimage);
        CardView book = (CardView) Home.findViewById(R.id.stationaryimage);
        CardView sports = (CardView) Home.findViewById(R.id.sportsimage);
        CardView kitchen = (CardView) Home.findViewById(R.id.kitchenimages);
        CardView fashion = (CardView) Home.findViewById(R.id.brandimage);

        AdView mAdView=(AdView)  Home.findViewById(R.id.addView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ArrayList<SlideModel> imagelist =new ArrayList<>();
        imagelist.add(new SlideModel(R.drawable.widefashion,null));
        imagelist.add(new SlideModel(R.drawable.widefur,null));
        imagelist.add(new SlideModel(R.drawable.widesports,null));
        imagelist.add(new SlideModel(R.drawable.widestati,null));
          imageSlider.setImageList(imagelist);



        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home,FurnitureHomeActivity.class);
                startActivity(intent);
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home,BooksRecycleActivity.class);
                startActivity(intent);
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home,kitchenRecycleActivity.class);
                startActivity(intent);
            }
        });
        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home,FashionRecycleActivity.class);
                startActivity(intent);
            }
        });
        electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home,ElectronicRecycleActivity.class);
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home,SportsRecycleActivity.class);
                startActivity(intent);
            }
        });
    }
}