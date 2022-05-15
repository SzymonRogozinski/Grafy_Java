package com.company;

import java.util.ArrayList;
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
        ArrayList<Integer> way=new ArrayList<>();
        int w,next_w,move,next_move,slice=0;
        int lenght= points.getColumns() * points.getVerses();
        //Znalezienie początku
        do {
            w=random.nextInt(lenght);
        } while(points.howManyNeighbour(w)<4 && points.howManyNeighbour(w)>0);
        way.add(w);
        //Tworzenie ścieżki
        do {
            next_w=getRandomNeighbour(points,w);
            if(!way.contains(next_w)){
                way.add(next_w);
                w=next_w;
            }
        } while(points.howManyNeighbour(w)==4);
        //Cięcie
        //try i catch wyłapuje czy w danym przypadku nie wystąpił początek albo koniec, gdzie może nie mieć co ciąć
        //Pierwszy krok
        w= way.get(0);
        next_move=way.get(1);
        move=points.ifNeighbour(w,next_w);
        //Tnie po lewo
        if(move==0 || move==3){
            slice=1;
        }
        //Tnie od dołu
        else if(move==1 || move==2){
            slice=3;
        }
        else{
            System.err.println("Błąd przy tworzeniu ścieżki tnącej");
            System.exit(1);
        }
        try {
            points.destroyConnection(w, points.Neighbour(w, slice));
        }catch(IllegalArgumentException e){
            ;
        }
        w=next_w;
        //Reszta ściężki
        for(int i=2;i<way.size();i++){
            next_w=way.get(i);
            next_move= points.ifNeighbour(w,next_w);
            //Tnie po lewo
            if((next_move==0 || next_move==3) && next_move==move){
                slice=1;
            }
            //Tnie od dołu
            else if((next_move==1 || next_move==2) && next_move==move){
                slice=3;
            }
            //Tnie po lewo, ale wcześniej też od dołu
            else if((next_move==0 || next_move==3)){
                slice=1;
                try {
                    points.destroyConnection(w, points.Neighbour(w, 3));
                }catch(IllegalArgumentException e){
                    ;
                }
            }
            //Tnie od dołu, ale wcześniej też po lewej
            else if((next_move==1 || next_move==2)){
                slice=3;
                try {
                    points.destroyConnection(w, points.Neighbour(w, 1));
                }catch(IllegalArgumentException e){
                    ;
                }
            }
            else{
                System.err.println("Błąd przy tworzeniu ścieżki tnącej");
                System.exit(1);
            }
            try {
                points.destroyConnection(w, points.Neighbour(w, slice));
            }catch(IllegalArgumentException e){
                ;
            }
        }

    }

}
