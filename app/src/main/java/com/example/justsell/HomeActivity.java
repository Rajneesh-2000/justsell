package com.example.justsell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.io.FileReader;

public class HomeActivity extends AppCompatActivity {

      BottomNavigationView bottomNavigationView;
      Homefregment homefregment=new Homefregment();
      SellFragment sellFragment=new SellFragment();
      AcountFragment acountFragment=new AcountFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.R.id.container,homefregment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.miHome:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.R.id.container,homefregment).commit();
                        return true;

                    case R.id.miaccount:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.R.id.container,acountFragment).commit();
                        return true;
                }

                return false;
            }
        });


    }

}