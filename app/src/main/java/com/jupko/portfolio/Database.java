package com.jupko.portfolio;

import java.util.ArrayList;

public class Database {

    private static final ArrayList<Coin> coinsList = new ArrayList<>();

    public static void removeCoinList(String coinsName) {
        Database.coinsList.remove(coinsName);
    }

    public static void setCoinList(Coin coin) {
        Database.coinsList.add(coin);
    }

    public static ArrayList<Coin> getCoinsList() {
        return coinsList;
    }

    public static int sizeCoinsList() {
        return coinsList.size();
    }


    //SINGLETON PATTERN
    private static class InnerDb {
        private static final Database instance = new Database();
    }

    public static Database getInstance() {
        return InnerDb.instance;
    }

    private Database() {
    }
}

