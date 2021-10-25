// Original bug: KT-45364

fun box(): Any {
    val l: Any = {}
    val javaClass = l.javaClass
    val enclosingClass = javaClass.getEnclosingClass().getName()
    return "enclosing class: $enclosingClass"
}
