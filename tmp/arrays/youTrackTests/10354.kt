// Original bug: KT-6248

fun foo(): String {
    return if (false) "" else {
        val p = ""
        p // breakpoint on this line: p is not visible in debugger
    }
}

fun main(args: Array<String>) {
    foo()
}
