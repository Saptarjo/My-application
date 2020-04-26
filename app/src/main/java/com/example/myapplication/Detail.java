package com.example.myapplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Detail extends RealmObject {
@PrimaryKey
    private int amount;
   private String date;
    private String freq1;
    private String freq2;
    private int period;

    public Detail() {
    }

    public Detail(int amount, String date, String freq1, String freq2, int period) {
        this.amount = amount;
        this.date = date;
        this.freq1 = freq1;
        this.freq2 = freq2;
        this.period = period;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFreq1() {
        return freq1;
    }

    public void setFreq1(String freq1) {
        this.freq1 = freq1;
    }

    public String getFreq2() {
        return freq2;
    }

    public void setFreq2(String freq2) {
        this.freq2 = freq2;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "amount=" + amount +
                ", date='" + date + '\'' +
                ", freq1='" + freq1 + '\'' +
                ", freq2='" + freq2 + '\'' +
                ", period=" + period +
                '}';
    }
}