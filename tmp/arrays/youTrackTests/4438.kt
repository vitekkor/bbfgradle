// Original bug: KT-36171

operator fun Unit.get(vararg args: Any) {
    println("Unit.get argument count: ${args.size}")
    (this as Any).get(*args)
}

operator fun Any.get(vararg args: Any) {
    println("Any.get argument count: ${args.size}")
}

fun main() {
    Unit["1", 2, '3']
}
