// Original bug: KT-45756

fun foo() {
    val rec = object {
        fun a() {
            b() // works!
        }

        fun b() {
            a() // works!
        }
    }
    rec.a() // works
    rec.b() // works
}
