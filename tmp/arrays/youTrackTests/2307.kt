// Original bug: KT-40391

interface Greeter {
	fun sayHello()
}
inline class InlineNamedGreeter(private val name:String) : Greeter{
	override fun sayHello() = println("Hello, $name!")
}

inline fun <T:Greeter> makeSayHello(implementation: T) = implementation.sayHello()

val greeter = InlineNamedGreeter("world")

fun `Inlined function that accepts inline classes are boxed`(){
	makeSayHello(greeter)
}

fun `Direct call does not box the inline class`(){
	greeter.sayHello()
}
