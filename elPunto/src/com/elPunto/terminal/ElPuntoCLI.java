package com.elPunto.terminal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.elPunto.clases.Productos;
import com.elPunto.database.ConexionMysql;

public class ElPuntoCLI {

	// pantalla inicial
	public static void pantallaInicial(Scanner in) {

		System.out.println("Bienvenido al punto");
		System.out.println();

		int seleccion = 0;

		do {
			System.out.println("");
			System.out.println("Selecciona una opción(0 para terminar):");
			System.out.println();
			System.out.println("1.- Venta");
			System.out.println("2.- Agregar Producto");
			System.out.println("3.- Ganancias");
			System.out.println("4.- Consulta de inventario");

			System.out.print("opción: ");
			seleccion = in.nextInt();
			in.nextLine();
			System.out.println();

			switch (seleccion) {
			case 1:
				ElPuntoCLI.pantallaVenta(in);
				break;
			case 2:
				ElPuntoCLI.pantallaAgregarProducto(in);
				break;
			case 3:
				ElPuntoCLI.pantallaGanancias(in);
				break;
			case 4:
				ElPuntoCLI.pantallaConsultaInventario(in);
				break;
			case 0:
				System.out.println("Hasta luego mingood. :)");
				break;
			default:
				System.out.println("Selecciona una opción correcta");
				System.out.println();
			}
		} while (seleccion != 0);
	}

	// pantalla de venta
	public static void pantallaVenta(Scanner in) {
		String eleccion = "y";
		ArrayList<Productos> productos = ConexionMysql.listaProductos();
		double total_venta = 0;
		double total_mayoreo = 0;
		LocalDate hoy = LocalDate.now();
		Date hoyDB = Date.valueOf(hoy);
		int venta_id = ConexionMysql.insertarVenta(hoyDB);

		while (eleccion.equalsIgnoreCase("y")) {

			System.out.println();
			//System.out.println("1.- Venta");
			System.out.println();

			// mostrar lista de productos
			System.out.println("<<<Lista de productos>>>");
			Productos.mostrarProductos(ConexionMysql.listaProductos());

			// seleccionar producto
			System.out.print("Seleccione un producto (# de producto): ");
			int opcion = in.nextInt();
			in.nextLine();
			System.out.println("");
			Productos p = productos.get(opcion - 1);
			int id = p.getId();
			System.out.println(p.getNombre());
			System.out.printf("Precio: %.2f%n", p.getPrecio_venta());
			System.out.print("Cantidad: ");
			int cant = in.nextInt();
			in.nextLine();

			while (cant > productos.get(opcion - 1).getStock()) {
				System.out.println("No hay stock de este producto.");
				System.out.printf("Stock: %d%n", productos.get(opcion - 1).getStock());
				System.out.print("Cantidad: ");
				cant = in.nextInt();
				in.nextLine();
			}
			
			ConexionMysql.descontarStock(cant, id);

			System.out.printf("Subtotal: %.2f%n", productos.get(opcion - 1).getPrecio_venta() * cant);
			System.out.print("Continuar: (y/n): ");
			eleccion = in.nextLine();
			System.out.println("");
			total_venta += productos.get(opcion - 1).getPrecio_venta() * cant;
			total_mayoreo += productos.get(opcion - 1).getPrecio_unitario() * cant;

			// ingresar a tabla articulos_x_venta
			ConexionMysql.insertarArticulos(venta_id, id, cant);

		}

		double ganancia = total_venta - total_mayoreo;
		System.out.printf("Total: %.2f%n", total_venta);
		System.out.printf("Ganancia: %.2f%n", ganancia);
		System.out.print("Paga con: ");
		double pago = in.nextDouble();
		in.nextLine();
		System.out.printf("Cambio: %.2f%n", pago - total_venta);

		System.out.print("Confirmar venta(y/n): ");
		String confVenta = in.nextLine();
		if (confVenta.equalsIgnoreCase("y")) {
			// insercion a base de datos
			ConexionMysql.actualizarVenta(venta_id, total_venta, ganancia);
		} else {
			System.out.println("Venta cancelada.");
		}

	}

