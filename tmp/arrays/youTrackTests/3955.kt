// Original bug: KT-37554

interface Out<out T>
interface In<in T>
interface Recursive<T : In<T>>

interface Specialized : In<Specialized>

class Parent : Specialized

fun <T : In<T>> foo(o: Out<T>): Recursive<T>? = null

fun test(o: Out<Parent>) {
    foo(o) ?: return // Error: type mismatch 
}
