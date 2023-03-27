package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.foodapp.Fragments.Cart_Fragment;
import com.example.foodapp.Fragments.Home_Fragment;
import com.example.foodapp.Fragments.Profile_Fragment;
import com.example.foodapp.Fragments.favorite_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_Activity3 extends AppCompatActivity {

    FrameLayout frameLayout ;
    BottomNavigationView navigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);

        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,new Home_Fragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.home_id:
                        fragment = new Home_Fragment();
                        break;
                    case R.id.favorite_id:
                        fragment = new favorite_Fragment();
                        break;
                    case R.id.cart_id:
                        fragment = new Cart_Fragment();
                        break;
                    case R.id.profile_id:
                        fragment = new Profile_Fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id,fragment).commit();
                return true;
            }
        });
    }
}