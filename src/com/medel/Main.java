package com.medel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<String> resultado = new ArrayList<String>();

    public static void main(String[] args) {
        int[] numeros = new int[] {4, 5, 9, 13, 1, 10};
        encuentraSuma(new int[] {184, 180, 163, 156, 213, 205, 175, 208, 11, 36, 7, 10, 40, 30, 15, 23, 1, 39, 35, 25, 5, 32, 28, 24, 38, 21, 31, 18, 33, 6, 13, 14, 9, 20, 17, 16, 4, 26, 27, 37, 3, 12, 34, 22, 29, 2, 19, 8}, 6, 288);
        for(String x : resultado){
            System.out.println(x);
        }
        generarArchivo();
    }

    public static void encuentraSuma (int [] numeros, int cantidadOperando, int objetivo){
        List<Integer> list = new ArrayList<Integer>();
        for(int x = 0 ; x < numeros.length; x++){
            list.add(numeros[x]);
        }

        encuentraSuma(list,cantidadOperando,objetivo,new ArrayList<>(),1);
    }

    public static void guardarResultado(List<Integer> arreglo){
        arreglo = arreglo.stream().sorted().collect(Collectors.toList());
        String x = arreglo.get(0)+","+arreglo.get(1)+","+arreglo.get(2);

        boolean bandera = false;
        for(String valor : resultado){
            if(valor.equals(x)){
                bandera=true;
            }
        }
        if(!bandera) {
            resultado.add(x);
        }
    }

    public static List<Integer> encuentraSuma(List<Integer> numeros,
                                              int cantidadOperando,
                                              int objetivo,
                                              List<Integer> resultados,
                                              int vuelta){
        if (cantidadOperando == 1) {
            for(int y = 0 ; y<numeros.size() ; y++) {
                if (numeros.get(y) + sumaList(resultados) == objetivo) {
                    resultados.add(numeros.get(y));
                    guardarResultado(resultados);
                    resultados.remove(resultados.get(resultados.size()-1));
                }
            }
            return resultados;
        }
        List<Integer> real = cloneList(numeros);
        for(int x = 0 ; x<numeros.size() ; x++){
            numeros=cloneList(real);
            resultados.add(numeros.get(x));
            numeros.remove(numeros.get(x));
            encuentraSuma(numeros,
                    cantidadOperando-1,
                    objetivo,
                    resultados,vuelta+1
            );
            resultados.remove(resultados.get(resultados.size()-1));

        }
        return resultados;
    }

    public static int sumaList( List<Integer> numeros){
        int suma = 0;
        for(int x = 0 ; x < numeros.size(); x++){
            suma = suma+numeros.get(x);
        }
        return suma;
    }

    public static List<Integer> cloneList(List<Integer> req){
        List<Integer> res = new ArrayList<Integer>();
        for (int x = 0 ; x<req.size(); x++){
            res.add(req.get(x));
        }
        return res;
    }

    public static void generarArchivo(){
        File file = new File (".\\archivo.txt");
        String contenido = "";
        for (String x : resultado){
            contenido= contenido+x+"\n";
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void burbuja(int[] A) {
        int i, j, aux;
        for (i = 0; i < A.length - 1; i++) {
            for (j = 0; j < A.length - i - 1; j++) {
                if (A[j + 1] < A[j]) {
                    aux = A[j + 1];
                    A[j + 1] = A[j];
                    A[j] = aux;
                }
            }
        }
    }

}
