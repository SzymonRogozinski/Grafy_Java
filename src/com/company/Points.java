package com.company;

import java.util.function.BiFunction;

//Przechowywanie punktów
public class Points {
    //Wymiary
    final private int verses,columns;
    private double [][] connection;

    public Points(int verses,int columns) {
        if(verses<1 || columns <1){
            System.err.println("Niepoprawne wymiary grafu");
            System.exit(1);
        }
        this.verses=verses;
        this.columns=columns;
        connection=new double[verses*columns][4];
    }

    public double getConection(int position, int neighbour){
        int x=ifNeighbour(position, neighbour);
        if(x==-1)
            throw new IllegalArgumentException("Podane wartości nie sąsiadują ze sobą!");
        return connection[position][x];
    }

    public int getVerses() {
        return verses;
    }

    public int getColumns() {
        return columns;
    }

    //Do wczytywania z pliku, Tworzy połączenie w jedną stronę
    public void setOneWayConnection(int position, int neighbour, double value){
        int x=ifNeighbour(position, neighbour);
        if(x==-1)
            throw new IllegalArgumentException("Podane wartości nie sąsiadują ze sobą!");
        connection[position][x]=value;
    }

    //Do generowania, Tworzy połączenie w dwie strony
    public void setTwoWayConnection(int position, int neighbour,double randomValue){
        int x=ifNeighbour(position, neighbour);
        if(x==-1)
            throw new IllegalArgumentException("Podane wartości nie sąsiadują ze sobą!");
        connection[position][x]=randomValue;
        connection[neighbour][ifNeighbour(neighbour,position)]=randomValue;
    }

    public void destroyConnection(int position, int neighbour){
        int x=ifNeighbour(position, neighbour);
        if(x==-1)
            throw new IllegalArgumentException("Podane wartości nie sąsiadują ze sobą!");
        connection[position][x]=0.0;
        connection[neighbour][ifNeighbour(neighbour,position)]=0.0;
    }

    public int ifNeighbour(int position, int neighbour){
        if(position<0 || neighbour<0 || position>columns*verses-1 || neighbour>columns*verses-1)
            return -1;
        //Góra
        if(position-columns==neighbour)
            return 0;
        //Lewo
        else if(position-1==neighbour && position/columns==neighbour/columns)
            return 1;
        //Prawo
        else if(position+1==neighbour && position/columns==neighbour/columns)
            return 2;
        //Dół
        else if(position+columns==neighbour)
            return 3;
        else
            return -1;
    }

    public int Neighbour(int position, int index){
        if(index==0 && position>columns)
            return position-columns;
        else if(index==1 && position/columns==(position-1)/columns)
            return position-1;
        else if(index==2 && position/columns==(position+1)/columns)
            return position+1;
        else if (index==3 && position+columns<columns*verses)
            return position+columns;
        else
            return -1;
    }

    public int howManyNeighbour(int position){
        int i,h=0;
        for(i=0;i<4;i++)
            if(connection[position][i]!=0)
                h++;
        return h;
    }

    public String pointNeighbourPrint(int position){
        String s="";
        if(position-columns>-1)
            s+=(position-columns) + " :" + connection[position][0] + " ";
        if((position-1)/columns==position/columns && position>0)
            s+=(position-1) + " :" + connection[position][1] + " ";
        if((position+1)/columns==position/columns)
            s+=(position+1) + " :" + connection[position][2] + " ";
        if(position+columns<columns*verses)
            s+=(position+columns) + " :" + connection[position][3] + " ";
        return s;

    }

}
