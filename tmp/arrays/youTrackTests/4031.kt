// Original bug: KT-14980

fun foo() {
    val x = ""
    val a = if (x.isEmpty()) x else null  // all fine
    val b = if (x.isEmpty()) (x as String?) else returnNull() // warning - useless cast
    val c = if (x.isEmpty()) x else returnNull() // error
}

fun <T> returnNull(): T? = null
