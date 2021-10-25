// Original bug: KT-22638

fun main(args: Array<String>) {
    sayHello().talk()   
}

fun  sayHello() = HelloGenerator().apply {
    //talk = this::helloFunction // <- OK on jvz and js : prints hello world 
    talk = ::helloFunction  // <- OK on jvm, KO on js (Cannot read property 'helloFunction' of undefined)
}

class HelloGenerator {
    var talk: () -> Unit = ::helloFunction
    fun helloFunction() { println("hello world") }
}
