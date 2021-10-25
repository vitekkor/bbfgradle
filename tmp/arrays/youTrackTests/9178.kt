// Original bug: KT-15806

/**
 * We declare a package-level function main which returns Unit and takes
 * an Array of strings as a parameter. Note that semicolons are optional.
 */


fun main(args: Array<String>) {
    ExampleClass().yoto()
}


class ExampleClass {
    
    fun yoto() {
        Level.L1.doTheThing()
    }
    
    companion object {
        private fun Any?.doTheThing() {
            println("this: $this in extension function")
            when (this) {
                is String -> println("a string")
                is Level -> {
                    println("this: $this just before inner `when`")
                    when (this) {
                        Level.L1 -> println("L1 from inner `when`")
                    	Level.L2 -> println("L2 from inner `when`")
                    }
                }
                
                else -> println("i don't know")
            }
        }
    }
}


enum class Level {
    L1, 
    L2
}
