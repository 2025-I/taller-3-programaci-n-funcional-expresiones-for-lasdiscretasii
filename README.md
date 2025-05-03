# Taller: 3 - Fundamentos de programacón funcional y concurrente: Expresiones for


Este proyecto resuelve un problema clásico de reorganización de trenes en una estación de maniobras, utilizando programación funcional en Scala, expresiones `for` y funciones recursivas.

## Objetivo

Dado un tren de entrada (`t1`) y un tren deseado (`t2`), se debe definir una **maniobra**: una secuencia de movimientos que, usando vías auxiliares, transforma `t1` en `t2`.

## Modelo de la Estación

La estación tiene tres vías:

- **Principal**: vía donde inicia y debe terminar el tren.
- **Uno**: vía auxiliar 1.
- **Dos**: vía auxiliar 2.

El **estado** se representa como una tripla:

```scala
type Vagon = Any
type Tren = List[Vagon]
type Estado = (Tren, Tren, Tren) // (principal, uno, dos)
```

Un **movimiento** es una operación que traslada n vagones de una vía a otra:

```scala
trait Movimiento
case class Uno(n: Int) extends Movimiento
case class Dos(n: Int) extends Movimiento
```

### Reglas

- `Uno(n)` con `n > 0`: mueve `n` vagones del final de `principal` al inicio de `uno`.
- `Uno(n)` con `n < 0`: mueve `n` vagones del inicio de `uno` al final de `principal`.
- `Dos(n)`: opera de forma análoga, pero con la vía `dos`.

---

## Requisitos

Implementa las siguientes funciones en Scala:

### 1. `aplicarMovimiento`

Aplica un solo movimiento a un estado:

```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado
```

Ejemplo:

```scala
val e1 = (List('a', 'b', 'c', 'd'), Nil, Nil)
val e2 = aplicarMovimiento(e1, Uno(2))
// Resultado: (List('a', 'b'), List('c', 'd'), Nil)

val e3 = aplicarMovimiento(e2, Dos(3))
// Resultado: (Nil, List('c', 'd'), List('a', 'b'))
```

---

### 2. `aplicarMovimientos`

Aplica una lista de movimientos a un estado inicial:

```scala
type Maniobra = List[Movimiento]

def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado]
```

Ejemplo:

```scala
val e = (List('a', 'b'), List('c'), List('d'))
val movs = List(Uno(1), Dos(1), Uno(-2))

aplicarMovimientos(e, movs)
// Resultado:
// List(
//   (List('a', 'b'), List('c'), List('d')),
//   (List('a'), List('b', 'c'), List('d')),
//   (Nil, List('b', 'c'), List('a', 'd')),
//   (List('b', 'c'), Nil, List('a', 'd'))
// )
```

---

### 3. `definirManiobra`

Define una maniobra para transformar un tren inicial en uno deseado:

```scala
def definirManiobra(t1: Tren, t2: Tren): Maniobra
```

Ejemplo:

```scala
definirManiobra(List('a', 'b', 'c', 'd'), List('d', 'b', 'c', 'a'))
// Resultado (una posible solución):
// List(Uno(4), Uno(-3), Dos(3), Uno(-1), Dos(-1), Uno(1), Dos(-1), Dos(-1), Uno(-1))
```

⚠️ *Puede haber múltiples soluciones válidas. Tu implementación debe generar una que funcione correctamente.*

---

## Clonar el Repositorio

```bash
git clone git@github.com:2025-I/semestre-2025-i-taller-3-programacion-funcional-expresiones-for-plantilla-funcional.git
cd maniobras-trenes
```

Reemplaza `usuario` con tu nombre de usuario en GitHub si es un repositorio personal.

---

## Recomendaciones

- Asegúrate de que no haya vagones repetidos en un mismo tren.
- Usa anotaciones `@tailrec` en funciones recursivas para optimización.
- Puedes probar la función `definirManiobra` con diferentes combinaciones de `t1` y `t2`.

---

## Créditos

Taller 3 - Fundamentos de programación funcional y concurrente: Expresiones For
Universidad del Valle – 2025-I


### Integrantes

- **Juan David Charry Medina** – 2559927
- **Geraldine Florez Rico** – 2269476