// Original bug: KT-42005

// Bug happens on JVM , JVM -Xuse-ir
// !LANGUAGE: +InlineClasses
class A

inline class Z1(val x: A) {
    override fun toString(): String {
        return x.toString()
    }
}

fun main() {
    val a: Z1? = null
    a.toString()
}
