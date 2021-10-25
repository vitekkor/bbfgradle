// Original bug: KT-44723

// MODULE: lib
// FILE: 1.kt

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class MQualifiedTypeName private constructor(val s: String) {
    constructor(x: Int) : this(x.toString())
}

// MODULE: main(lib)
// FILE: 2.kt

fun test(x: MQualifiedTypeName? = null): Any? = x
