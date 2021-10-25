// Original bug: KT-38143

// Generic interface, nothing interesting here
interface UseCase<in I, out O> {
    fun execute(input: I): O
}

// We don't want to call use cases without input with explicit Unit argument, so we create this handy extension
fun <O> UseCase<Unit, O>.execute(): O = execute(Unit)

// Type arguments nested too deep for new inference
class Foo : UseCase<Unit, List<List<List<String>>>> {
    override fun execute(input: Unit) = listOf(listOf(listOf("foo")))
}

// These type arguments are OK, see below
class Bar : UseCase<Unit, List<List<String>>> {
    override fun execute(input: Unit) = listOf(listOf("bar"))
}

fun main() {
    val foo = Foo()
    foo.execute(Unit) // Member: OK both versions
    foo.execute() // Extension: Old type inference - OK, new type inference - NOK "Not enough information to infer type variable O"

    val bar = Bar()
    bar.execute(Unit) // Member: OK both versions
    bar.execute() // Extension: OK both versions
}
