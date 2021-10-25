// Original bug: KT-32443

fun UByte?.receiver(): Unit = TODO()
fun returnType(): UByte = TODO()
fun defaultParameter(ubyte: UByte = 1u): Unit = TODO()

fun reproducers() {
    null.receiver() // Not reported
    val f = returnType() // Not reported
    // val f: UByte = returnType() // Reported because of explicit type
    defaultParameter() // Not reported
}
