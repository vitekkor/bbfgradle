// Original bug: KT-28595

open class A {}
class B : A() {}
class C : A() {}

fun liftClass(boolean: Boolean){
    val a1: A
    if (boolean) { //"Return or assignment can be lifted out" inspection did not work
        a1 = B()
    } else {
        a1 = C()
    }
}
