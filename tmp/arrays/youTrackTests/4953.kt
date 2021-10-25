// Original bug: KT-35044

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double {
    when (expr) {
        is Const -> println(expr.number)
        is Sum -> eval(expr.e1) + eval(expr.e2)
//        NotANumber -> Double.NaN
    }
    return 0.0
}
