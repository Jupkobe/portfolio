package com.jupko.portfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView btcData, ethData, usdData;
    private RecyclerView coinsRecView;
    private FloatingActionButton addNewCoin;
    private CoinsRecViewAdapter adapter;
    private Database db = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        adapter = new CoinsRecViewAdapter(this);
        coinsRecView = findViewById(R.id.coinsRecView);
        coinsRecView.setAdapter(adapter);
        coinsRecView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter.setCoins(db.getCoinsList());

        if (db.sizeCoinsList() < 1) {
            db.setCoinList(new Coin("cro",
                    0.25,
                    50));
            db.setCoinList(new Coin("algo",
                    0.25,
                    30));
        }

        addNewCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewCoinActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        btcData = findViewById(R.id.btcData);
        ethData = findViewById(R.id.ethData);
        usdData = findViewById(R.id.usdData);
        addNewCoin = findViewById(R.id.addNewCoin);
    }
}