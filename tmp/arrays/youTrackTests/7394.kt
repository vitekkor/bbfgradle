// Original bug: KT-27339

fun <U : CharSequence?> useGeneric(arg: U) {
    println(requireNotNull(arg).length) // Error: only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type U
}
