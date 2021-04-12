// Original bug: KT-26264

fun Any.foo() {
    if (this is A<*>) bar()      // [TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR] Type inference failed: fun <T> A<T>.bar(): Unit cannot be applied to receiver: Any  arguments: ()

    if (this is A<*>) this.bar() // OK
}

class A<T>

fun <T> A<T>.bar() {}
