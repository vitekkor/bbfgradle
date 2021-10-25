// Original bug: KT-30877

// FILE: a.kt

public inline fun assertFailsWith() {
    launchIn2 {
        catch2<Any>(10) {
        }
    }
}

fun foo() {
    assertFailsWith()
}

public fun launchIn2(builder: () -> Unit) {  }

public inline fun <reified T> catch2(t: T, action: (T) -> Unit) {
    T::class
}


// FILE: box.kt

fun box() : String {
    assertFailsWith()
    return "OK"
}
