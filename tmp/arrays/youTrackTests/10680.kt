// Original bug: KT-2702

open class A<R> {
    open fun foo(r: R): R {return r}
}

open class B : A<String>() {

}

open class C : B() {
    override fun foo(r: String): String {
        super.foo(r)
        return ""
    }
}

fun main(args: Array<String>) {
    C().foo("")
}
