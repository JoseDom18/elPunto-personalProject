package com.elPunto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConexionMysql {

    static String url = "jdbc:mysql://localhost:3306/elpunto";
    static String user = "root";
    static String password = "";

    public static void insertarProducto (String nombre, double precio, int cantidad, double precioUnitario, double precioSugerido, double precioVenta){
        String sql = "INSERT INTO productos (nombre, unidades_x_paquete, precio_paquete, precio_unitario, precio_sugerido, precio_venta) values (?,?,?,?,?,?)";
        try (
                Connection miconexion = DriverManager.getConnection(url, user, password);
                PreparedStatement pstm = miconexion.prepareStatement(sql);
        ){

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexión exitosa a la base de datos de elPunto");

            pstm.setString(1, nombre);
            pstm.setDouble(2, precio);
            pstm.setInt(3, cantidad);
            pstm.setDouble(4, precioUnitario);
            pstm.setDouble(5, precioSugerido);
            pstm.setDouble(6, precioVenta);

            int filasAfectadas = pstm.executeUpdate();
            System.out.printf("Se afectaron %d filas.%n", filasAfectadas);

        } catch (Exception e) {
            System.out.println("fallo la conexión a la base de datos del elPunto");
            e.printStackTrace();
        }
    }
}
