// Original bug: KT-28697

fun main(args: Array<String>)= Foobar().print()

class Foobar{
    val name = "martin"

    fun print(){
        val name = "david" // notification here would be awesome
        println(name)
    }
}
