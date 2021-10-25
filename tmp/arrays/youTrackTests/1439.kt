// Original bug: KT-31292

fun call(block: () -> Unit): Unit = TODO()

class A {
    
    private fun a(x: String) = run {
        {
            x
            call {
            }
        }
    }
}
