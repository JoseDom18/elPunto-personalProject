package com.elPunto.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMysql {

    static String url = "jdbc:mysql://localhost:3306/elpunto";
    static String user = "root";
    static String password = "";

    public static void conexiondb (){
        try (
                Connection miconexion = DriverManager.getConnection(url, user, password);
        ){
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexión exitosa a la base de datos de elPunto");

            // crear conexion

        } catch (Exception e) {
            System.out.println("fallo la conexión a la base de datos del elPunto");
            e.printStackTrace();
        }
    }
}
