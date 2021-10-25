// Original bug: KT-15706

open class In<in I>
class Child<T> : In<T>()

fun test(t: In<String>) { // Child<T> <: In<String>  => T :> String
    if (t is Child) {
        val child: Child<String> = t // this is incorrect -- t is not subtype of Child<String>
    }
}

// call: test(Child<Any>())
