// Original bug: KT-10837

@get:Deprecated("")
var foo = ""

fun main(args: Array<String>) {
    println(::foo.annotations)
    println(::foo.getter.annotations)
    println(::foo.setter.annotations)
}
