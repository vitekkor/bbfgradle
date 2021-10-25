// Original bug: KT-42077

var s = ""
class C {
    var x = 0
    	get() {
            s += "get"
            return field
        }
    	set(value) {
            s += "set"
            field = value
        }
}
fun main() {
    val c: C = C()
    println(c.x++)
    println(s)
    s = ""
    println(++c.x)
    println(s)
}
