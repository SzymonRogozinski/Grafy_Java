package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader {
    Scanner file_scanner; //Skaner pliku
    String [] bufor; //Bufor do wczytywania wyrażeń z pliku
    ArrayList<Double> converted_bufor; //Tu trzymane są wartości liczbowe, wyciągnięte z bufora
    Points points=null;
    public Points downloadGraph(String filename){
        int verses, columns,i,j,lenght;
        //Wczytanie pliku
        try{
        file_scanner=new Scanner(new File(filename));
        }catch(FileNotFoundException e){
            System.err.println("Nie odnaleziono pliku");
            System.exit(1);
        }
        //Wczytanie wymiarów
        try {
            bufor = (file_scanner.nextLine().replace(":","")).split(" ");
        }catch (NoSuchElementException e){
            System.err.println("Plik nie zawiera wymiarów");
            System.exit(1);
        }
        //Czy zawiera tylko dwie liczby
        if((converted_bufor = toDoubleArrayList(bufor)).size()!=2) {
            System.err.println("Błędny format pliku! Wymiar to dwie liczby");
            System.exit(1);
        }
        verses = converted_bufor.get(0).intValue();
        columns = converted_bufor.get(1).intValue();
        points = new Points(verses, columns);
        lenght=verses*columns;
        //Wczytanie grafu
        for(i=0;i<lenght;i++){
            //Pierw pobiera nową linię, następnie pozbywa się ":" i tworzy tablicę String
            try {
                bufor = (file_scanner.nextLine().replace(":", "")).split(" ");
            }catch(NoSuchElementException e){
                break;
            }
            //Czy podano wierzchołki w odpowiedni sposób
            if((converted_bufor = toDoubleArrayList(bufor)).size()%2!=0){
                System.err.println("Błędny format pliku! Droga nie ma wartości");
                System.exit(1);
            }
            j=0;
            while(j< converted_bufor.size()){
                try {
                    points.setOneWayConnection(i, converted_bufor.get(j).intValue(), converted_bufor.get(j + 1));
                }catch(IllegalArgumentException e){
                    System.err.println("Wierzchołek " + i + ": " + "Błędny format pliku! Wierzchołki są niepołączone");
                    System.exit(1);
                }
                j+=2;
            }
        }
        if(i!=lenght){
            System.err.println("Błędny format pliku! Złe wymiary grafu");
            System.exit(1);
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
