package com.example.sakabookujicoba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    dbHelper myDB;
    ArrayList<String> cf_id, cf_cash, cf_date, cf_kete, cf_desc;
    TextView displayTsaldo, displayMasuk, displayKeluar;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTsaldo = (TextView) findViewById(R.id.saldo_total);
        displayMasuk = (TextView) findViewById(R.id.textpemasukkan);
        displayKeluar = (TextView) findViewById(R.id.textpengeluaran);

        //recyclerview
        recyclerView = findViewById(R.id.riwayatkeuangan);

        //Deklarasi bottom navigation bar
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.homes);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.homes:
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
                        startActivity(new Intent(getApplicationContext(),SupportActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //Deklarasi Button Create Uang Masuk
        Button btnincome = (Button) findViewById(R.id.buttoniconpemasukkan);
        btnincome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,CreateIncomeActivity.class);
                startActivityForResult(i, 1);
            }
        });

        //Deklarasi Button Create Uang Keluar
        Button btnoutcome = (Button) findViewById(R.id.buttoniconpengeluaran);
        btnoutcome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,CreateOutcomeActivity.class);
                startActivityForResult(i, 1);
            }
        });

        //masukkin data ke arraylist
        myDB = new dbHelper(MainActivity.this);
        cf_id = new ArrayList<>();
        cf_cash = new ArrayList<>();
        cf_date = new ArrayList<>();
        cf_kete = new ArrayList<>();
        cf_desc = new ArrayList<>();

        StoreDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, cf_id, cf_cash, cf_date, cf_kete, cf_desc);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        StoreSummedMasuk();
        StoreSummedKeluar();
        StoreTsaldo();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
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

    void StoreSummedMasuk(){
        Cursor cursor = myDB.sumMasuk();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                displayMasuk.setText(cursor.getString(0));
            }
        }
    }

    void StoreSummedKeluar(){
        Cursor cursor = myDB.sumKeluar();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                displayKeluar.setText(cursor.getString(0));
            }
        }
    }

    void StoreTsaldo(){

        Integer aa;
        Integer bb;
        if(displayMasuk.getText().equals("")){
            aa = 0;
        }else{
            aa = Integer.parseInt(displayMasuk.getText().toString());
        }

        if(displayKeluar.getText().equals("")){
            bb = 0;
        }else{
            bb = Integer.parseInt(displayKeluar.getText().toString());
        }
        displayTsaldo.setText(Integer.toString(aa - bb));

    }
}
