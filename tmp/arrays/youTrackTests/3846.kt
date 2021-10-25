// Original bug: KT-34295

fun foo(x: Boolean) {
    val bar = when (x) {
        true -> { { y: Int -> println(y) } as Int.() -> Unit }
        false -> { { y: Int -> println(y) } as (Int) -> Unit } // BTW: cast here is redundant by IDE, but without it the type inference reports about Int.(Int) -> Unit actual type
    }

    10.run {
        bar() // it's OK
    }
}

fun main() {
    foo(false)
}
