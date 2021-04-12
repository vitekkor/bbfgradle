// Original bug: KT-37419

interface Receiver
interface Parameter
typealias LambdaWithReceiver = Receiver.(Parameter) -> Unit

fun Receiver.method(param: Parameter): LambdaWithReceiver = TODO()

enum class E { VALUE }

class SomeClass {
    val e = E.VALUE

    val fail: LambdaWithReceiver
        get() = when (e) {
            E.VALUE -> { param: Parameter ->
                method(param) // [TYPE_MISMATCH], [UNRESOLVED_REFERENCE_WRONG_RECEIVER]
            }
        }
}
