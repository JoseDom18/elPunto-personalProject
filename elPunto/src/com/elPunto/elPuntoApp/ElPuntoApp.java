package com.elPunto.elPuntoApp;

import com.elPunto.database.ConexionMysql;
import com.elPunto.terminal.ElPuntoCLI;

import java.util.Scanner;

public class ElPuntoApp {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        //ConexionMysql.conexiondb();
        ElPuntoCLI.pantallaInicial(in);
    }
}
