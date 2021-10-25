// Original bug: KT-28683

typealias StringSupplier = () -> String

typealias WhateverStringFunction = (Any?) -> String

fun main(args: Array<String>) {
    whatIs(object : StringSupplier, WhateverStringFunction {
        override fun invoke() = TODO()
        override fun invoke(a: Any?): String = TODO()
        override fun toString(): String = "object : StringSupplier, WhateverStringFunction"
    })

    whatIs(object : WhateverStringFunction, StringSupplier {
        override fun invoke() = TODO()
        override fun invoke(a: Any?): String = TODO()
        override fun toString(): String = "object : WhateverStringFunction, StringSupplier"
    })
}

fun whatIs(f: Any) {
    try {
        f as StringSupplier
        println("$f is a StringSupplier")
    } catch (t: Throwable) {
        println("$f is not a StringSupplier")
    }
    try {
        f as WhateverStringFunction
        println("$f is a WhateverStringFunction")
    } catch (t: Throwable) {
        println("$f is not a WhateverStringFunction")
    }
}
