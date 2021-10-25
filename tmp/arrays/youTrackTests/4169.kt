// Original bug: KT-20734

fun main(args: Array<String>) {
    val a = mutableSetOf<Boolean>()
    val p = Pair<Boolean?, Boolean?>(false, false)
    a.remove(p.first) // Ok, resolved to extension in the stdlib
    if (p.first != null) {
        a.remove(p.first) // Smart cast impossible, resolved to member
    }
}
