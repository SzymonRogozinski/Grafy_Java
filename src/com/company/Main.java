package com.company;

public class Main {

    public static void main(String[] args) {
	    Points p=new Points(2,3);
        p.setConnection(1,1,0.6);
        System.out.println(p.ifNeighbour(1,1));
        System.out.println(p.ifNeighbour(0,1));

    }
}
