package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class testAplicarMovimiento extends AnyFunSuite {

  val obj = new maniobraTrenes()

  /** ----------------------
   * Pruebas de Juguete (5)
   * ---------------------- */
  test("Juguete 1: movimientos simples con 10 vagones") {
    val e1 = (List.range(1, 11), List(), List())
    val e2 = obj.aplicarMovimiento(e1, Uno(3))
    assert(e2 == (List(1,2,3,4,5,6,7), List(8,9,10), List()))
  }

  test("Juguete 2: mover de t1 a t3") {
    val e1 = (List.range(1, 11), List(), List())
    val e2 = obj.aplicarMovimiento(e1, Dos(2))
    assert(e2 == (List(1,2,3,4,5,6,7,8), List(), List(9,10)))
  }

  test("Juguete 3: devolver de t2 a t1") {
    val e1 = (List(1,2,3), List(4,5), List())
    val e2 = obj.aplicarMovimiento(e1, Uno(-1))
    assert(e2 == (List(1,2,3,4), List(5), List()))
  }

  test("Juguete 4: devolver de t3 a t1") {
    val e1 = (List(1,2), List(), List(3,4,5))
    val e2 = obj.aplicarMovimiento(e1, Dos(-2))
    assert(e2 == (List(1,2,3,4), List(), List(5)))
  }

  test("Juguete 5: combinación de varios movimientos") {
    val e1 = (List.range(1, 11), List(), List())
    val movs = List(Uno(3), Dos(2), Dos(-1), Uno(-2), Uno(1), Dos(1), Dos(-2), Uno(1), Uno(-1), Dos(1))
    val estados = obj.aplicarMovimientos(e1, movs)
    val (a, b, c) = estados.last
    assert(a.size + b.size + c.size == 10)
  }

  /** ----------------------
   * Pruebas Pequeñas (5)
   * ---------------------- */
  test("Pequeña 1: 100 vagones, alternancia Uno y Dos") {
    val e1 = (List.range(1, 101), List(), List())
    val movs = List.tabulate(10)(i => if (i % 2 == 0) Uno(5) else Dos(5))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.length == 11)
    assert(estados.last._1.size + estados.last._2.size + estados.last._3.size == 100)
  }

  test("Pequeña 2: mover todo a t2 en bloques de 10") {
    val e1 = (List.range(1, 101), List(), List())
    val movs = List.fill(10)(Uno(10))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.isEmpty)
    assert(estados.last._2.size == 100)
  }

  test("Pequeña 3: ir de t1 a t3 completamente") {
    val e1 = (List.range(1, 101), List(), List())
    val movs = List.fill(10)(Dos(10))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._3.size == 100)
  }

  test("Pequeña 4: enviar y traer vagones entre t1 y t2") {
    val e1 = (List.range(1, 101), List(), List())
    val movs = List(Uno(10), Uno(-5), Uno(5))
    val estados = obj.aplicarMovimientos(e1, movs)
    val (a, b, c) = estados.last
    assert(a.size + b.size + c.size == 100)
  }

  test("Pequeña 5: traer desde t3 a t1 después de enviar") {
    val e1 = (List.range(1, 101), List(), List())
    val movs = List(Dos(10), Dos(-5))
    val estados = obj.aplicarMovimientos(e1, movs)
    val (a, b, c) = estados.last
    assert(a.size == 95 && c.size == 5)
  }

  /** ----------------------
   * Pruebas Medianas (5)
   * ---------------------- */
  test("Mediana 1: 500 vagones, 10 Uno(25), luego 10 Dos(25)") {
    val e1 = (List.range(1, 501), List(), List())
    val movs = List.fill(10)(Uno(25)) ++ List.fill(10)(Dos(25))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size + estados.last._2.size + estados.last._3.size == 500)
  }

  test("Mediana 2: ir a t2 y devolver desde t2") {
    val e1 = (List.range(1, 501), List(), List())
    val movs = List(Uno(100), Uno(-50))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size == 450)
  }

  test("Mediana 3: combinaciones Uno y Dos") {
    val e1 = (List.range(1, 501), List(), List())
    val movs = List(Uno(100), Dos(50), Uno(-50), Dos(-25))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size + estados.last._2.size + estados.last._3.size == 500)
  }

  test("Mediana 4: 10 movimientos alternos negativos") {
    val e1 = (List.range(1, 501), List.fill(100)("x"), List.fill(50)("y"))
    val movs = List.fill(5)(Uno(-10)) ++ List.fill(5)(Dos(-5))
    val estados = obj.aplicarMovimientos(e1, movs)
    val (a, b, c) = estados.last
    assert(a.size + b.size + c.size == 650)
  }

  test("Mediana 5: solo movimientos hacia t2") {
    val e1 = (List.range(1, 501), List(), List())
    val movs = List.fill(5)(Uno(50))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._2.size == 250)
  }

  /** ----------------------
   * Pruebas Grandes (5)
   * ---------------------- */
  test("Grande 1: 1000 vagones, alternancia cíclica") {
    val e1 = (List.range(1, 1001), List(), List())
    val movs = (1 to 10).flatMap(_ => List(Uno(50), Dos(50), Uno(-25), Dos(-25))).toList
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size + estados.last._2.size + estados.last._3.size == 1000)
  }

  test("Grande 2: mover todo a t3") {
    val e1 = (List.range(1, 1001), List(), List())
    val movs = List.fill(20)(Dos(50))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._3.size == 1000)
  }

  test("Grande 3: llevar a t2 y devolver todo") {
    val e1 = (List.range(1, 1001), List(), List())
    val movs = List.fill(10)(Uno(100)) ++ List.fill(10)(Uno(-100))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size == 1000)
  }

  test("Grande 4: mezcla completa de movimientos") {
    val e1 = (List.range(1, 1001), List(), List())
    val movs = List.fill(5)(Uno(30)) ++ List.fill(5)(Dos(30)) ++ List.fill(5)(Uno(-15)) ++ List.fill(5)(Dos(-15))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size + estados.last._2.size + estados.last._3.size == 1000)
  }

  test("Grande 5: 100 movimientos aleatorios de tamaño 10") {
    val e1 = (List.range(1, 1001), List(), List())
    val movs = List.fill(50)(Uno(10)) ++ List.fill(50)(Dos(10))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size + estados.last._2.size + estados.last._3.size == 1000)
  }

  /** ----------------------
   * Pruebas Extra / Personalizadas
   * ---------------------- */

  test("Extra: 20 vagones, prueba sencilla") {
    val e1 = (List.range(1, 21), List(), List())
    val movs = List(Uno(10), Uno(-5))
    val estados = obj.aplicarMovimientos(e1, movs)
    val (a, b, c) = estados.last
    assert((a ++ b ++ c).size == 20)
  }

  test("Personalizada: caracteres") {
    val e1 = (List('a', 'b', 'c', 'd'), List(), List())
    val e2 = obj.aplicarMovimiento(e1, Uno(2))
    assert(e2 == (List('a', 'b'), List('c', 'd'), List()))
    val e3 = obj.aplicarMovimiento(e2, Dos(1))
    assert(e3 == (List('a'), List('c', 'd'), List('b')))
    val e4 = obj.aplicarMovimiento(e3, Uno(-2))
    assert(e4 == (List('a', 'c', 'd'), List(), List('b')))
  }

  test("Mediana: 1000 vagones en 50 movimientos") {
    val e1 = (List.range(1, 1001), List(), List())
    val movs = List.fill(25)(Uno(20)) ++ List.fill(25)(Uno(-20))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size == 1000)
  }

  test("Grande: 10000 vagones, dividir y reunir") {
    val e1 = (List.range(1, 10001), List(), List())
    val movs = List.fill(100)(Uno(50)) ++ List.fill(100)(Uno(-50))
    val estados = obj.aplicarMovimientos(e1, movs)
    assert(estados.last._1.size == 10000)
  }

  test("Gigante: 50000 vagones, alternancia t2 y t3") {
    val e1 = (List.range(1, 50001), List(), List())
    val movs = List.tabulate(500)(i => if (i % 2 == 0) Uno(100) else Dos(100))
    val estados = obj.aplicarMovimientos(e1, movs)
    val (a, b, c) = estados.last
    assert(a.size + b.size + c.size == 50000)
  }
}

