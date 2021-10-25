// Original bug: KT-4542

interface  A
interface B: A
interface C: B

fun bar(b: B) {} //this 'bar' is chosen
fun <T: A> bar(t: T) {} //if it's commented out, the next function is chosen
fun <T: C> bar(t: T) {}

fun test(x: C) {
    bar(x)
}
