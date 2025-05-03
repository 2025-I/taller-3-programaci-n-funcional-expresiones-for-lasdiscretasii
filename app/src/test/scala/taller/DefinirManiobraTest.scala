package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DefinirManiobraTest extends AnyFunSuite {

  val maniobraTrenes = new maniobraTrenes

  /** ----------------------
   * Pruebas de Juguete (5)
   * ---------------------- */

  test("Prueba 1: Tamaño 10 - t1 con 10 vagones y t2 vacío") {
    val t1 = (1 to 10).map(i => s"Vagón$i").toList
    val t2 = List()
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 2: Tamaño 10 - t1 con 10 vagones y t2 con 10 vagones, todos coinciden") {
    val t1 = (1 to 10).map(i => s"Vagón$i").toList
    val t2 = (1 to 10).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 3: Tamaño 10 - t1 con 10 vagones y t2 con 5 vagones coincidentes") {
    val t1 = (1 to 10).map(i => s"Vagón$i").toList
    val t2 = (1 to 5).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 4: Tamaño 10 - t1 con 10 vagones y t2 con 5 vagones no coincidentes") {
    val t1 = (1 to 10).map(i => s"Vagón$i").toList
    val t2 = (11 to 15).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 5: Tamaño 10 - t1 con 10 vagones y t2 con 10 vagones, algunos coinciden y otros no") {
    val t1 = (1 to 10).map(i => s"Vagón$i").toList
    val t2 = (5 to 15).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }


  /** ----------------------
   * Pruebas de pequeñas (5)
   * ---------------------- */
  test("Prueba 6: Tamaño 100 - t1 con 100 vagones y t2 vacío") {
    val t1 = (1 to 100).map(i => s"Vagón$i").toList
    val t2 = List()
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 7: Tamaño 100 - t1 con 100 vagones y t2 con 100 vagones, todos coinciden") {
    val t1 = (1 to 100).map(i => s"Vagón$i").toList
    val t2 = (1 to 100).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 8: Tamaño 100 - t1 con 100 vagones y t2 con 50 vagones coincidentes") {
    val t1 = (1 to 100).map(i => s"Vagón$i").toList
    val t2 = (1 to 50).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 9: Tamaño 100 - t1 con 100 vagones y t2 con 50 vagones no coincidentes") {
    val t1 = (1 to 100).map(i => s"Vagón$i").toList
    val t2 = (101 to 150).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 10: Tamaño 100 - t1 con 100 vagones y t2 con 100 vagones, algunos coinciden") {
    val t1 = (1 to 100).map(i => s"Vagón$i").toList
    val t2 = (50 to 150).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  /** ----------------------
   * Pruebas de mediana (5)
   * ---------------------- */
  test("Prueba 11: Tamaño 500 - t1 con 500 vagones y t2 vacío") {
    val t1 = (1 to 500).map(i => s"Vagón$i").toList
    val t2 = List()
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 12: Tamaño 500 - t1 con 500 vagones y t2 con 500 vagones, todos coinciden") {
    val t1 = (1 to 500).map(i => s"Vagón$i").toList
    val t2 = (1 to 500).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 13: Tamaño 500 - t1 con 500 vagones y t2 con 250 vagones coincidentes") {
    val t1 = (1 to 500).map(i => s"Vagón$i").toList
    val t2 = (1 to 250).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 14: Tamaño 500 - t1 con 500 vagones y t2 con 250 vagones no coincidentes") {
    val t1 = (1 to 500).map(i => s"Vagón$i").toList
    val t2 = (501 to 750).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 15: Tamaño 500 - t1 con 500 vagones y t2 con 500 vagones, algunos coinciden") {
    val t1 = (1 to 500).map(i => s"Vagón$i").toList
    val t2 = (250 to 750).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }


  /** ----------------------
   * Pruebas de grande (5)
   * ---------------------- */
  test("Prueba 16: Tamaño 1000 - t1 con 1000 vagones y t2 vacío") {
    val t1 = (1 to 1000).map(i => s"Vagón$i").toList
    val t2 = List()
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 17: Tamaño 1000 - t1 con 1000 vagones y t2 con 1000 vagones, todos coinciden") {
    val t1 = (1 to 1000).map(i => s"Vagón$i").toList
    val t2 = (1 to 1000).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 18: Tamaño 1000 - t1 con 1000 vagones y t2 con 500 vagones coincidentes") {
    val t1 = (1 to 1000).map(i => s"Vagón$i").toList
    val t2 = (1 to 500).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }

  test("Prueba 19: Tamaño 1000 - t1 con 1000 vagones y t2 con 500 vagones no coincidentes") {
    val t1 = (1 to 1000).map(i => s"Vagón$i").toList
    val t2 = (1001 to 1500).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.isEmpty)
  }

  test("Prueba 20: Tamaño 1000 - t1 con 1000 vagones y t2 con 1000 vagones, algunos coinciden") {
    val t1 = (1 to 1000).map(i => s"Vagón$i").toList
    val t2 = (500 to 1500).map(i => s"Vagón$i").toList
    val resultado = maniobraTrenes.definirManiobra(t1, t2)
    assert(resultado.nonEmpty)
  }
}
