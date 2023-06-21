package com.example.justsell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class SellFragment extends Fragment {

    Activity sell;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sell = getActivity();
        return inflater.inflate(R.layout.fragment_sell, container, false);


    }
    public void onStart() {
        super.onStart();
        CardView furniture = (CardView) sell.findViewById(R.id.sofa);
        CardView electronic = (CardView) sell.findViewById(R.id.electronicsss);
        CardView book = (CardView) sell.findViewById(R.id.book);
        CardView sports = (CardView) sell.findViewById(R.id.football);
        CardView kitchen = (CardView) sell.findViewById(R.id.kitchenss);
        CardView fashion = (CardView) sell.findViewById(R.id.kpde);

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell,furnitureActivity.class);
                startActivity(intent);
            }
        });


        electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell, Sportsactivity.class);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell, BooksUploadActivity.class);
                startActivity(intent);
            }
        });


        electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell, ElectronicsUploadActivity.class);
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell, Sportsactivity.class);
                startActivity(intent);
            }
        });

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell, kitchenUploadActivity.class);
                startActivity(intent);
            }
        });

        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell, FashionUploadActivity.class);
                startActivity(intent);
            }
        });
    }
}