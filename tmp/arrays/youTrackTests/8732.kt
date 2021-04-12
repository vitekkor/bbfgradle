// Original bug: KT-19004

fun foo(a: Any) {
    if (a == "") {
        println(a)  
    } else if (a is String) {
        println(a)
    } else if (a is List<*>) {
        @Suppress("UNCHECKED_CAST")
        println(a as List<String>)
    }
}
