package com.elPunto.clases;

public class Productos {

	private String nombre;
	private int unidades_x_paquete;
	private double precio_paquete;
	private double precio_unitario;
	private double precio_sugerido;
	private double precio_venta;
	
	public Productos (String nombre, int unidades_x_paquete, double precio_paquete, double precio_unitario, double precio_sugerido, double precio_venta) {
		this.nombre = nombre;
		if (unidades_x_paquete < 0 || precio_paquete < 0 || precio_unitario < 0 || precio_sugerido < 0 || precio_venta < 0) throw new IllegalArgumentException("Las cantidades deben ser positivas");
		this.unidades_x_paquete = unidades_x_paquete;
		this.precio_paquete = precio_paquete;
		this.precio_unitario = precio_unitario;
		this.precio_sugerido = precio_sugerido;
		this.precio_venta = precio_venta;
		
	}

	public String getNombre() {
		return nombre;
	}

	public int getUnidades_x_paquete() {
		return unidades_x_paquete;
	}

	public double getPrecio_paquete() {
		return precio_paquete;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public double getPrecio_sugerido() {
		return precio_sugerido;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}
	
}
