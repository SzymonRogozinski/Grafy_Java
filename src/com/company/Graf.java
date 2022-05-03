package com.company;

public class Graf {
    final private int columns, verses;
    private int graphs=1;
    final private double min,max;
    private Points points;

    public Graf(int columns, int verse, double min, double max) {
        this.columns = columns;
        this.verses = verse;
        this.min = min;
        this.max = max;
        points=new Points(verses,columns);
    }

    public int getColumns() {
        return columns;
    }

    public int getVerses() {
        return verses;
    }

    public int getGraphs() {
        return graphs;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public Points getPoints() {
        return points;
    }
}
