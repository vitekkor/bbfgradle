// Original bug: KT-18548

fun <T> foo (t : T) : Unit {
    println("$t$t")
}

fun bar (i : Int) : Unit {
    println("$i$i")
}

fun baz (a : Any) : Unit {
    println("$a$a")
}

fun main(args: Array<String>) {
    foo(21)
    bar(21)
    baz(21)
}
