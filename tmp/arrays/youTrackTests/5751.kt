// Original bug: KT-19033

package debugger.watches. kt19033

fun main(args: Array<String>) {
    foo("ABC")
}

fun foo(name: String) = bar(name.toLowerCase(), name.length)

inline fun bar(name: String, otherArg: Int) {
    println(name)
}
