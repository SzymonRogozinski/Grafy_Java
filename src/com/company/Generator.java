package com.company;

import java.util.Random;

public class Generator {
    private static Random random=new Random();

    public void generateGraph(Points points,double min,double max){
        int lenght =points.getVerses()* points.getColumns();
        for(int i=0;i<lenght;i++){
            //prawo
            if(i/points.getColumns()==(i+1)/points.getColumns())
                points.setTwoWayConnection(i,i+1,getRandomConnectionValue(min, max));
            //dół
            if(i/ points.getColumns()< points.getVerses()-1)
                points.setTwoWayConnection(i,i+points.getColumns(),getRandomConnectionValue(min, max));
        }
    }

    public double getRandomConnectionValue(double min,double max){
        return random.nextDouble(min,max);
    }

}
