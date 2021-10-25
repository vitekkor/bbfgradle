// Original bug: KT-8167

fun foo1(str: String): String {
    class A(val x: Int) {
        val a = str
    }
    return A(0).a
}

fun String.foo2(): String {
    class A() {
        val a = this@foo2
    }
    return A().a
}

fun String.bar(): String {
    class A(val x: Int) {
        val a = this@bar
    }
    return A(0).a
}

fun main(args: Array<String>) {
    println(foo1("foo1(String) is ok"))
    println("String.foo2() is ok".foo2())
    println("String.bar() throws VerifyError".bar())
}
