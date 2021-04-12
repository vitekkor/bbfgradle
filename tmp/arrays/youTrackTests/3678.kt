// Original bug: KT-32349

fun <T> Any?.asGeneric() = this as? T //There is warning about unchecked cast here

fun main()
{
	42.asGeneric<Nothing>()!!
	println("Static analyzer thinks that this code is unreachable, but you will see it in output anyway") //Unreachable code, but will be executed
}
