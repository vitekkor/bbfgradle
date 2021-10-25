// Original bug: KT-12706

import java.util.*
fun main(args: Array<String>) {
   val f = Foo()
   println(f.size) // TypeError: Cannot read property 'size' of undefined
}
class Foo(private val list: List<Int> = ArrayList()) : List<Int> by list {    
}
