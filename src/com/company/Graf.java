package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Graf {
    private int graphs=1;
    final private double min;
    final private double max;
    private Points points;

    //Konstruktor do grafu generowanego
    public Graf(int columns, int verses, double min, double max) {
        this.min = min;
        this.max = max;
        points=new Points(verses,columns);
        Generator generator=new Generator();
        generator.generateGraph(points,min,max);
    }

    //Konstruktor do grafu wczytywanego
    public Graf(String filename){
        min=0;
        max=0;
        Reader reader=new Reader();
        this.points=reader.downloadGraph(filename);
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

    public void printGraf(){
        System.out.println("Liczba grafow spojnych: " + graphs);
        if(min!=max)
            System.out.println("Zakres wartosi: " + min + "-" + max);
        int lenght=points.getColumns()*points.getVerses();
        for(int i=0;i<lenght;i++)
            System.out.println(i + " -> " + points.pointNeighbourPrint(i));
    }

    public void grafToFile(String filename) throws IOException {
        FileWriter writer=new FileWriter(filename);
        writer.write(points.getVerses()+" " + points.getColumns()+"\n");
        int lenght=points.getVerses()*points.getColumns();
        for(int i=0;i<lenght;i++)
            writer.write("\t" + points.pointNeighbourPrint(i) + "\n");
        writer.close();
    }
}
