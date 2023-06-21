package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StyleActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Homefregment homefregment = new Homefregment();
    SellFragment sellFragment = new SellFragment();

    AcountFragment acountFragment = new AcountFragment();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // first open home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.Containerr,homefregment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.miHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Containerr, homefregment).commit();
                        return true;

                    case R.id.sell:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Containerr, sellFragment).commit();
                        return true;

                    case R.id.miaccount:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Containerr, acountFragment).commit();

                        return true;


                }
                return false;
            }
        });

    }


}