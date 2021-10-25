// Original bug: KT-774

fun box1() {
    var i : Int? = 1
    if (i != null) {
        i += 10 // Infix call corresponds to a dot-qualified call 'i.plusAssign(10)' which is not allowed on a nullable receiver 'i'. Use ?.-qualified call instead
    }
}
