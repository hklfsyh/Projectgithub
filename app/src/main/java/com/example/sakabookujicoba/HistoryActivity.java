package com.example.sakabookujicoba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    dbHelper myDB;
    ArrayList<String> cf_id, cf_cash, cf_date, cf_kete, cf_desc;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //recyclerview
        recyclerView = findViewById(R.id.riwayatkeuangan);

        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.historys);

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
                        return true;
                    case R.id.statistics:
                        startActivity(new Intent(getApplicationContext(),StatisticActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.supports:
                        startActivity(new Intent(getApplicationContext(),SupportActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //masukkin data ke arraylist
        myDB = new dbHelper(HistoryActivity.this);
        cf_id = new ArrayList<>();
        cf_cash = new ArrayList<>();
        cf_date = new ArrayList<>();
        cf_kete = new ArrayList<>();
        cf_desc = new ArrayList<>();


        StoreDataInArrays();
        customAdapter = new CustomAdapter(HistoryActivity.this, this, cf_id, cf_cash, cf_date, cf_kete, cf_desc);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }

    void StoreDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                cf_id.add(cursor.getString(0));
                cf_cash.add(cursor.getString(1));
                cf_date.add(cursor.getString(2));
                cf_kete.add(cursor.getString(3));
                cf_desc.add(cursor.getString(4));
            }
        }
    }

}