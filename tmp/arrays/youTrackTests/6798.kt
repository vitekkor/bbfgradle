// Original bug: KT-7948

class SomeClass(
    var value: Long? = null
)

fun main(args: Array<String>) {
    val someVal = SomeClass()
    someVal.value = try { 10L } catch (t: Throwable) { null }
}
