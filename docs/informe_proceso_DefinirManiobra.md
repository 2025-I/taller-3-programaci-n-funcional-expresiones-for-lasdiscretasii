# Informe de Proceso de `definirManiobra`

## 1. Introducción

La función `definirManiobra` tiene como objetivo generar una secuencia de maniobras necesarias para trasladar los vagones de un tren `t1` a otro tren `t2`, respetando el orden de aparición de los vagones en `t1`. Las maniobras se generan de acuerdo con las coincidencias de vagones entre `t1` y `t2`, y las operaciones generadas son de tipo `Uno` y `Dos`, que definen el movimiento de los vagones.

## 2. Descripción del Proceso de Ejecución

La función `definirManiobra` sigue una serie de pasos para determinar las maniobras necesarias:

1. **Recorrido de los Vagones en `t2`:** La función recorre cada vagón de `t2`, y para cada vagón, busca su índice correspondiente en `t1`.

2. **Generación de Movimientos:**

    * Si un vagón se encuentra en `t1`, se genera una secuencia de movimientos:

        * **Movimiento Uno (n > 0):** Mueve los vagones desde `t1` hasta `t2`.
        * **Movimiento Dos (n > 0):** Mueve los vagones desde `t1` hasta `t3` (suponiendo que se necesitan más trenes).
    * Cada maniobra considera tanto el número de vagones a mover como la posición de los mismos en los trenes.

3. **Resultado:** Se devuelve una lista de las maniobras necesarias para alinear los vagones en `t2` según el orden en `t1`.

## 3. Ejemplo de Proceso

### Ejemplo 1: Definición de Maniobra Básica

**Estado inicial:**

```scala
t1 = List("Vagón1", "Vagón2", "Vagón3", "Vagón4", "Vagón5")
t2 = List("Vagón2", "Vagón4")
```

**Maniobras generadas:**

```scala
List(
  Uno(3),   // Mover 3 vagones desde t1 a t2
  Dos(1),   // Mover vagón desde t2 a t3
  Uno(-3),  // Regresar los vagones a t1
  Dos(-1)   // Regresar el vagón de t3 a t2
)
```

### Ejemplo 2: Definición de Maniobra con Más Vagones

**Estado inicial:**

```scala
t1 = List("Vagón1", "Vagón2", "Vagón3", "Vagón4", "Vagón5", "Vagón6")
t2 = List("Vagón2", "Vagón4", "Vagón6")
```

**Maniobras generadas:**

```scala
List(
  Uno(2),   // Mover 2 vagones desde t1 a t2
  Dos(2),   // Mover vagones desde t1 a t3
  Uno(-2),  // Regresar los vagones a t1
  Dos(-2)   // Regresar los vagones de t3 a t2
)
```

## 4. Pruebas de Software

Las pruebas fueron diseñadas para verificar el comportamiento correcto de la función `definirManiobra` bajo diferentes condiciones, tanto para listas pequeñas como grandes. A continuación se detallan las pruebas realizadas.

### Pruebas de Juguete

* **Prueba 1: Tamaño 10 con movimientos pequeños:**

    * **t1:** 10 vagones
    * **t2:** 5 vagones que coinciden con `t1`
    * **Resultado esperado:** Maniobras generadas para mover los vagones correspondientes de `t1` a `t2`.

* **Prueba 2: Tamaño 10 con movimientos aleatorios:**

    * **t1:** 10 vagones
    * **t2:** 10 vagones sin coincidencias
    * **Resultado esperado:** No se generan maniobras ya que no hay coincidencias.

### Pruebas Pequeñas

* **Prueba 3: Tamaño 100 con coincidencias en vagones:**

    * **t1:** 100 vagones
    * **t2:** 50 vagones coincidentes
    * **Resultado esperado:** Generación de maniobras para mover los vagones que coinciden.

* **Prueba 4: Tamaño 100 con vagones no coincidentes:**

    * **t1:** 100 vagones
    * **t2:** 100 vagones diferentes
    * **Resultado esperado:** No se generan maniobras ya que no hay coincidencias.

### Pruebas Medianas

* **Prueba 5: Tamaño 500 con coincidencias distribuidas:**

    * **t1:** 500 vagones
    * **t2:** 250 vagones coincidentes
    * **Resultado esperado:** Maniobras generadas para mover los vagones correspondientes.

