// Original bug: KT-3450

public class A {
    public operator fun get(vararg attrs : Pair<String, String>) : A = this

    public infix operator fun invoke(builder : A.() -> Unit) {

    }
}
fun x(y : String) : A = A()

//this compiles 
val working = x("aaa")["a" to "b"] invoke {}

//this does not 
val not_working = x("aaa")["a" to "b"] {}
