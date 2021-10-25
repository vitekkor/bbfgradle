// Original bug: KT-41337

import java.util.function.Function

abstract class A<T> : Function<Int, T> where T: CharSequence, T: Comparable<T> {
    fun usage() {
        val a: T = apply(10) // explicit type
        if (a == null) {
            
        }
    }
}
