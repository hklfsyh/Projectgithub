package com.example.sakabookujicoba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateOutcomeActivity extends AppCompatActivity {

    EditText cash_input, date_input, kete_input, desc_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createoutcome);

        cash_input = findViewById(R.id.outcomeuangkeluar);
        date_input = findViewById(R.id.outcomeuangtanggal);
        kete_input = findViewById(R.id.outcomeuangketerangan);
        desc_input = findViewById(R.id.outcomeuangdescription);
        add_button = findViewById(R.id.buttonsimpanoutcome);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(CreateOutcomeActivity.this);
                myDB.addCF(Integer.valueOf(cash_input.getText().toString().trim()),
                        date_input.getText().toString().trim(),
                        kete_input.getText().toString().trim(),
                        desc_input.getText().toString().trim());
                finish();
            }
        });

    }
}