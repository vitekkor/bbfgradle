// Original bug: KT-38162

@DslMarker
annotation class ExampleDsl

fun outer(body: OuterDsl.() -> Unit) = OuterDsl.body()

@ExampleDsl
object OuterDsl {
    val outerOnly = "This shouldn't be directly accessible from InnerDsl"
    inline fun inner(body: InnerDsl.() -> Unit) = InnerDsl.body()
}

@ExampleDsl
object InnerDsl

fun main() {
    outer {
        outerOnly.let { str -> // here `let` shouldn't be marked as redundant
            inner {
                println(str)
            }
        }
    }
}
