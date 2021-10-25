// Original bug: KT-10223

inline fun <T> using(input: Any?, f: (Any?) -> T): T = f(input)

fun main(args: Array<String>) {
    
    val input: Any? = null
    
    val f: (Any?) -> String = { 
        when (it) {
            is String -> it
            else -> throw RuntimeException()
        }
    }

    // works with lambda with explicit type
    val test1: String = using(input, f)
    
    // works with explicit cast
    val test2: String = using(input) { 
        when (it) {
            is String -> it as String
            else -> throw RuntimeException()
        }
    }
    
    // works with explicit generic 
    val test3: String = using<String>(input) { 
        when (it) {
            is String -> it
            else -> throw RuntimeException()
        }
    }

    // Error:(32, 24) Type inference failed. Expected type mismatch: inferred type is kotlin.Any? but kotlin.String was expected
    val test4: String = using(input) { 
        when (it) {
            is String -> it
            else -> throw RuntimeException()
        }
    }

}
