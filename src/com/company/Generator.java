package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private static final Random random=new Random();

    public void generateGraph(Points points,double min,double max,int graphs){
        int lenght =points.getVerses() * points.getColumns();
        //Generowanie jednego grafu
        for(int i=0;i<lenght;i++){
            //prawo
            if(i/points.getColumns()==(i+1)/points.getColumns())
                points.setTwoWayConnection(i,i+1,2,getRandomConnectionValue(min, max));
            //dół
            if(i/ points.getColumns()< points.getVerses()-1)
                points.setTwoWayConnection(i,i+points.getColumns(),3,getRandomConnectionValue(min, max));
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
            }else if (r==1){
                w=position-1;
            }else if (r==2){
                w=position+1;
            }else{
                w=position+points.getColumns();
            }
            if(points.ifNeighbour(position,w)!=-1)
                return w;
        }
    }

    private void sliceGraph(Points points){
        ArrayList<Integer> way=new ArrayList<>();
        int control,w,next_w,move,next_move,slice=0;
        int lenght= points.getColumns() * points.getVerses();
        //Znalezienie początku
        do {
            w=random.nextInt(lenght);
        } while(points.howManyNeighbour(w)==4 || points.howManyNeighbour(w)==0);
        way.add(w);
        //Tworzenie ścieżki
        control=0; //Służy do sprawdzenia, czy kod nie wykonuję się za długo
        do {
            next_w=getRandomNeighbour(points,w);
            if(!way.contains(next_w)){
                way.add(next_w);
                w=next_w;
                control=0;
            }else
                control++;
        } while(points.howManyNeighbour(w)==4 && control<40);
        if(control==40)
            return;
        //Cięcie
        //try i catch wyłapuje czy w danym przypadku nie wystąpił początek albo koniec, gdzie może nie mieć co ciąć
        //Co jeśli są tylko dwa punkty
        if(way.size()==2){
            if(points.howManyNeighbour(way.get(0))==1 && points.howManyNeighbour(way.get(1))==1){ //Jeśli są połączone tylko ze sobą
                points.destroyConnection(way.get(0),way.get(1));
            } else{ //Jeśli istnieją połączenia z innymi punktami
                for (int j = 0; j < 4; j++) {
                    if(points.Neighbour(way.get(0),j)==way.get(1)){
                        try {
                            points.destroyConnection(way.get(0), points.Neighbour(way.get(0), j));
                        } catch (IllegalArgumentException ignored) { }
                    }
                }
                for (int j = 0; j < 4; j++) {
                    if(points.Neighbour(way.get(1),j)==way.get(0)){
                        try {
                            points.destroyConnection(way.get(1), points.Neighbour(way.get(1), j));
                        } catch (IllegalArgumentException ignored) { }
                    }
                }
            }
        }else {
            //Droga dłuższa niż dwa
            //Pierwszy krok
            w = way.get(0);
            next_w = way.get(1);
            move = points.ifNeighbour(w, next_w);
            if (move == 0 || move == 3) { //Tnie po lewo
                slice = 1;
            } else if (move == 1 || move == 2) { //Tnie od dołu
                slice = 3;
            } else {
                System.err.println("Błąd przy tworzeniu ścieżki tnącej");
                System.exit(1);
            }
            try {
                points.destroyConnection(w, points.Neighbour(w, slice));
            } catch (IllegalArgumentException ignored) {
            }
            w = next_w;
            //Reszta ściężki
            for (int i = 2; i < way.size(); i++) {
                next_w = way.get(i);
                next_move = points.ifNeighbour(w, next_w);
                if ((next_move == 0 || next_move == 3) && next_move == move) { //Tnie po lewo
                    slice = 1;
                } else if ((next_move == 1 || next_move == 2) && next_move == move) { //Tnie od dołu
                    slice = 3;
                } else if ((next_move == 0 || next_move == 3)) { //Tnie po lewo, ale wcześniej też od dołu
                    slice = 1;
                    points.destroyConnection(w, points.Neighbour(w, 3));
                } else if ((next_move == 1 || next_move == 2)) { //Tnie od dołu, ale wcześniej też po lewej
                    slice = 3;
                    points.destroyConnection(w, points.Neighbour(w, 1));
                } else {
                    System.err.println("Błąd przy tworzeniu ścieżki tnącej");
                    System.exit(1);
                }
                points.destroyConnection(w, points.Neighbour(w, slice));
                w=next_w;
                move=next_move;
            }
            //Ostatni element
            try {
                points.destroyConnection(next_w, points.Neighbour(next_w, slice));
            } catch (IllegalArgumentException ignored) {
            }
        }
    }
}
