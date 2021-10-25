// Original bug: KT-11345

public fun main(args: Array<String>) {
	println(test(0))
}

class A (val toString: String)

fun test(case: Int): String {
    val a = A("foo")
    return when (case) {
    	1 -> "a = ${a}" // TypeError: Cannot convert object to primitive value
    	2 -> "${a}" // TypeError: a.toString is not a function
        else -> "${a.toString}" // "foo"
    }
}
