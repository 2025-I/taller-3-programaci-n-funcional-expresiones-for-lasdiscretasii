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
}
