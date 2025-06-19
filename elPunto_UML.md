<!-- cSpell:language=es -->
# Proyecto El punto 🏪📦💸💹

## Introducción

Este proyecto tiene como objetivo desarrollar una aplicación de escritorio para llevar la administración básica de mi negocio personal; personalmente siento que es muy tedioso sacar las cuentas al momento de hacer la venta cuando los artículos son muchos. Otro factor importante que observo es que no sé cuánto gano con la venta de mis productos; tampoco sé cuánto inventario me queda de cada producto. No llevo un registro de mis clientes ni de mis ventas. Con esto en mente, el objetivo de este proyecto es hacer un software en donde registre mis productos, que al registrarlos automáticamente calcule el precio de mayoreo por unidad. También que al momento de hacer una venta se descuenten los productos en automático y me avise cuando me esté quedando sin inventario de un producto. Que pueda agregar producto a mi inventario pudiendo actualizar el precio de mayoreo si este sufrió algún cambio, así los productos tendrán un precio actualizado con el cual calcular la ganancia de buena manera. Que al momento de hacer una venta pueda agregar un cliente para tener datos de compra de mis clientes, como quién fue el que me compró más. Una parte esencial del software es que pueda mostrar mis ganancias por día, semana, mes y año. Pueda saber cuál es el producto más vendido y qué producto se queda estancando, que casi no se vende. Pero principalmente eso, quiero saber cuánto está generando mi negocio y qué productos son los que prefieren mis clientes. 

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

