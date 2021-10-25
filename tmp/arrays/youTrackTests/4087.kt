// Original bug: KT-8966

fun <T : CharSequence> T.bar() {}

fun Any.foo() {
    if (this is String) {
        this.bar() // OK, smart cast on `this`
        bar()      // [TYPE_INFERENCE_UPPER_BOUND_VIOLATED] Type parameter bound for T in fun <T : CharSequence> T.bar(): Unit is not satisfied: inferred type Any is not a subtype of CharSequence
    }
}
