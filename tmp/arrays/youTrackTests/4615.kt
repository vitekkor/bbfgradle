// Original bug: KT-35555

class X(val field: Any?)

fun foo(list: List<X>) {
    for (x in list) {
        if (x.field != null) {
            println(x.field.hashCode())
        }
    }
    // can be any other code here
}