	// pantalla de agregar producto
	public static void pantallaAgregarProducto(Scanner in) {
		System.out.println();
		System.out.println("2.- Agregar producto");
		System.out.println();

		String nombreProducto;
		double precioProducto;
		int cantidad;
		double precioUnitario;
		double precioSugerido;
		double precioVenta;
		String confirmacion;

		// agregar producto

		System.out.print("Nombre del producto: ");
		nombreProducto = in.nextLine();
		System.out.print("Precio de paquete: ");
		precioProducto = in.nextDouble();
		in.nextLine();
		System.out.print("Cantidad del paquete: ");
		cantidad = in.nextInt();
		in.nextLine();
		precioUnitario = precioProducto / cantidad;
		System.out.printf("Precio por unidad: %.2f%n", precioUnitario);
		precioSugerido = precioUnitario / (1 - .30);
		System.out.printf("Precio sugerido: %.2f%n", precioSugerido);
		System.out.print("Precio de venta: ");
		precioVenta = in.nextDouble();
		in.nextLine();
		System.out.println();

		// confirmacion o volver a empezar
		System.out.print("Confirmar (y/n): ");
		confirmacion = in.nextLine();
		if (confirmacion.equalsIgnoreCase("y")) {

			ConexionMysql.insertarProducto(nombreProducto, precioProducto, cantidad, precioUnitario, precioSugerido,
					precioVenta);

			System.out.println("Producto agregado con exito.");

		} else if (confirmacion.equalsIgnoreCase("n")) {

			System.out.println("Operación cancelada.");
		} else {
			System.out.println("Elija una opción correcta.");
		}

		System.out.println();

	}

	// pantalla ganancias
	public static void pantallaGanancias(Scanner in) {

		System.out.println("1. Ver ganancia del dia.");
		System.out.println("2. Ver ganancia de los ultimos 7 dias.");
		System.out.println("3. Ver ganancia de los ultimos 30 dias.");
		System.out.print("Selecciona una opción: ");
		int select = in.nextInt();
		in.nextLine();
		
		switch (select) {
		case 1:
			// metodo ganancias 7 dias
			System.out.println("");
			System.out.printf("Ganancia del dia: %.2f", ConexionMysql.gananciasDelDia());
			System.out.println("");
			break;
		case 2:
			// metodo ganancias 7 dias
			System.out.println("");
			System.out.printf("Ganancia de los ultimos 7 dias: %.2f", ConexionMysql.ganancias7Dias());
			System.out.println("");
			break;
		case 3:
			// metodo ganancia30 dias
			System.out.println("");
			System.out.printf("Ganancia de los ultimos 30 dias: %.2f", ConexionMysql.ganancias30Dias());
			System.out.println("");
			break;
			default:
				System.out.println("Selecciona una opción valida.");
		}

	}

	// pantalla consulta de inventario
	public static void pantallaConsultaInventario(Scanner in) {
		
		System.out.println("Selecciona un producto para consultar su inventario.");
		System.out.println("");
		Productos.mostrarProductos(ConexionMysql.listaProductos());
		ArrayList<Productos> p = ConexionMysql.listaProductos();
		System.out.print("Opción: ");
		int select = in.nextInt();
		in.nextLine();
		Productos pro = p.get(select - 1);
		
		System.out.printf("Producto: %s%n", pro.getNombre());
		System.out.printf("Stock actual: %d%n", pro.getStock());
		
		System.out.println("Aumentar stock?(y/n): ");
		String yn = in.nextLine();
		
		if (yn.equalsIgnoreCase("y")) {
			System.out.print("Cantidad: ");
			int cant = in.nextInt();
			in.nextLine();
			ConexionMysql.aumentarStock(cant, pro.getId());
		} else if (yn.equalsIgnoreCase("n")) {
			System.out.println("Hasta la proxima.");
		} else System.out.println("Elige una (y/n) para continuar.");
		

	}
}
