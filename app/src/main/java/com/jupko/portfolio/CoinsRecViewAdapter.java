package com.jupko.portfolio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoinsRecViewAdapter extends RecyclerView.Adapter<CoinsRecViewAdapter.ViewHolder> {
    private static final String TAG = "CoinsRecViewAdapter";

    private ArrayList<Coin> coins = new ArrayList<>();
    private Context mContext;

    public CoinsRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_coin, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        //Coin text size adapter
        switch (holder.coinsName.length()) {
            case 3:
                holder.coinsName.setWidth(60);
                break;
            case 4:
                holder.coinsName.setWidth(70);
                break;
            case 5:
                holder.coinsName.setWidth(80);
                break;
        }

        holder.coinsName.setText(
                coins.get(position).getName());
        holder.investSize.setText(
                Double.toString(coins.get(position).getInvestmentSize()));
        Glide.with(mContext)
                .asBitmap()
                .load(coins.get(position).getImageUrl())
                .into(holder.coinImg);
        holder.buyPoint.setText(
                Double.toString(coins.get(position).getBuyPoint()));
        holder.currentPoint.setText(
                Double.toString(coins.get(position).getCurrentPoint()));
        holder.profitPercentage.setText(
                "%" + Double.toString(coins.get(position).getProfitPercentage()));
        holder.profit.setText(
                Double.toString(coins.get(position).getProfit()));


        //Loss Profit Color Change
        if(coins.get(position).didProfit()) {
            holder.profitPercentage.setTextColor(Color.parseColor("#3eda90"));
            holder.profit.setTextColor(Color.parseColor("#3eda90"));
        } else {
            holder.profitPercentage.setTextColor(Color.parseColor("#f46154"));
            holder.profit.setTextColor(Color.parseColor("#f46154"));
        }
        // Turn on off edit section
        holder.smallCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins.get(position).getName().equals("CRO")){
                    Toast.makeText(mContext,
                            "Allahın Aslanı " + coins.get(position).getName(),
                            Toast.LENGTH_SHORT).show();
                }
                if(coins.get(position).isExpanded()){
                    TransitionManager.beginDelayedTransition(holder.expandedCoin);
                    holder.expandedCoin.setVisibility(View.GONE);
                    coins.get(position).setExpanded(false);
                } else {
                    TransitionManager.beginDelayedTransition(holder.expandedCoin);
                    holder.expandedCoin.setVisibility(View.VISIBLE);
                    coins.get(position).setExpanded(true);
                }
            }
        });
        // Edit section stuff
        holder.editExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    if (holder.nameExpanded.getText().toString().equals("")){
//                    } else {
//                        coins.get(position).setName(holder.nameExpanded.getText().toString());
//                        notifyDataSetChanged();
//                    }
                if (!TextUtils.isEmpty(holder.nameExpanded.getText().toString())){
                    coins.get(position).setName(holder.nameExpanded.getText().toString());
                    notifyDataSetChanged();
                    holder.nameExpanded.getText().clear();
                } if (!TextUtils.isEmpty(holder.buyExpanded.getText().toString())){
                    coins.get(position).setBuyPoint(
                            Double.parseDouble(holder.buyExpanded.getText().toString()));
                    notifyDataSetChanged();
                    holder.buyExpanded.getText().clear();
                } if (!TextUtils.isEmpty(holder.sizeExpanded.getText().toString())) {
                    coins.get(position).setInvestmentSize(
                            Double.parseDouble(holder.sizeExpanded.getText().toString()));
                    notifyDataSetChanged();
                    holder.sizeExpanded.getText().clear();
                }
                coins.get(position).update();
            }
        });
        // Edit section Delete button
        holder.deleteExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.sizeExpanded.getText().clear();
                holder.nameExpanded.getText().clear();
                holder.buyExpanded.getText().clear();
                coins.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout parent, smallCoin, expandedCoin;
        private ImageView coinImg;
        private TextView coinsName, profit, currentPoint, buyPoint, profitPercentage, investSize;
        private EditText nameExpanded, buyExpanded, sizeExpanded;
        private Button editExpanded, deleteExpanded;
        private View colorProfit;
        private View colorLoss;

        @SuppressLint("ResourceType")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            investSize = itemView.findViewById(R.id.investSize);
            smallCoin = itemView.findViewById(R.id.smallCoin);
            colorProfit = itemView.findViewById(R.id.profit);
            colorLoss = itemView.findViewById(R.color.loss);
            expandedCoin = itemView.findViewById(R.id.expandedCoin);
            coinImg = itemView.findViewById(R.id.coinImg);
            coinsName = itemView.findViewById(R.id.coinsName);
            profit = itemView.findViewById(R.id.profit);
            currentPoint = itemView.findViewById(R.id.currentPoint);
            buyPoint = itemView.findViewById(R.id.buyPoint);
            profitPercentage = itemView.findViewById(R.id.profitPercentage);
            editExpanded = itemView.findViewById(R.id.editExpanded);
            sizeExpanded = itemView.findViewById(R.id.sizeExpanded);
            buyExpanded = itemView.findViewById(R.id.buyExpanded);
            deleteExpanded = itemView.findViewById(R.id.deleteExpanded);
            nameExpanded = itemView.findViewById(R.id.nameExpanded);
        }
    }

}
