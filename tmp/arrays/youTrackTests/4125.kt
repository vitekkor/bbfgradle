// Original bug: KT-10755

interface IA
interface IB
interface IC
interface IG<T>

object X1 : IA, IB
object Y1 : IA, IC

object X2 : IA, IG<X2>
object Y2 : IA, IG<Y2>

fun <T> select(a: T, b: T) = a

val xy1 = select(X1, Y1) // IA
val xy2 = select(X2, Y2) // Any, expected(?): IA
val num = select(1, 1.0) // Any, expected(?): Number
