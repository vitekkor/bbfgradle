// Original bug: KT-31613

enum class X(y : Y) {A(Y.B);
    init {
        when (y) {Y.B -> {}}
    }
    
    fun neverCall(x : X) {
        when(x) {X.A -> {}}
    }
}

enum class Y() {B }

fun main(args: Array<String>) {
    print(X.A)
}
