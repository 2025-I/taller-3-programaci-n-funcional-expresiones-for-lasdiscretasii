
# Informe de Proceso de `aplicarMovimientos`

## 1. Introducción

La función `aplicarMovimientos` se encarga de aplicar de manera secuencial una lista de movimientos a un estado inicial de trenes representado por una tupla de tres listas: `t1`, `t2` y `t3`. Esta operación permite simular un conjunto completo de maniobras para reorganizar los vagones según se requiera.

La función depende de la correcta implementación de `aplicarMovimiento`, la cual se encarga de aplicar un solo movimiento. El resultado es una lista de estados sucesivos, incluyendo el estado inicial y todos los intermedios generados tras cada operación.

## 2. Descripción del Proceso de Ejecución

La ejecución de `aplicarMovimientos` consiste en los siguientes pasos:

1. Iniciar con un estado base `(t1, t2, t3)`.
2. Recorrer la lista de movimientos (`Maniobra`) en orden.
3. Aplicar cada movimiento sobre el estado actual utilizando `aplicarMovimiento`.
4. Registrar el nuevo estado en una lista acumuladora.
5. Repetir hasta aplicar todos los movimientos.
6. Devolver la lista completa de estados, en orden cronológico.

La implementación hace uso de recursión de cola para mayor eficiencia.

## 3. Estado de la Pila de Llamados

### Ejemplo de Proceso

**Estado inicial:**
```scala
(List(1,2,3,4), List(), List())
```

**Movimientos aplicados:**
```scala
List(Uno(2), Dos(1))
```

**Secuencia de estados:**
```scala
List(
  (List(1,2,3,4), List(), List()),
  (List(1,2), List(3,4), List()),
  (List(1), List(3,4), List(2))
)
```

Cada estado refleja la aplicación secuencial de un movimiento y permite verificar paso a paso cómo evoluciona la posición de los vagones.

## 4. Pruebas de Software

Las pruebas de software para `aplicarMovimientos` cubren distintos tamaños de datos y combinaciones de movimientos. Las categorías incluyen pruebas de juguete, pequeñas, medianas y grandes.

### Prueba 1: Sin movimientos
- **Estado inicial:** `(List(1,2,3), List(), List())`
- **Maniobra:** `List()`
- **Estado final esperado:** `(List(1,2,3), List(), List())`

### Prueba 2: Movimiento Uno(1)
- **Estado inicial:** `(List(1,2,3), List(), List())`
- **Maniobra:** `List(Uno(1))`
- **Estado final esperado:** `(List(1,2), List(3), List())`

### Prueba 3: Movimiento Dos(2)
- **Estado inicial:** `(List(1,2,3,4), List(), List())`
- **Maniobra:** `List(Dos(2))`
- **Estado final esperado:** `(List(1,2), List(), List(3,4))`

### Prueba 4: Secuencia reversible
- **Estado inicial:** `(List(1,2,3,4), List(), List())`
- **Maniobra:** `List(Uno(2), Uno(-2))`
- **Estado final esperado:** Igual al estado inicial

### Prueba 5: Paso a paso combinado
- **Estado inicial:** `(List(1,2,3,4), List(), List())`
- **Maniobra:** `List(Uno(2), Dos(1))`
- **Estado final esperado:** `(List(1), List(3,4), List(2))`

## 5. Resultados de las Pruebas Ejecutadas

### Clase: `taller.testAplicarMovimientos`

- **Total de pruebas:** 20
- **Fallos:** 0
- **Ignoradas:** 0
- **Duración total:** 0.435 s
- **Resultado general:** ✅ 100% exitoso

| Nombre de la prueba                               | Categoría | Duración | Resultado |
|---------------------------------------------------|-----------|----------|-----------|
| Juguete 1: sin movimientos                        | Juguete   | 0.001s   | ✅ passed |
| Juguete 2: un solo movimiento Uno(1)              | Juguete   | 0.001s   | ✅ passed |
| Juguete 3: un solo movimiento Dos(2)              | Juguete   | 0.001s   | ✅ passed |
| Juguete 4: secuencia reversible Uno y Uno(-)      | Juguete   | 0.001s   | ✅ passed |
| Juguete 5: paso a paso válido                     | Juguete   | 0.002s   | ✅ passed |
| Pequeña 1: mover todo a t2                        | Pequeña   | 0.002s   | ✅ passed |
| Pequeña 2: ida y vuelta con t3                    | Pequeña   | 0.002s   | ✅ passed |
| Pequeña 3: alternancia Uno y Dos                  | Pequeña   | 0.001s   | ✅ passed |
| Pequeña 4: movimientos inválidos                  | Pequeña   | 0.001s   | ✅ passed |
| Pequeña 5: mezcla con devoluciones                | Pequeña   | 0.001s   | ✅ passed |
| Mediana 1: 100 vagones ida y vuelta               | Mediana   | 0.004s   | ✅ passed |
| Mediana 2: dividir entre t2 y t3                  | Mediana   | 0.003s   | ✅ passed |
| Mediana 3: mezcla de movimientos                  | Mediana   | 0.004s   | ✅ passed |
| Mediana 4: devolver más de lo enviado             | Mediana   | 0.002s   | ✅ passed |
| Mediana 5: alternancia Uno y Dos                  | Mediana   | 0.002s   | ✅ passed |
| Grande 1: 1000 vagones en bloques a t2            | Grande    | 0.008s   | ✅ passed |
| Grande 2: 500 a t2, luego 500 a t3                | Grande    | 0.010s   | ✅ passed |
| Grande 3: mezcla de maniobras con 1000 vagones    | Grande    | 0.007s   | ✅ passed |
| Grande 4: alternancia Uno y Dos en 10 bloques     | Grande    | 0.009s   | ✅ passed |
| Grande 5: ida y vuelta múltiples a t2 y t3        | Grande    | 0.007s   | ✅ passed |

## 6. Conclusiones

La función `aplicarMovimientos` fue validada exitosamente mediante una serie de pruebas exhaustivas que cubren escenarios desde los más básicos hasta situaciones complejas con gran cantidad de datos. La implementación muestra un comportamiento predecible y estable, y logra mantener la integridad de los datos a través de múltiples transformaciones.

Además, se confirma que la secuencia de estados generada permite trazabilidad total del proceso, lo cual es valioso para depuración y verificación.

## 7. Mejoras Futuras

- Añadir soporte para cancelación o deshacer movimientos.
- Implementar validaciones para evitar maniobras sin efecto (por ejemplo, mover 0 vagones).
- Paralelizar pruebas con gran cantidad de datos para reducir tiempos de ejecución.
- Extender el sistema para soportar más de tres trenes o reglas personalizadas.
