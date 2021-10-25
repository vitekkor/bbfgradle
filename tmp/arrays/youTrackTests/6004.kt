// Original bug: KT-30503

fun test() {
    var b: Any? = null
    true && (if (true) {b = 11; true} else {b = 123; true}) && (if (true) {b.toString(); true} else {b.toString(); true}) // OK
}
