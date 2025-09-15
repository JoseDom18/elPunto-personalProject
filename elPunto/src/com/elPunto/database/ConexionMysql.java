package com.elPunto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.elPunto.clases.Productos;

public class ConexionMysql {

	static String url = "jdbc:mysql://localhost:3306/elpunto";
	static String user = "root";
	static String password = "";

	public static void insertarProducto(String nombre, double precio, int cantidad, double precioUnitario,
			double precioSugerido, double precioVenta) {
		if (evitarDuplicado(nombre)) {
			System.out.printf("El producto %s no se inserto por que ya existe.%n", nombre);
			return;
		}
		String sql = "INSERT INTO productos (nombre, unidades_x_paquete, precio_paquete, precio_unitario, precio_sugerido, precio_venta, stock) values (?,?,?,?,?,?,?)";
		try (Connection miconexion = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = miconexion.prepareStatement(sql);) {

			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Conexión exitosa a la base de datos de elPunto");

			pstm.setString(1, nombre);
			pstm.setDouble(3, precio);
			pstm.setInt(2, cantidad);
			pstm.setDouble(4, precioUnitario);
			pstm.setDouble(5, precioSugerido);
			pstm.setDouble(6, precioVenta);
			pstm.setInt(7, cantidad);

			int filasAfectadas = pstm.executeUpdate();
			System.out.printf("Se afectaron %d filas.%n", filasAfectadas);

		} catch (Exception e) {
			System.out.println("fallo la conexión a la base de datos del elPunto");
			e.printStackTrace();
		}
	}

	public static boolean evitarDuplicado(String nombre) {
		String sql = "Select 2 from productos where nombre=?";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql);) {

			Class.forName("com.mysql.cj.jdbc.Driver");

			pstm.setString(1, nombre);

			try (ResultSet rs = pstm.executeQuery()) {
				return rs.next();
			}

		} catch (Exception e) {
			System.out.println("Error de conexión al comprobar.");
		}
		return false;
	}

	public static ArrayList<Productos> listaProductos() {
		ArrayList<Productos> productos = new ArrayList<>();

		String sql = "Select * from productos";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();) {

			while (rs.next()) {

				String nombre = rs.getString(2);
				int unidades = rs.getInt(3);
				double precio_paquete = rs.getDouble(4);
				double precio_unitario = rs.getDouble(5);
				double precio_sugerido = rs.getDouble(6);
				double precio_venta = rs.getDouble(7);
				int stock = rs.getInt(8);
				
				Productos p = new Productos(nombre, unidades, precio_paquete , precio_unitario, precio_sugerido, precio_venta, stock);

				productos.add(p);
			}

		} catch (Exception e) {
			System.out.println("Error de conexión al comprobar.");

		}

		return productos;
	}
}
