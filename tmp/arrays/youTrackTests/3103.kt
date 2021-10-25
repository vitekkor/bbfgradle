// Original bug: KT-22032

interface Base1 
interface Base2
fun <T> foo(arg: T) where T : Base1, T : Base2 {}

fun bar(arg: Base1) {
    if (arg is Base2) {
        // here arg has a type which is subtype of both Base1 & Base2
        foo(arg) // nevertheless, TYPE_INFERENCE_UPPER_BOUND_VIOLATED
    }
}
