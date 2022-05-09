package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader {
    Scanner file_scanner; //Skaner pliku
    String [] bufor;
    ArrayList<Double> converted_bufor;
    Points points=null;
    public Points downloadGraph(String filename){
        //Wczytanie pliku
        try{
        file_scanner=new Scanner(new File(filename));
        }catch(FileNotFoundException e){
            System.err.println("Nie odnaleziono pliku");
            System.exit(1);
        }
        //Wczytanie wymiarów
        bufor=file_scanner.nextLine().split(" ");
        int verses, columns,i,j,lenght = 0;
        try {
            converted_bufor = toDoubleArrayList(bufor);
            verses = converted_bufor.get(0).intValue();
            columns = converted_bufor.get(1).intValue();
            points = new Points(verses, columns);
            lenght=verses*columns;
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Plik zawiera niepoprawne dane");
            System.exit(1);
        }
        //Wczytanie grafu
        for(i=0;i<lenght;i++){
            //Pierw pobiera nową linię, następnie pozbywa się : i tworzy tablicę String
            bufor=(file_scanner.nextLine().replace(":","")).split(" ");
            converted_bufor = toDoubleArrayList(bufor);
            j=0;
            while(j< converted_bufor.size()){
                try  {
                    points.setOneWayConnection(i, converted_bufor.get(j).intValue(), converted_bufor.get(j+1));
                }catch (InputMismatchException e){
                    System.err.println("Plik zawiera niepoprawne dane");
                  System.exit(1);
                }catch(NoSuchElementException e){
                    System.err.println("Plik zawiera za mało wierzchołków");
                   System.exit(1);
                }
                j+=2;
            }
        }
        return points;
    }

    public ArrayList<Double> toDoubleArrayList(String [] line){
        ArrayList<Double> value=new ArrayList<>();
        for(int i=0;i< line.length;i++){
            try {
                value.add(Double.parseDouble(line[i]));
            }catch(NumberFormatException e){
                ;
            }
        }
        return value;
    }






}
