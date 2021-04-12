// Original bug: KT-18329

fun bar(i: Int) {
    //do nothing
}

fun foo1() {
    for (i in 1..10) {
        bar(i)
    }
}

fun foo2() {
    for (i in (1..10)) { // difference only in parentheses
        bar(i)
    }
}

fun main(args: Array<String>) {
    foo1()
    foo2()
}
