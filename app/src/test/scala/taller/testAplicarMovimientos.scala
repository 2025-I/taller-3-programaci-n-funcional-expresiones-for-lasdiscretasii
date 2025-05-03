package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class testAplicarMovimientos extends AnyFunSuite {

  val obj = new maniobraTrenes()

  /** ----------------------
   * Pruebas de Juguete (5)
   * ---------------------- */
  test("Juguete 1: sin movimientos") {
    val e = (List(1,2,3), List(), List())
    val res = obj.aplicarMovimientos(e, List())
    assert(res == List(e))
  }

  test("Juguete 2: un solo movimiento Uno(1)") {
    val e = (List(1,2,3), List(), List())
    val res = obj.aplicarMovimientos(e, List(Uno(1)))
    assert(res.last == (List(1,2), List(3), List()))
  }

  test("Juguete 3: un solo movimiento Dos(2)") {
    val e = (List(1,2,3,4), List(), List())
    val res = obj.aplicarMovimientos(e, List(Dos(2)))
    assert(res.last == (List(1,2), List(), List(3,4)))
  }

  test("Juguete 4: secuencia reversible Uno(2) y Uno(-2)") {
    val e = (List(1,2,3,4), List(), List())
    val res = obj.aplicarMovimientos(e, List(Uno(2), Uno(-2)))
    assert(res.last == e)
  }

  test("Juguete 5: paso a paso válido") {
    val e = (List(1,2,3,4), List(), List())
    val movs = List(Uno(2), Dos(1))
    val estados = obj.aplicarMovimientos(e, movs)
    assert(estados(1) == (List(1,2), List(3,4), List()))
    assert(estados(2) == (List(1), List(3,4), List(2)))
  }

  /** ----------------------
   * Pruebas Pequeñas (5)
   * ---------------------- */
  test("Pequeña 1: mover todo a t2 en 2 pasos") {
    val e = (List.range(1, 11), List(), List())
    val res = obj.aplicarMovimientos(e, List(Uno(5), Uno(5)))
    assert(res.last == (List(), List.range(1,11), List()))
  }

  test("Pequeña 2: mover a t3 y traer de vuelta") {
    val e = (List.range(1, 11), List(), List())
    val res = obj.aplicarMovimientos(e, List(Dos(5), Dos(-5)))
    assert(res.last == e)
  }

  test("Pequeña 3: movimientos alternos Uno y Dos") {
    val e = (List.range(1, 11), List(), List())
    val movs = List(Uno(3), Dos(2))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._3 == List(6,7))
  }

  test("Pequeña 4: movimientos inválidos no cambian estado") {
    val e = (List(1,2), List(), List())
    val res = obj.aplicarMovimientos(e, List(Uno(10), Dos(10)))
    assert(res.last._1.isEmpty)
  }

  test("Pequeña 5: mezcla con devoluciones") {
    val e = (List.range(1, 6), List(), List())
    val res = obj.aplicarMovimientos(e, List(Uno(2), Uno(-1), Dos(1)))
    assert(res.last == (List(1,2,3), List(5), List(4)))
  }

  /** ----------------------
   * Pruebas Medianas (5)
   * ---------------------- */
  test("Mediana 1: 100 vagones a t2 y regreso a t1") {
    val e = (List.range(1, 101), List(), List())
    val movs = List.fill(5)(Uno(20)) ++ List.fill(5)(Uno(-20))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last == e)
  }

  test("Mediana 2: dividir vagones entre t2 y t3") {
    val e = (List.range(1, 101), List(), List())
    val movs = List(Uno(40), Dos(30))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._2.size == 40 && res.last._3.size == 30)
  }

  test("Mediana 3: mezcla de movimientos y total correcto") {
    val e = (List.range(1, 101), List(), List())
    val movs = List(Uno(25), Dos(25), Uno(-10), Dos(-5))
    val res = obj.aplicarMovimientos(e, movs)
    val (a,b,c) = res.last
    assert((a ++ b ++ c).map(_.asInstanceOf[Int]).sorted == List.range(1,101))
  }

  test("Mediana 4: devolver más de lo enviado no rompe") {
    val e = (List.range(1, 51), List(51,52), List(53))
    val movs = List(Uno(-5), Dos(-5))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._1 == List.range(1,54))
    assert((res.last._2.isEmpty))
    assert((res.last._3.isEmpty))
  }

  test("Mediana 5: alternar Uno y Dos varias veces") {
    val e = (List.range(1, 101), List(), List())
    val movs = List.fill(5)(List(Uno(10), Dos(10))).flatten
    val res = obj.aplicarMovimientos(e, movs)
    val (a,b,c) = res.last
    assert((a ++ b ++ c).map(_.asInstanceOf[Int]).sorted == List.range(1, 101))
  }

  /** ----------------------
   * Pruebas Grandes (5)
   * ---------------------- */
  test("Grande 1: 1000 vagones en bloques") {
    val e = (List.range(1, 1001), List(), List())
    val movs = List.fill(10)(Uno(100))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._2.size == 1000)
  }

  test("Grande 2: 500 a t2, luego 500 a t3") {
    val e = (List.range(1, 1001), List(), List())
    val movs = List.fill(5)(Uno(100)) ++ List.fill(5)(Dos(100))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._2.size == 500 && res.last._3.size == 500)
  }

  test("Grande 3: devolver desde t3 hasta t1") {
    val e = (List(), List(), List.range(1, 1001))
    val movs = List.fill(10)(Dos(-100))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._1.size == 1000)
  }

  test("Grande 4: movimientos reversibles en gran escala") {
    val e = (List.range(1, 1001), List(), List())
    val movs = List.fill(10)(Uno(50)) ++ List.fill(10)(Uno(-50))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last == e)
  }

  test("Grande 5: mezcla total Uno y Dos") {
    val e = (List.range(1, 1001), List(), List())
    val movs = List.fill(5)(List(Uno(50), Dos(50), Uno(-25), Dos(-25))).flatten
    val res = obj.aplicarMovimientos(e, movs)
    val (a,b,c) = res.last
    assert((a ++ b ++ c).map(_.asInstanceOf[Int]).sorted == List.range(1, 1001))
  }

  /** ----------------------
   * Pruebas Extras / Personalizadas
   * ---------------------- */

  test("Extra: 20 elementos, ida y vuelta") {
    val e = (List.range(1, 21), List(), List())
    val movs = List.fill(2)(Uno(10)) ++ List.fill(2)(Uno(-10))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._1.size == 20)
  }

  test("Personalizada: caracteres") {
    val e = (List('a', 'b', 'c', 'd'), List(), List())
    val movs = List(Uno(2), Dos(1), Uno(-2))
    val res = obj.aplicarMovimientos(e, movs)
    val esperado = List(
      (List('a', 'b', 'c', 'd'), List(), List()),
      (List('a', 'b'), List('c', 'd'), List()),
      (List('a'), List('c', 'd'), List('b')),
      (List('a', 'c', 'd'), List(), List('b'))
    )
    assert(res == esperado)
  }

  test("Mediana: 3000 elementos en 60 movimientos") {
    val e = (List.range(1, 3001), List(), List())
    val movs = List.fill(30)(Uno(50)) ++ List.fill(30)(Uno(-50))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._1.size == 3000)
  }

  test("Grande: 10000 vagones alternando") {
    val e = (List.range(1, 10001), List(), List())
    val movs = List.tabulate(200)(i => if (i % 2 == 0) Uno(25) else Dos(25))
    val res = obj.aplicarMovimientos(e, movs)
    val (a, b, c) = res.last
    assert((a ++ b ++ c).map(_.asInstanceOf[Int]).sorted == List.range(1, 10001))
  }

  test("Gigante: 50000 vagones, reversibilidad") {
    val e = (List.range(1, 50001), List(), List())
    val movs = List.fill(100)(Uno(250)) ++ List.fill(100)(Uno(-250))
    val res = obj.aplicarMovimientos(e, movs)
    assert(res.last._1.size == 50000)
  }

}

