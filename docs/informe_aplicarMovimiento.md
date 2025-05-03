
# Informe de Proceso de `aplicarMovimiento`

## 1. Introducción

La función `aplicarMovimiento` tiene como objetivo gestionar el traslado de vagones entre tres trenes (`t1`, `t2`, `t3`) de acuerdo con el tipo de movimiento especificado. Los movimientos pueden ser de dos tipos: **Movimiento Uno** y **Movimiento Dos**, dependiendo de la dirección y el número de vagones a mover.

## 2. Descripción del Proceso de Ejecución

### Movimiento Uno
- **Movimiento Uno (n > 0):** Se trasladan los primeros `n` vagones de `t1` a `t2`.
- **Movimiento Uno (n < 0):** Se trasladan los primeros `|n|` vagones de `t2` a `t1`.

### Movimiento Dos
- **Movimiento Dos (n > 0):** Se trasladan los primeros `n` vagones de `t1` a `t3`.
- **Movimiento Dos (n < 0):** Se trasladan los primeros `|n|` vagones de `t3` a `t1`.

El proceso de ejecución se realiza en una secuencia de pasos definidos por el tipo de movimiento y la cantidad de vagones especificados.

## 3. Estado de la Pila de Llamados

### Ejemplo de Proceso

**Estado inicial:**
```scala
(List(1,2,3,4,5,6,7), List(), List())
```

**Movimiento aplicado:** `Uno(3)`

**Estado final:** 
```scala
(List(1,2,3,4,5,6,7), List(8,9,10), List())
```

Este proceso se repite para todas las pruebas, actualizando el estado de las pilas `t1`, `t2` y `t3` según el tipo y número de vagones movidos.

## 4. Pruebas de Software

Las pruebas fueron diseñadas para verificar el comportamiento correcto de la función `aplicarMovimiento` bajo diferentes condiciones. A continuación, se describen algunas pruebas representativas.

### Prueba 1: Movimiento Uno con 3 vagones
- **Estado inicial:** `(List(1,2,3,4,5,6,7), List(), List())`
- **Movimiento aplicado:** `Uno(3)`
- **Estado final esperado:** `(List(1,2,3,4,5,6,7), List(8,9,10), List())`

### Prueba 2: Movimiento Dos con 5 vagones
- **Estado inicial:** `(List(1,2,3,4,5,6,7), List(), List())`
- **Movimiento aplicado:** `Dos(5)`
- **Estado final esperado:** `(List(1,2,3,4,5,6,7), List(), List(8,9,10))`

### Prueba 3: Movimiento Uno negativo con 2 vagones
- **Estado inicial:** `(List(), List(8,9), List())`
- **Movimiento aplicado:** `Uno(-2)`
- **Estado final esperado:** `(List(8,9), List(), List())`

## 5. Resultados de las Pruebas Ejecutadas

### Clase: `taller.testAplicarMovimiento`

- **Total de pruebas:** 25  
- **Fallos:** 0  
- **Ignoradas:** 0  
- **Duración total:** 0.500 s  
- **Resultado general:** ✅ 100% exitoso  

| Nombre de la prueba                                         | Duración | Resultado |
|-------------------------------------------------------------|----------|-----------|
| Extra: 20 vagones, prueba sencilla                          | 0s       | ✅ passed |
| Gigante: 50000 vagones, alternancia t2 y t3                 | 0.398s   | ✅ passed |
| Grande 1: 1000 vagones, alternancia cíclica                 | 0.014s   | ✅ passed |
| Grande 2: mover todo a t3                                   | 0.003s   | ✅ passed |
| Grande 3: llevar a t2 y devolver todo                       | 0.002s   | ✅ passed |
| Grande 4: mezcla completa de movimientos                    | 0.003s   | ✅ passed |
| Grande 5: 100 movimientos aleatorios de tamaño 10           | 0.003s   | ✅ passed |
| Grande: 10000 vagones, dividir y reunir                     | 0.031s   | ✅ passed |
| Juguete 1: movimientos simples con 10 vagones               | 0.018s   | ✅ passed |
| Juguete 2: mover de t1 a t3                                 | 0.002s   | ✅ passed |
| Juguete 3: devolver de t2 a t1                              | 0s       | ✅ passed |
| Juguete 4: devolver de t3 a t1                              | 0s       | ✅ passed |
| Juguete 5: combinación de varios movimientos                | 0.001s   | ✅ passed |
| Mediana 1: 500 vagones, 10 Uno(25), luego 10 Dos(25)        | 0.003s   | ✅ passed |
| Mediana 2: ir a t2 y devolver desde t2                      | 0.001s   | ✅ passed |
| Mediana 3: combinaciones Uno y Dos                          | 0.001s   | ✅ passed |
| Mediana 4: 10 movimientos alternos negativos                | 0.003s   | ✅ passed |
| Mediana 5: solo movimientos hacia t2                        | 0.002s   | ✅ passed |
| Mediana: 1000 vagones en 50 movimientos                     | 0.004s   | ✅ passed |
| Pequeña 1: 100 vagones, alternancia Uno y Dos              | 0.002s   | ✅ passed |
| Pequeña 2: mover todo a t2 en bloques de 10                 | 0.002s   | ✅ passed |
| Pequeña 3: ir de t1 a t3 completamente                      | 0.002s   | ✅ passed |
| Pequeña 4: enviar y traer vagones entre t1 y t2             | 0.001s   | ✅ passed |
| Pequeña 5: traer desde t3 a t1 después de enviar            | 0.002s   | ✅ passed |
| Personalizada: caracteres                                   | 0.002s   | ✅ passed |

## 6. Conclusiones

Las pruebas fueron exitosas, y la función `aplicarMovimiento` se comportó como se esperaba en todos los casos. Las pruebas abarcaron desde escenarios simples hasta situaciones con grandes volúmenes de datos, demostrando tanto la funcionalidad como la estabilidad del sistema. Sin embargo, se identificó que los tiempos de ejecución en pruebas con volúmenes altos (como 50000 vagones) pueden incrementarse de forma considerable, lo que sugiere posibles optimizaciones.

## 7. Mejoras Futuras

- Optimizar el manejo de grandes volúmenes de vagones utilizando estructuras de datos más eficientes.
- Ampliar las pruebas para cubrir más casos complejos, como movimientos entre más de dos trenes o con trenes vacíos.
- Implementar un mecanismo de control de errores para evitar movimientos inválidos o fuera de rango.
