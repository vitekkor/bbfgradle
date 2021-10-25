// Original bug: KT-32349

val tryExecUnreachable = execUnreachable() //Initialized before notYetInitialized
fun execUnreachable()
{
	notYetInitialized //This variable is null right now, because of initialization order. No access to this variable, so there is no NPE, but static analyzer see 'Nothing' and thinks control flow ends up here
	println("Static analyzer thinks that this code is unreachable, but you will see it in output anyway") //Unreachable code, but will be executed
}

val notYetInitialized : Nothing = initFail() //'Instance' of Nothing, by the way, it is meant to be impossible
fun initFail() : Nothing = throw Exception("Crash with exception eventually") //This example will crash here when it will be time to initialize 'notYetInitialized' variable

fun main()
{
	tryExecUnreachable //Use it somehow
}
