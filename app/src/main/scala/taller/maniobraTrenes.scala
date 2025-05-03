package taller

import scala.annotation.tailrec

class maniobraTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)
  type Maniobra = List[Movimiento]

  /**
   * Aplica un movimiento a un estado
   * @param e Estado actual (t1, t2, t3)
   * @param m Movimiento a aplicar
   * @return Nuevo estado
   */
  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    val (t1, t2, t3) = e

    (for (mov <- Some(m)) yield mov match {
      case Uno(n) if n > 0 =>
        val a = math.min(n, t1.length)
        val (resto, movidos) = t1.splitAt(t1.length - a)
        (resto, movidos ++ t2, t3)

      case Uno(n) if n < 0 =>
        val a = math.min(-n, t2.length)
        val (movidos, resto) = t2.splitAt(a)
        (t1 ++ movidos, resto, t3)

      case Dos(n) if n > 0 =>
        val a = math.min(n, t1.length)
        val (resto, movidos) = t1.splitAt(t1.length - a)
        (resto, t2, movidos ++ t3)

      case Dos(n) if n < 0 =>
        val a = math.min(-n, t3.length)
        val (movidos, resto) = t3.splitAt(a)
        (t1 ++ movidos, t2, resto)

      case _ => e
    }).get
  }

  /**
   * Aplica una secuencia de movimientos a un estado inicial
   * @param e Estado inicial
   * @param movs Lista de movimientos
   * @return Lista de estados sucesivos
   */

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    @tailrec
    def aplicarMovimientosAux(restantes: Maniobra, acc: List[Estado]): List[Estado] = {
      restantes match {
        case Nil => acc.reverse // Asegura orden natural
        case m :: ms =>
          val siguiente = aplicarMovimiento(acc.head, m)
          aplicarMovimientosAux(ms, siguiente :: acc)
      }
    }

    aplicarMovimientosAux(movs, List(e))
  }


  /**
   * Genera una lista de posibles maniobras para transformar el tren t1 en el orden deseado t2.
   * Cada maniobra es una secuencia de movimientos necesarios para colocar un vagón de t1 en la posición correspondiente de t2.
   *
   * @param t1 Tren de entrada (estado inicial).
   * @param t2 Tren objetivo (estado deseado).
   * @return Lista de maniobras que, aplicadas en orden, reordenan los vagones de t1 para que coincidan con t2.
   */

  def definirManiobra(t1: Tren, t2: Tren): List[Maniobra] = {
    def aux(t1: Tren, t2: Tren, acc: List[Maniobra]): List[Maniobra] = t2 match {
      case Nil => acc.reverse
      case vagon :: tail =>
        t1.indexWhere(_ == vagon) match {
          case -1 => aux(t1, tail, acc)
          case j =>
            val maniobra: Maniobra = List(
              Uno(t1.length - j),
              Dos(t2.indexOf(vagon)),
              Uno(-(t1.length - j)),
              Dos(-t2.indexOf(vagon))
            )
            aux(t1, tail, maniobra :: acc)
        }
    }

    aux(t1, t2, Nil)
  }


}
