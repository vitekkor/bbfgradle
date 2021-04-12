// Original bug: KT-19980

class ExtSubject(var property: String)
class ExtContainer() {
    var ExtSubject.extNestVar: String
        get() = property
        set(p) { property = p }

    fun classContext() {
        var es = ExtSubject("")
        println() // Breakpoint A.
    }
}

var ExtSubject.extTopVar
    get() = property
    set(p) { property = p }

fun main(args: Array<String>) {
    val pc = ExtContainer()
    pc.classContext()

    val es = ExtSubject("")
    println() // Breakpoint B.
} 