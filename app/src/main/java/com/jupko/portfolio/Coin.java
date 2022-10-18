package com.jupko.portfolio;

import android.media.Image;

import java.util.Locale;

public class Coin {

    private String name, ImageUrl, nameWithUSDT;
    private double buyPoint, currentPoint, investmentSize, currentSize, profit, profitPercentage;
    private boolean isExpanded = false, didProfit;

    public Coin(String name, double buyPoint, double investmentSize) {
        this.name = name.toUpperCase(Locale.ROOT);
        this.buyPoint = buyPoint;
        this.investmentSize = investmentSize;
        this.currentPoint = 1.5;
        if(this.currentPoint - this.buyPoint > 0){
            this.profitPercentage = ((this.currentPoint - this.buyPoint) / this.buyPoint) * 100;
            this.profit = (this.investmentSize * (this.currentPoint / this.buyPoint)) - this.investmentSize;
            this.didProfit = true;
        } else {
            this.profitPercentage = 100 - ((this.currentPoint / this.buyPoint) * 100);
            this.profit = this.investmentSize - (this.investmentSize * (this.currentPoint / this.buyPoint));
            this.didProfit = false;
        }
        this.currentSize = this.profit + this.investmentSize;
        setImageUrl(ImageUrl);
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public boolean isExpanded(){
        return this.isExpanded;
    }

    public boolean didProfit() {
        return didProfit;
    }

    public void setExpanded(boolean tf){
        this.isExpanded = tf;
    }

    public void setImageUrl(String url) {
        if(name.equals("CRO")) {
            this.ImageUrl = "https://s2.coinmarketcap.com/static/img/coins/64x64/3635.png";
        }
        else if (name.equals("ALGO")){
            this.ImageUrl = "https://s2.coinmarketcap.com/static/img/coins/64x64/8104.png";
        } else {
            this.ImageUrl = url;
        }
    }

    public String getName() {
        return name;
    }

    public String getNameWithUSDT() {
        return nameWithUSDT;
    }

    public void setName(String name) {
        this.name = name.toUpperCase(Locale.ROOT);
        setImageUrl(ImageUrl);
        this.nameWithUSDT = this.name + "USDT";
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }

    public void setProfitPercentage(double profitPercentage) {
        this.profitPercentage = profitPercentage;
    }

    public double getBuyPoint() {
        return buyPoint;
    }

    public void setBuyPoint(double buyPoint) {
        this.buyPoint = buyPoint;
    }

    public double getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(double currentPoint) {
        this.currentPoint = currentPoint;
    }

    public double getInvestmentSize() {
        return investmentSize;
    }

    public void setInvestmentSize(double investmentSize) {
        this.investmentSize = investmentSize;
    }

    public double getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(double currentSize) {
        this.currentSize = currentSize;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void update() {
        if(this.currentPoint - this.buyPoint > 0){
            this.profitPercentage = ((this.currentPoint - this.buyPoint) / this.buyPoint) * 100;
            this.profit = (this.investmentSize * (this.currentPoint / this.buyPoint)) - this.investmentSize;
            this.didProfit = true;
        } else {
            this.profitPercentage = 100 - ((this.currentPoint / this.buyPoint) * 100);
            this.profit = this.investmentSize - (this.investmentSize * (this.currentPoint / this.buyPoint));
            this.didProfit = false;
        }
    }

    @Override
    public String toString() {
        return "Coin{" +
                "name='" + name + '\'' +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", buyPoint=" + buyPoint +
                ", currentPoint=" + currentPoint +
                ", investmentSize=" + investmentSize +
                ", currentSize=" + currentSize +
                ", profit=" + profit +
                ", profitPercentage=" + profitPercentage +
                '}';
    }
}
