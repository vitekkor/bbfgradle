// Original bug: KT-6008

fun test() {
    val a = if (true) 1 else 0.8   // incorrect warning "type was casted to Any",
                                   // should be type checked to Double instead
}
