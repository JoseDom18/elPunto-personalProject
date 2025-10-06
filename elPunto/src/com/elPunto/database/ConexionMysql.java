package com.elPunto.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				int unidades = rs.getInt(3);
				double precio_paquete = rs.getDouble(4);
				double precio_unitario = rs.getDouble(5);
				double precio_sugerido = rs.getDouble(6);
				double precio_venta = rs.getDouble(7);
				int stock = rs.getInt(8);

				Productos p = new Productos(id, nombre, unidades, precio_paquete, precio_unitario, precio_sugerido,
						precio_venta, stock);

				productos.add(p);
			}

		} catch (Exception e) {
			System.out.println("Error de conexión al comprobar.");

		}

		return productos;
	}

	public static int insertarVenta(Date fecha) {
		String sql = "INSERT INTO ventas (fecha, total_x_venta, ganancia) VALUES (?, 0, 0)";
		int venta_id = -1;
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			pstm.setDate(1, fecha);

			int filas = pstm.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = pstm.getGeneratedKeys()) {
					if (rs.next()) {
						venta_id = rs.getInt(1);
						System.out.printf("Venta id: %d", venta_id);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error de conexión al insertar en ventas.");
			e.printStackTrace();
		}
		return venta_id;
	}

	public static void actualizarVenta(int venta_id, double total_venta, double ganacia) {
		String sql = "UPDATE ventas SET total_x_venta = ?, ganancia = ? WHERE venta_id = ?";

		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql)) {

			pstm.setDouble(1, total_venta);
			pstm.setDouble(2, ganacia);
			pstm.setInt(3, venta_id);

			int filas = pstm.executeUpdate();
			System.out.printf("Venta de registrada con exito. Filas afectadas %d", filas);
			System.out.println("");
		} catch (Exception e) {
			System.out.println("Error de conexión al insertar en ventas.");
		}

	}

	public static void insertarArticulos(int id_venta, int id_producto, int cantidad) {
		String sql = "INSERT INTO articulos_x_venta (venta_id, producto_id, cantidad) VALUES (?, ?, ?)";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql);) {

			pstm.setInt(1, id_venta);
			pstm.setInt(2, id_producto);
			pstm.setInt(3, cantidad);

			int filas = pstm.executeUpdate();
			 System.out.printf("Inserción exitosa se afectaron %d filas%n", filas);
			// System.out.println("");

		} catch (Exception e) {
			System.out.println("Error de conexión al insertar en articulos por venta.");
		}

	}

	public static void descontarStock(int cantidad, int id) {
		String sql = "UPDATE productos SET stock = stock - ? WHERE producto_id = ?";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql);) {

			pstm.setInt(1, cantidad);
			pstm.setInt(2, id);

			int filas = pstm.executeUpdate();
			 System.out.printf("Inserción exitosa se afectaron %d filas%n", filas);

		} catch (Exception e) {
			System.out.println("Error de conexión al descontar Stock.");
		}

	}

	public static void aumentarStock(int cantidad, int id) {
		String sql = "UPDATE productos SET stock = stock + ? WHERE producto_id = ?";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstm = con.prepareStatement(sql);) {

			pstm.setInt(1, cantidad);
			pstm.setInt(2, id);

			int filas = pstm.executeUpdate();
			System.out.printf("Inserción exitosa se afectaron %d filas%n", filas);

		} catch (Exception e) {
			System.out.println("Error de conexión al aumentar Stock.");
		}

	}

	public static double gananciasDelDia() {
		String sql = "SELECT IFNULL(sum(ganancia), 0) ganancia FROM ventas WHERE fecha = CURDATE()";
		double ganancia = 0;

		try (Connection con = DriverManager.getConnection(url, user, password);
				Statement pstm = con.createStatement();
				ResultSet rs = pstm.executeQuery(sql);) {

			while (rs.next()) {
				ganancia = rs.getDouble(1);
			}

		} catch (Exception e) {
			System.out.println("Error de conexion al consultar ganancias.");
		}
		return ganancia;

	}

	public static double ganancias7Dias() {
		String sql = "SELECT sum(ganancia) ganancia FROM ventas WHERE fecha >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
		double ganancia = 0;

		try (Connection con = DriverManager.getConnection(url, user, password);
				Statement pstm = con.createStatement();
				ResultSet rs = pstm.executeQuery(sql);) {

			while (rs.next()) {
				ganancia = rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println("Error de conexion al consultar ganancias.");
		}
		return ganancia;

	}

	public static double ganancias30Dias() {
		String sql = "SELECT sum(ganancia) ganancia FROM ventas WHERE fecha >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)";
		double ganancia = 0;

		try (Connection con = DriverManager.getConnection(url, user, password);
				Statement pstm = con.createStatement();
				ResultSet rs = pstm.executeQuery(sql);) {

			while (rs.next()) {
				ganancia = rs.getDouble(1);
			}

		} catch (Exception e) {
			System.out.println("Error de conexion al consultar ganancias.");
		}
		return ganancia;

	}

}
