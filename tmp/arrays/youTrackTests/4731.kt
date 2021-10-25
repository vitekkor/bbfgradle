// Original bug: KT-26435

fun main(args: Array<String>) {
    println("Start")
    doSth(A(Id))
}
object Id


sealed class Base(val id: Id)
class A(id: Id) : Base(id)
class B(id: Id) : Base(id)

fun doSth(base: Base): Base =
        if (base is A) process(base, f = { "A" })
        else if (base is B) process(base, f = { "B" })
        else error("123")

inline fun <reified T : Base> process(t: T, f: (T) -> Unit): T {
    f(t)
    return A(t.id) as T
}
