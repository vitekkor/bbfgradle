// Original bug: KT-17055

fun foo() {}

fun test() {
    fun bar() {}

    println(::foo.equals(::foo)) // <- ok
    println(::foo.hashCode()) // <- ok

    println(::bar.equals(::bar)) // <- NPE in JVM target (see below)
    println(::bar.hashCode()) // <- NPE in JVM target (see below)
}
