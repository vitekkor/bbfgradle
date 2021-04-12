// Original bug: KT-8462

fun <T, X, Y> Pair<T, X>.bar(t: () -> Pair<T, Y>) =  t()
fun <T, X> Pair<T, X>.bas(t: () -> Pair<T, X>) =  t()

fun test(a: Pair<Number, Char>, b: Pair<Int, String>) {
    b.bar { a } // inference fail 
    b.bas { a } // ok
}
