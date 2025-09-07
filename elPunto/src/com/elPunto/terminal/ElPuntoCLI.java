package com.elPunto.terminal;

import java.util.Scanner;

public class ElPuntoCLI {

    // pantalla inicial
    public static void pantallaInicial (Scanner in) {

        System.out.println("Bienvenido al punto");
        System.out.println();

        int seleccion = 0;

        do {
            System.out.println("Selecciona una opción:");
            System.out.println();
            System.out.println("1.- Venta");
            System.out.println("2.- Agregar Producto");
            System.out.println("3.- Ganancias");
            System.out.println("4.- Consulta de inventario");

            System.out.print("opción: ");
            seleccion = in.nextInt();
            in.nextLine();
            System.out.println();

        } while (seleccion != 0);

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
            default:
                System.out.println("Selecciona una opción correcta");
                System.out.println();
        }
    }

    // pantalla de venta
    public static void pantallaVenta(Scanner in) {

    }

    // pantalla de agregar producto
    public static void pantallaAgregarProducto(Scanner in) {

        System.out.println("2.- Agregar producto");
        System.out.println();

        String nombreProducto;
        double precioProducto;
        int cantidad;
        double precioSugerido;
        double precioVenta;
        String confirmacion;

        // agregar producto

        System.out.print("Nombre del producto: ");
        nombreProducto = in.nextLine();
        in.nextLine();
        System.out.print("Precio de paquete: ");
        precioProducto = in.nextDouble();
        in.nextLine();
        System.out.print("Cantidad del paquete: ");
        cantidad = in.nextInt();
        in.nextLine();
        precioSugerido = precioProducto / cantidad;
        System.out.printf("Precio sugerido: %.2f%n", precioSugerido);
        System.out.print("Precio de venta: ");
        precioVenta = in.nextDouble();
        in.nextLine();

        // confirmacion o volver a empezar
        System.out.print("Confirmar (y/n): ");
        confirmacion = in.nextLine();
        if (confirmacion.equalsIgnoreCase("y")){

        } else if (confirmacion.equalsIgnoreCase("n")) {

        } else {
            System.out.println("Elija una opción correcta.");
        }


        System.out.println("Producto agregado con exito.");

    }

    // pantalla ganancias
    public static void pantallaGanancias(Scanner in) {

    }

    // pantalla consulta de inventario
    public static void pantallaConsultaInventario(Scanner in) {

    }
}
