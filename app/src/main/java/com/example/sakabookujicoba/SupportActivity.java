package com.example.sakabookujicoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SupportActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.supports);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.homes:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.historys:
                        startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.statistics:
                        startActivity(new Intent(getApplicationContext(),StatisticActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.supports:
                        return true;
                }
                return false;
            }
        });

        Button Button = (android.widget.Button) findViewById(R.id.instagramsatu);
        Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/hklkhklkhklkh/"));
                startActivity(intent);
            }
        });

        Button Button2 = (Button) findViewById(R.id.instagramdua);

        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/srs.sal/"));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }

}