// Original bug: KT-12833

val lambdasMap: Map<String, (String) -> String?> = mapOf(
        "foo" to {input -> input + "_fooed"},
        "bar" to {input -> "bar"},
        "baz" to {input -> if ('a' in input) "baz" else null}
)

fun main(args: Array<String>){
    val testStr = "lalala"
    listOf("foo", "bar", "baz", "ololoz").forEach {
        println(lambdasMap[it]?.invoke(testStr))
    }
}
