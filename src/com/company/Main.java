package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    Reader r=new Reader();
        Graf g=new Graf("mygraph");
        g.grafToFile("elo");
        g.printGraf();
    }
}