* **Prueba 6: Tamaño 500 con pocas coincidencias:**

    * **t1:** 500 vagones
    * **t2:** 10 vagones coincidentes
    * **Resultado esperado:** Maniobras generadas solo para los 10 vagones coincidentes.

### Pruebas Grandes

* **Prueba 7: Tamaño 1000 con coincidencias parciales:**

    * **t1:** 1000 vagones
    * **t2:** 500 vagones coincidentes
    * **Resultado esperado:** Maniobras generadas para mover los vagones coincidentes.

* **Prueba 8: Tamaño 1000 con ninguna coincidencia:**

    * **t1:** 1000 vagones
    * **t2:** 500 vagones sin coincidencias
    * **Resultado esperado:** No se generan maniobras.

## 5. Resultados de las Pruebas Ejecutadas

### Clase: `taller.DefinirManiobraTest`

* **Total de pruebas:** 20
* **Fallos:** 0
* **Ignoradas:** 0
* **Duración total:** 0.153s
* **Resultado general:** ✅ 100% exitoso

| **Prueba** | **Duración** | **Resultado** |
|------------|--------------|---------------|
| **Prueba 1:** Tamaño 10 - t1 con 10 vagones y t2 vacío | 0.048s | ✅ passed |
| **Prueba 2:** Tamaño 10 - t1 con 10 vagones y t2 con 10 vagones, todos coinciden | 0.006s | ✅ passed |
| **Prueba 3:** Tamaño 10 - t1 con 10 vagones y t2 con 5 vagones coincidentes | 0.002s | ✅ passed |
| **Prueba 4:** Tamaño 10 - t1 con 10 vagones y t2 con 5 vagones no coincidentes | 0.003s | ✅ passed |
| **Prueba 5:** Tamaño 10 - t1 con 10 vagones y t2 con 10 vagones, algunos coinciden y otros no | 0.002s | ✅ passed |
| **Prueba 6:** Tamaño 100 - t1 con 100 vagones y t2 vacío | 0.002s | ✅ passed |
| **Prueba 7:** Tamaño 100 - t1 con 100 vagones y t2 con 100 vagones, todos coinciden | 0.007s | ✅ passed |
| **Prueba 8:** Tamaño 100 - t1 con 100 vagones y t2 con 50 vagones coincidentes | 0.004s | ✅ passed |
| **Prueba 9:** Tamaño 100 - t1 con 100 vagones y t2 con 50 vagones no coincidentes | 0.003s | ✅ passed |
| **Prueba 10:** Tamaño 100 - t1 con 100 vagones y t2 con 100 vagones, algunos coinciden | 0.004s | ✅ passed |
| **Prueba 11:** Tamaño 500 - t1 con 500 vagones y t2 vacío | 0.004s | ✅ passed |
| **Prueba 12:** Tamaño 500 - t1 con 500 vagones y t2 con 500 vagones, todos coinciden | 0.011s | ✅ passed |
| **Prueba 13:** Tamaño 500 - t1 con 500 vagones y t2 con 250 vagones coincidentes | 0.005s | ✅ passed |
| **Prueba 14:** Tamaño 500 - t1 con 500 vagones y t2 con 250 vagones no coincidentes | 0.003s | ✅ passed |
| **Prueba 15:** Tamaño 500 - t1 con 500 vagones y t2 con 500 vagones, algunos coinciden | 0.005s | ✅ passed |
| **Prueba 16:** Tamaño 1000 - t1 con 1000 vagones y t2 vacío | 0.005s | ✅ passed |
| **Prueba 17:** Tamaño 1000 - t1 con 1000 vagones y t2 con 1000 vagones, todos coinciden | 0.016s | ✅ passed |
| **Prueba 18:** Tamaño 1000 - t1 con 1000 vagones y t2 con 500 vagones coincidentes | 0.007s | ✅ passed |
| **Prueba 19:** Tamaño 1000 - t1 con 1000 vagones y t2 con 500 vagones no coincidentes | 0.007s | ✅ passed |
| **Prueba 20:** Tamaño 1000 - t1 con 1000 vagones y t2 con 1000 vagones, algunos coinciden | 0.009s | ✅ passed |

---

## 6. Conclusiones

Las pruebas fueron exitosas, y la función `definirManiobra` se comportó como se esperaba en todos los casos. Las pruebas cubrieron una amplia gama de escenarios, desde listas pequeñas hasta listas de gran tamaño, asegurando tanto la precisión como la eficiencia del sistema.

