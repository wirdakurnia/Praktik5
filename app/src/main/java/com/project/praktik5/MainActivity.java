package com.project.praktik5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnTambah, btnLihatData;
    private EditText etNama;
    private DatabaseHelper databaseHelper;
    private TextView txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        txtHasil = findViewById(R.id.txtHasil);
        etNama = findViewById(R.id.etNama);
        btnTambah = findViewById(R.id.btnTambah);
        btnLihatData = findViewById(R.id.btnLihatData);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                long l = databaseHelper.AddName(nama);
                etNama.setText("");
                if(l > 0)
                    Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });

        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> all = databaseHelper.getAllNama();
                if(all != null && !all.isEmpty()){
                    StringBuffer buffer = new StringBuffer();
                    for(String nama : all){
                        buffer.append(nama);
                        buffer.append(",");
                    }
                    String names = buffer.toString();
                    txtHasil.setText(names.substring(0, names.length()-1));
                }
            }
        });
    }
}