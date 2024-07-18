package com.example.tebakkataapps;

public class DataModel {
    private String name;
    private String datePlay;
    private int scoreBenar;
    private int scoreSalah;
    private int scoreTotal;

    public DataModel(String name, String datePlay, int scoreBenar, int scoreSalah, int scoreTotal) {
        this.name = name;
        this.datePlay = datePlay;
        this.scoreBenar = scoreBenar;
        this.scoreSalah = scoreSalah;
        this.scoreTotal = scoreTotal;
    }

    public String getName() {
        return name;
    }

    public String getDatePlay() {
        return datePlay;
    }

    public int getScoreBenar() {
        return scoreBenar;
    }

    public int getScoreSalah() {
        return scoreSalah;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }
}
