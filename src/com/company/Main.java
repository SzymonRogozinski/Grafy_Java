package com.company;

public class Main {

    public static void main(String[] args) {
	    Reader r=new Reader();
        Points p=null;
        p=r.downloadGraph("mygraph");
        Graf g=new Graf(p);
        g.printGraf();
    }
}
