// Original bug: KT-16285

class A<T>{}

fun <T> foo(a:T) = A<T>()

fun bar() {
    val v0 = 1
    val v1 = foo(v0)
    val v2 = foo(v1)
    val v3 = foo(v2)
    val v4 = foo(v3)
    val v5 = foo(v4)
    val v6 = foo(v5)
    val v7 = foo(v6)
    val v8 = foo(v7)
    val v9 = foo(v8)
    val v10 = foo(v9)
    val v11 = foo(v10)
    val v12 = foo(v11)
    val v13 = foo(v12)
    val v14 = foo(v13)
    val v15 = foo(v14)
    val v16 = foo(v15)
    val v17 = foo(v16)
    val v18 = foo(v17)
    val v19 = foo(v18)
    val v20 = foo(v19)
//...and so on. I compiled up to v25: "Compilation completed successfully in 5m 13s 676ms"
}
