// Original bug: KT-29059

inline class Foo(val v: Int)

@kotlin.jvm.JvmName("someMethod1")
fun String.method1(v: Foo) { }

@kotlin.jvm.JvmName("someMethod2")
fun Foo.method2(v: Int) { }
