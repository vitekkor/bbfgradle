// Original bug: KT-30576

fun foo(arg: Any) {
    if (arg is String) {
        arg ?: return // [USELESS_ELVIS] Elvis operator (?:) always returns the left operand of non-nullable type String
    }
}
