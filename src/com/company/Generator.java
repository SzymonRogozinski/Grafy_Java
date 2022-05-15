package com.company;

import java.util.Random;

public class Generator {
    private static Random random=new Random();

    public void generateGraph(Points points,double min,double max,int graphs){
        int lenght =points.getVerses()* points.getColumns();
        //Generowanie jednego grafu
        for(int i=0;i<lenght;i++){
            //prawo
            if(i/points.getColumns()==(i+1)/points.getColumns())
                points.setTwoWayConnection(i,i+1,getRandomConnectionValue(min, max));
            //dół
            if(i/ points.getColumns()< points.getVerses()-1)
                points.setTwoWayConnection(i,i+points.getColumns(),getRandomConnectionValue(min, max));
        }
        //Dzielenie na mniejsze
        for(int i=1;i<graphs;i++){
            sliceGraph(points);
        }




    }

    private double getRandomConnectionValue(double min,double max){
        return random.nextDouble(min,max);
    }

    private int getRandomNeighbour(Points points,int position){
        int r,w;
        while(true){
            r=random.nextInt(4);
            if(r==0){
                w=position-points.getColumns();
                if(w>-1 && points.getConection(position,w)!=0)
                    return w;
            }else if (r==1){
                w=position-1;
                if(position/points.getColumns()==w/points.getColumns() && points.getConection(position,w)!=0)
                    return w;
            }else if (r==2){
                w=position+1;
                if(position/points.getColumns()==w/points.getColumns() && points.getConection(position,w)!=0)
                    return w;
            }else{
                w=position+points.getColumns();
                if(w<points.getVerses()*points.getColumns() && points.getConection(position,w)!=0)
                    return w;
            }
        }
    }

    private void sliceGraph(Points points){
        do {
            ;
        } while(true);
    }

}
