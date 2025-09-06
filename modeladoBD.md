# Modelado de base de datos para el punto

<div align="justify">
Requiero una base de datos que guarde datos de productos, datos por venta.
</div>

## productos
- producto_id INT ***PRIMARY KEY***
- nombre varchar(50) NOT NULL
- unidades_x_paquete int NOT NULL
- precio_paquete DECIMAL(6, 2) NOT NULL
- precio_unitario DECIMAL(6, 2) NOT NULL
- precio_sugerido DECIMAL(6, 2) NOT NULL
- precio_venta INT NOT NULL