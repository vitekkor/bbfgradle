// Original bug: KT-33650

package test1
class A<T>{
    fun bar(){}
}

@Deprecated("", ReplaceWith("this.asB().foo(creator)", "test2.asB", "test2.foo"))
fun <T> A<T>.foo(creator: A<T>.() -> Unit) {}

fun test(){
    A<Int>().foo { bar() } // replace this
}
