package com.jupko.portfolio;

import static com.jupko.portfolio.MainActivity.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class NewCoinActivity extends AppCompatActivity {

    private EditText name, buy, size;
    private Button back, add;
    private Database db = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_coin);



        name = findViewById(R.id.name);
        buy = findViewById(R.id.buy);
        size = findViewById(R.id.size);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.setCoinList(new Coin(name.getText().toString(),
                        Double.parseDouble(buy.getText().toString()),
                        Double.parseDouble(size.getText().toString())));

                Intent intent = new Intent(NewCoinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewCoinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
