// Original bug: KT-26857

abstract class Foo() {
        
        abstract val bar: String
        
        init {
            printBar()
        }
        
        fun printBar() {
            println(bar)
        }
    }
