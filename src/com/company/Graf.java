package com.company;

public class Graf {
    private int graphs=1;
    final private double min,max;
    private Points points;

    public Graf(int columns, int verses, double min, double max) {
        this.min = min;
        this.max = max;
        points=new Points(verses,columns);
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
