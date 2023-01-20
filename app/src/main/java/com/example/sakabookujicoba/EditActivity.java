package com.example.sakabookujicoba;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText cash_input, date_input, kete_input, desc_input;
    Button update_button, delete_button;

    String id, cash, date, kete, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        cash_input = findViewById(R.id.edituang);
        date_input = findViewById(R.id.edittanggal);
        kete_input = findViewById(R.id.editketerangan);
        desc_input = findViewById(R.id.editdesc);
        update_button = findViewById(R.id.buttonsimpanincome);
        delete_button = findViewById(R.id.buttonhapusincome);

        //mengambil item yang dipilih
        getAndSetIntentData();


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(EditActivity.this);
                cash = cash_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                kete = kete_input.getText().toString().trim();
                desc = desc_input.getText().toString().trim();
                myDB.updateData(id, cash, date, kete, desc);
                finish();

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("cash") && getIntent().hasExtra("date") && getIntent().hasExtra("kete") && getIntent().hasExtra("desc")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            cash = getIntent().getStringExtra("cash");
            date = getIntent().getStringExtra("date");
            kete = getIntent().getStringExtra("kete");
            desc = getIntent().getStringExtra("desc");


            //setting intent data
            cash_input.setText(cash);
            date_input.setText(date);
            kete_input.setText(kete);
            desc_input.setText(desc);

        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("hapus " + id + " ?");
        builder.setMessage("Anda yakin untuk menghapus data berikut : " + cash + ", " + desc + ", " + date + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper myDB = new dbHelper(EditActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}