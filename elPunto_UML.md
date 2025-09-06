<!-- cSpell:language=es -->
# Proyecto El punto 🏪📦💸💹

## Introducción

<div align="justify">
Este proyecto tiene como objetivo desarrollar una aplicación de escritorio para llevar la administración básica de mi negocio personal; personalmente siento que es muy tedioso sacar las cuentas al momento de hacer la venta cuando los artículos son muchos. Otro factor importante que observo es que no sé cuánto gano con la venta de mis productos; tampoco sé cuánto inventario me queda de cada producto. No llevo un registro de mis clientes ni de mis ventas. Con esto en mente, el objetivo de este proyecto es hacer un software en donde registre mis productos, que al registrarlos automáticamente calcule el precio de mayoreo por unidad. También que al momento de hacer una venta se descuenten los productos en automático y me avise cuando me esté quedando sin inventario de un producto. Que pueda agregar producto a mi inventario pudiendo actualizar el precio de mayoreo si este sufrió algún cambio, así los productos tendrán un precio actualizado con el cual calcular la ganancia de buena manera. Que al momento de hacer una venta pueda agregar un cliente para tener datos de compra de mis clientes, como quién fue el que me compró más. Una parte esencial del software es que pueda mostrar mis ganancias por día, semana, mes y año. Pueda saber cuál es el producto más vendido y qué producto se queda estancando, que casi no se vende. Pero principalmente eso, quiero saber cuánto está generando mi negocio y qué productos son los que prefieren mis clientes.
</div> 

## Indice

1. [Introducción](#introducción)
1. [Procesos](#procesos)
1. [Modelado UML](#modelado-uml)
  2.1 [Diagrama de casos de uso](#diagrama-de-casos-de-uso)

## Procesos

### Calcular precio mayoreo

Para sacar un precio de mayoreo al comprar un producto estos son los pasos que hago.

1. Anotar el precio del paquete en mi libreta.
1. Anotar la cantidad de producto que trae el paquete.
1. Dividir el precio del paquete entre su cantidad.
1. Anotar el precio de mayoreo por unidad.

### Calcular precio de venta

Para calcular el precio de venta de un articulo estos son los pasos que sigo.

1. Multiplico el precio del paquete por 1.25 para aumentar un 25% el costo del producto.
1. Divido el precio aumentado entre la cantidad de piezas que trae el paquete.
1. Redondeo la cantidad a un numero por encima de las décimas o pongo un precio que considere razonable.
1. Anoto en mi libreta el precio de venta por unidad.

### Realizar una venta

Estos son los pasos que aplico al momento de realizar una venta. Las cuentas hoy en dia las hago mentales y no llevo un registro diario de venta.

1. El cliente elige sus artículos a comprar.
1. Cuento los artículos que sean iguales y los multiplico por su precio unitario.
1. En caso de que seleccione diferentes artículos vuelvo a contar y multiplicar por su precio unitario y sumo a la cantidad de la cuenta.
1. Una vez sumados todos los artículos obtengo el total.
1. Doy la cantidad total al cliente y el cliente me paga, recibo el efectivo.
1. En el caso que el monto que se reciba no sea exacto, hago una cuenta mental para restar la cantidad al monto recibido y dar el cambio al cliente.
1. Con esto se genera una venta en mi negocio.

### Manejar inventario

Actualmente para manejar mi inventario, tampoco llevo un registro. Pero para resurtir hago lo siguiente.

1. Voy al lugar donde vendo mis dulces.
1. Observo cuales artículos ya no tengo y los apunto.
1. Observo cuales artículos están por terminarse y los apunto.
1. Voy al lugar donde vendan los artículos y los resurto.
1. Veo si el precio del paquete es el mismo de la vez pasada.
1. En caso de que no sea el mismo, aplico el proceso de calcular venta y si es necesario ajusto el precio del producto.

## Modelado UML

### Casos de uso

#### UC 1
Nombre: registro de producto.
Actor: vendedor.
Descripción: el vendedor puede agregar productos a la app con sus datos para llevar un registro.
1. El vendedor selecciona inventario el sistema entra a la sección de inventario.
2. El vendedor selecciona agregar producto.
3. El sistema lanza los campos a recibir primero pide el nombre del producto.
4. El sistema pide el precio del paquete.
5. El sistema pide la cantidad del paquete.
6. El sistema pide el precio unitario de venta por defecto.
6. El sistema con estos datos crea en automático el precio unitario al mayoreo.
8. Una vez dado todos los datos el sistema agrega el producto al inventario.
Precondición: El producto no tiene que existir en el sistema.
Postcondición: El producto queda registrado en el sistema.
Escenario: El sistema pide todos los datos con éxito y el producto queda registrado.
Escenario alternativo: El producto ya esta registrado y el sistema no crea el nuevo registro.

#### UC 2
Nombre: Aumentar inventario.
Actor: vendedor.
Descripción: El usuario aumenta el inventario de un producto. Los productos del mismo tipo tienen que ser agrupados en un solo tipo de producto no se tomara en cuenta sabores ni presentaciones, se generalizara por tipo de producto eg. sabritas, galletas, etc.
1. El usuario selecciona inventario.
2. El sistema arroja opciones
Precondición:
Postcondición:
Escenario:
Escenario alternativo:

#### UC 3
Nombre:
Actor:
Descripción:
Precondición:
Postcondición:
Escenario:
Escenario alternativo:

#### UC 4
Nombre:
Actor:
Descripción:
Precondición:
Postcondición:
Escenario:
Escenario alternativo:

#### UC 5
Nombre:
Actor:
Descripción:
Precondición:
Postcondición:
Escenario:
Escenario alternativo:

#### UC 6
Nombre:
Actor:
Descripción:
Precondición:
Postcondición:
Escenario:
Escenario alternativo:

#### UC 7
Nombre:
Actor:
Descripción:
Precondición:
Postcondición:
Escenario:
Escenario alternativo:

Teniendo en cuenta lo anterior los casos de uso que se me ocurren a mi software en estos momentos son:

- Calcular precio mayoreo unitario
- Calcular precio de venta unitario
- Realizar una venta
  - Calcular total de la venta
  - Calcular cambio de la venta
  - Registro de venta (datetime)
  - Asignar venta a un cliente
  - Restar artículos al inventario de manera automática
- Manejar inventario
  - Agregar productos
  - Quitar productos
  - Consultar productos
  - Modificar productos
  - Producto mas vendido
  - Producto menos vendido
  - Alerta de stock en inventario
- Calcular ganancias diarias, semanales, mensuales y cuatrimestrales.
- Administración de clientes
  - Agregar cliente
  - Eliminar cliente
  - Modificar cliente
  - Consultar cliente




### Diagrama de casos de uso

