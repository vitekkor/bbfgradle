// Original bug: KT-2689

fun foo(s1: String?, s2: String?) {
    println("${s1!!}")
    s1!!      // <- no diagnostic here

    println("${if (s2 == null) return else s2}")
    if (s2 == null) return       // <- no diagnostic here
}
