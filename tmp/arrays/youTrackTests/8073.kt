// Original bug: KT-22001

class A(val p: String) {
    var pp = ""
}

operator fun A.plusAssign(s: String) {
    pp = s
}

fun main(args: Array<String>) {
    "rrr".let { A(it) } += "aaa"
}
