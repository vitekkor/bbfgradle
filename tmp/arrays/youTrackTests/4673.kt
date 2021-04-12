// Original bug: KT-32267

fun main(c1: CommandFactory<Command1>, someService: SomeService) {
    c1.invoke { someService::execute }
}

fun <T> CommandFactory<T>.invoke(handler: () -> ((T) -> Unit)) {
    println("success: $handler")
}

interface CommandFactory<T>
class Command1
class Command2


interface SomeService {
    fun execute(command: Command1)
    fun execute(command: Command2)
}
