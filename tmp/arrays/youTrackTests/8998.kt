// Original bug: KT-17339

class C {
    fun foo() { }
}
fun main(args: Array<String>) {
    val f = C::foo::invoke
    val s = f.toString()
    //the same for "val s = f.isAbstract" and other f.<anything>
}
