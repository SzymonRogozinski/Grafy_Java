package com.company;

public class Main {

    public static void main(String[] args) {
	    Points p=new Points(3,3);
        Generator g= new Generator();
        g.generateGraph(p,0,1);
        for(int i=0;i<9;i++)
            p.pointNeighbourPrint(i);
    }
}
