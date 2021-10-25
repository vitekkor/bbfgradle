// Original bug: KT-13650

fun main(args: Array<String>) {
    test(null, 1);
}

fun test(nc: C?, a: Any) {
    nc?.x = if (a is String) 0 else throw Exception();
    println(a.toUpperCase()) // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
}

class C {
    var x: Int = 0
}
