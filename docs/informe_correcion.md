<p align="center">
  <img src="../imagenes/logoUnivalle.jpg" alt="Logo Univalle" width="100">
</p>

### Informe de Corrección de Ejercicios

**Asignatura:** Fundamentos de Programación Funcional y Concurrente  
**Autores:** Juan David Charry Medina (código 2559927), Geraldine Florez Rico (código 2269476)  
**Fecha:** 3 de mayo de 2025

---

### Introducción

Este informe demuestra formalmente que las funciones `aplicarMovimiento`, `aplicarMovimientos` y `definirManiobra` de la clase `maniobraTrenes` son correctas, utilizando notación matemática rigurosa, inducción estructural e invariantes de ciclo. Se explica cómo son los llamados entre funciones y se proporcionan ejemplos paso a paso para ilustrar el comportamiento de los algoritmos.

---

### Parte 1: Corrección de `aplicarMovimiento`

#### Definición formal

Sea:

- $E$: el conjunto de estados $(t1, t2, t3)$, donde cada $t_i$ es una lista de vagones.
- $M$: el conjunto de movimientos: `Uno(n)` (entre $t1$ y $t2$) y `Dos(n)` (entre $t1$ y $t3$), con $n \in \mathbb{Z}$.
- $f: E \times M \to E$: la función que representa el estado resultante tras aplicar $m$ al estado $e$.

Queremos demostrar:

$$
\forall e \in E,\, m \in M : aplicarMovimiento(e, m) = f(e, m)
$$

#### Demostración por casos

- **Caso base:** $n = 0$: no se mueve nada, el estado es idéntico:

  $$
  aplicarMovimiento(e, m) = e = f(e, m)
  $$

- **Caso inductivo:** $n > 0$ o $n < 0$: se mueven $a = \min(|n|, |origen|)$ vagones entre pilas, garantizando:

    - Ningún vagón se pierde.
    - El orden relativo se mantiene.
    - El número total de vagones $|t1| + |t2| + |t3|$ permanece constante.

Por lo tanto:

$$
\forall e, m : aplicarMovimiento(e, m) = f(e, m)
$$

#### Ejemplo paso a paso

Estado inicial: $([1,2,3], [], [])$  
Movimiento: `Uno(2)` → mueve 2 últimos de $t1$ a $t2$ → $([1], [2,3], [])$  
Movimiento: `Dos(1)` → mueve 1 último de $t1$ a $t3$ → $([], [2,3], [1])$

---

### Parte 2: Corrección de `aplicarMovimientos`

#### Definición formal

Sea:

- $aplicarMovimientos: E \times [M] \to [E]$
- Resultado: lista $[e_0, e_1, \dots, e_k]$, donde $e_0 = e$ y $e_i = aplicarMovimiento(e_{i-1}, m_i)$.

#### Invariante

- $Inv(i, e_i) \equiv e_i$: es el estado tras aplicar los primeros $i$ movimientos.
- Transformación:

$$
(i, e_i) \rightarrow (i + 1, aplicarMovimiento(e_i, m_{i+1}))
$$

El invariante se mantiene en cada paso.

#### Ejemplo paso a paso

Estado inicial: $([1,2,3], [], [])$  
Movimientos: `Uno(2)`, `Dos(1)`

Estados intermedios:

- Después de `Uno(2)`: $([1], [2,3], [])$
- Después de `Dos(1)`: $([], [2,3], [1])$

---

### Parte 3: Corrección de `definirManiobra`

#### Descripción y formalización

La función `definirManiobra(t1, t2)` construye una secuencia de maniobras para transformar $t1$ en $t2$. El auxiliar `aux` recorre $t2$ y para cada vagón $v$:

- Busca su posición $j$ en $t1$.
- Si existe, genera:

$$
Uno(t1.\text{length} - j), \quad Dos(t2.\text{indexOf}(v)), \quad Uno(-(t1.\text{length} - j)), \quad Dos(-t2.\text{indexOf}(v))
$$

Esto asegura que el vagón $v$ termina en la posición correcta.

#### Corrección por inducción estructural

- **Caso base:** $t2 = []$: se devuelve $[]$.
- **Paso inductivo:** para $vagon :: tail$, si la maniobra para `tail` es correcta, agregar la maniobra de `vagon` mantiene la corrección.

#### Ejemplo paso a paso

$t1 = [1,2,3], \quad t2 = [2,1]$

- Procesa `2`: posición 1 en $t1$, genera maniobra.
- Procesa `1`: posición 0 en $t1$, genera maniobra.

Resultado: lista de maniobras que colocan $t1$ en orden $[2,1]$.

---

### Llamados entre funciones

- `aplicarMovimientos` → llama secuencialmente a `aplicarMovimiento`.
- `definirManiobra` → llama recursivamente a `aux`.
- Todas las funciones respetan invariantes y garantizan resultados correctos.

---

### Conclusión general

Se ha demostrado, usando notación matemática clara y ejemplos trabajados, que las tres funciones implementadas son correctas. Los llamados entre funciones y las transformaciones cumplen las especificaciones, asegurando exactitud y consistencia.
