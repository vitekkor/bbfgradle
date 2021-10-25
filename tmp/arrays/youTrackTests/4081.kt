// Original bug: KT-30831

class Outer<T>(init: () -> T)
class Inner<E>

var foo = Outer<Inner<Int>> { Inner() }

fun bar() {
    foo = Outer { Inner() } // [TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER] Type inference failed: Not enough information to infer parameter E in constructor Please specify it explicitly.
}
