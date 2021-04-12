// Original bug: KT-22470

inline fun <reified T: Number> function() {
    ("" as? T)?.let { throw IllegalStateException("never thrown") }
    
    val lambda = { arg: Any ->
        (arg as? T)?.let { throw IllegalStateException("blows up, but shouldn't") }
    }
    
    lambda("")
}

fun main(args: Array<String>) {
    function<Float>()
}
