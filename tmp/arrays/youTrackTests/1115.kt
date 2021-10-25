// Original bug: KT-17554

fun main() {

    if (true) {
        run { // ok
            1
        }
    }

    @Suppress("SENSELESS_COMPARISON")
    if (true) {
        run { // Exception in thread "main" java.lang.ClassCastException: java.base/java.lang.Integer cannot be cast to kotlin.Unit
            1
        }
    }

}
